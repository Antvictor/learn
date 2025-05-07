package antvictor.study.java8.func;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Antvictor
 * @date 2024/3/10
 **/
public class A implements Func{
    List<Integer> list  = new ArrayList<>();

    public void test() {
        B b = new B();
        b.test(this);

        System.out.println(list.get(0));
    }

    public static void main(String[] args) {
        A a = new A();
        a.test();
    }

    @Override
    public String func(int i) {
        list.add(i);
        return "add success";
    }
}
