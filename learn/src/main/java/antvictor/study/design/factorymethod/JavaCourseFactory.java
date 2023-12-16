package antvictor.study.design.factorymethod;

/**
 * @author Antvictor
 * @date 2023/12/16
 **/
public class JavaCourseFactory implements ICourseFactory{
    @Override
    public ICourse create() {
        return new JavaCourse();
    }
}
