package antvictor.study.design.factorymethod;

/**
 * @author Antvictor
 * @date 2023/12/16
 **/
public class PythonCourseFactory implements ICourseFactory{
    @Override
    public ICourse create() {
        return new PythonCourse();
    }
}
