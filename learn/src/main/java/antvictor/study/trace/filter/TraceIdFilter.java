package antvictor.study.trace.filter;

import antvictor.study.trace.util.TraceIdUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Antvictor
 * @date 2023/12/13
 **/
@Slf4j
public class TraceIdFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init trace id filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String traceId = request.getHeader(TraceIdUtil.TRACE_ID);
            TraceIdUtil.generateTraceId(traceId);
            // 将请求和应答传递到下一个处理器处理
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 避免内存溢出
            TraceIdUtil.clearTraceId();
        }

    }

    @Override
    public void destroy() {
        log.info("end trace id filter");
    }
}
