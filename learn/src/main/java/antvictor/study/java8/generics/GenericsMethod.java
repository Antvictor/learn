package antvictor.study.java8.generics;

/**
 * @author Antvictor
 * @date 2024/1/22
 **/
public class GenericsMethod<String> {

    public static <E> E test(E e) {
        System.out.println(e);
        return e;
    }

    public static <E> E test() {
        return (E) "null";
    }
}
