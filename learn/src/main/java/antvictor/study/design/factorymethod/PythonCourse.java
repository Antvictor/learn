package antvictor.study.design.factorymethod;

/**
 * @author Antvictor
 * @date 2023/12/15
 **/
public class PythonCourse implements ICourse {
    @Override
    public void study() {
        System.out.println("study python");
    }
}
