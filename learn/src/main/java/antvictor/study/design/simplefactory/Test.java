package antvictor.study.design.simplefactory;

/**
 * @author Antvictor
 * @date 2023/12/15
 **/
public class Test {
    public static void main(String[] args) {
        ICourse course = CourseFactory.createCourse(JavaCourse.class);
        course.study();
    }
}
