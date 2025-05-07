package antvictor.study.result;

import antvictor.study.trace.util.TraceIdUtil;
import lombok.Data;

/**
 * @author Antvictor
 * @date 2023/12/13
 **/
@Data
public class Result<T> {
    private Integer code;
    private String traceId = TraceIdUtil.getTraceId();

    private T data;

    public Result(Integer code,  T data) {
        this.code = code;
        this.data = data;
    }

    public Result(T data) {
        this.code = 200;
        this.data = data;
    }
}
