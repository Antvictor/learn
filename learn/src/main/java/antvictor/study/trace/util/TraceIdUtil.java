package antvictor.study.trace.util;

import cn.hutool.core.util.IdUtil;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

/**
 * @author Antvictor
 * @date 2023/12/13
 **/
public class TraceIdUtil {
    public final static String TRACE_ID = "TraceId";

    /**
     * 获取traceId
     * @return
     */
    public static String getTraceId() {
        return MDC.get(TRACE_ID);
    }

    /**
     * 生成traceId
     * @return
     */
    public static String generateTraceId(){
        String traceId = IdUtil.fastSimpleUUID().toUpperCase();
        MDC.put(TRACE_ID, traceId);
        return traceId;
    }

    /**
     * 生成traceId
     * @param traceId
     * @return
     */
    public static String generateTraceId(String traceId){
        if (StringUtils.isEmpty(traceId)) {
            return generateTraceId();
        }
        MDC.put(TRACE_ID, traceId);
        return traceId;
    }

    /**
     * 清除traceId
     */
    public static void clearTraceId(){
        MDC.remove(TRACE_ID);
    }
}
