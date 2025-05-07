package antvictor.study.result;

import org.apache.poi.ss.formula.functions.T;

/**
 * @author Antvictor
 * @date 2023/12/13
 **/
public class ResultUtil {
    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }
}
