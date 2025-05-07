package antvictor.study.java8.func;

/**
 * @author Antvictor
 * @date 2024/3/10
 **/
public class B {
    public void test(Func func) {
        // 一堆处理
        String s = func.func(1);
        System.out.println(s);
        // 根据返回进行处理
        System.out.println("处理");
    }
}
