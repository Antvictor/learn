package antvictor.study.java8.generics;

/**
 * @author Antvictor
 * @date 2024/1/22
 **/
public class Test {
    public static void main(String[] args) {
        Generics<String> generics = new Generics<>();
        generics.setData("123");
        System.out.println(generics.getData());
        String s = GenericsMethod.test();
        Integer i = GenericsMethod.test();
        System.out.println(s);
        System.out.println(i);
    }


}
