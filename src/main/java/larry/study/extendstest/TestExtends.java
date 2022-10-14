package larry.study.extendstest;

import larry.study.entity.TTest1;
import larry.study.entity.TTest2;
import larry.study.entity.Test;

/**
 * @author exccedy
 * @date 2022/7/27
 **/
public class TestExtends {

    public static void test(Test test) {
        TTest1 tTest1 = (TTest1) test;
        System.out.println(tTest1.getName());
    }

    public static void test2(Test test) {
        TTest2 tTest1 = (TTest2) test;
        System.out.println(tTest1.getCode());
    }

    public static void main(String[] args) {
        TTest1 tTest1 = new TTest1();
        tTest1.setName("test");
        test(tTest1);
        TTest2 tTest2 = new TTest2();
        tTest2.setCode("success");
        test2(tTest2);

    }
}
