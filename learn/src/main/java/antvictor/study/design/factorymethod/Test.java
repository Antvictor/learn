package antvictor.study.design.factorymethod;


/**
 * @author Antvictor
 * @date 2023/12/15
 **/
public class Test {
    public static void main(String[] args) {
        ICourseFactory factory = new JavaCourseFactory();
        ICourse course = factory.create();
        course.study();
    }
}
