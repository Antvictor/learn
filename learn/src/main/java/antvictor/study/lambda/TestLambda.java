package antvictor.study.lambda;

import java.io.InputStream;
import java.util.Scanner;

/**
 * @author exccedy
 * @date 2023/4/21
 **/
public class TestLambda {
    interface A {
        String a(String name);
    }
    interface B {
        String b(String name, String msg);
    }
    interface C {
        String c();
    }

    interface D {
        String d(Entity entity, String msg);
    }
    interface E {
        String e(Entity entity, String msg, String e);
    }

    static class Entity {
         static String show(String msg) {
            System.out.println(msg);
            return msg;
        }
        String show2(String msg) {
             return msg + "!";
        }
    }

    public static void main(String[] args) {
   /*     Entity entity = new Entity();
        // 静态（绑定了this)
        A a = Entity::show;
        // 绑定了对象
        A a1 = entity::show2;

//        D d = entity::show2; // 签名不一致
//        C c = entity::show2; // 签名不一致
//        A a = Entity::show2; // 签名一致，但没有绑定对象
        // 签名不一致，但入参第一个是对象本身，对象的方法入参对应后面的参数
        D d = Entity::show2;
//        E e = Entity::show2; // 入参中除去对象后面的与对象方法中的参数不匹配
*/
        Scanner scanner = new Scanner(System.in);
        long i = 0;
        i += scanner.nextLong();
        i += scanner.nextLong();
        System.out.println(i);
    }
}
