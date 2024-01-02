package antvictor.study.design.singleton;

import java.lang.reflect.Constructor;

/**
 * @author Antvictor
 * @date 2023/12/20
 **/
public class HungrySingletonTest {

    public static void main(String[] args) {
        Class<?> clazz = HungrySingleton.class;
        try {
            Constructor<?> c = clazz.getDeclaredConstructor(null);

            c.setAccessible(true);

            System.out.println(c.newInstance());
            System.out.println(c.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}