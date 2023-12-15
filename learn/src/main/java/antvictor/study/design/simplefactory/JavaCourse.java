package antvictor.study.design.simplefactory;

/**
 * @author Antvictor
 * @date 2023/12/15
 **/
public class JavaCourse implements ICourse{
    @Override
    public void study() {
        System.out.println("study Java");
    }
}
