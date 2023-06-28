package antvictor.study.function;

import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

/**
 * 测试函数编程
 * @author Antvictor
 * @date 2023/5/8
 **/
public class FunctionTest {

    static IntFunction it = (i) -> i++;


    public static void main(String[] args) {
        ToIntFunction<Integer> test = i -> (int)i + 1;
        Integer i = 129;
        test.applyAsInt(i);
        System.out.println(i);
    }
}
