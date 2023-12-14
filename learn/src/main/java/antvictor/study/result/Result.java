package antvictor.study.result;

import antvictor.study.trace.util.TraceIdUtil;
import lombok.Data;

/**
 * @author Antvictor
 * @date 2023/12/13
 **/
@Data
public class Result {
    private Integer code;
    private String traceId = TraceIdUtil.getTraceId();

    private Object data;

    public Result(Integer code,  Object data) {
        this.code = code;
        this.data = data;
    }

    public Result(Object data) {
        this.code = 200;
        this.data = data;
    }
}
