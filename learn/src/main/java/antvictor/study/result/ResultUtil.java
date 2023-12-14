package antvictor.study.result;

/**
 * @author Antvictor
 * @date 2023/12/13
 **/
public class ResultUtil {
    public static Result success(Object data) {
        return new Result(data);
    }
}
