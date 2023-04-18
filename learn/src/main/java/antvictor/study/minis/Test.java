package antvictor.study.minis;

import antvictor.study.minis.tests.AsService;

/**
 * @author exccedy
 * @date 2023/3/14
 **/
public class Test {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        AsService asService = (AsService) context.getBean("asService");
        asService.sayHello();
    }
}
