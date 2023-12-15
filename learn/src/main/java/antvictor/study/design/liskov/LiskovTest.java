package antvictor.study.design.liskov;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author Antvictor
 * @date 2023/12/14
 **/
public class LiskovTest {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap();

        /**
         * 打印结果：
         * this is father max
         * this is father min
         * this is father eq
         */
//        Father s = new Father();
        /**
         * 打印结果：
         * this is father max
         * this is father min
         * this is son eq
         * 结论：eq方法不符合里氏替换原则，更改了业务逻辑
         */
//         Father s = new Son();


        /**
         * 打印结果：
         * this is father max
         * this is son min
         * this is son eq
         * 结论：max方法不符合里氏替换原则，更改了业务逻辑
         */
        Son s = new Son();
        // 只要传HashMap，无论对象如何变（Son/Father）都不会影响执行结果，必定执行父类方法。
        // 符合里氏替换原则的，子类替换父类后不会影响原来的业务。
        s.max(hashMap);

        // 比父类的入参小，当引用及对象都换成Son时，执行子类方法，不符合里氏替换原则，更改了业务逻辑
        s.min(hashMap);

        // 重写了父类方法，不符合里氏替换原则，更改了业务逻辑
        s.eq(hashMap);

    }


}
