package larry.study.server;

import larry.study.entity.Test1;
import larry.study.entity.Test1Example;

import java.util.List;

public interface TestService {
    void addTest(Test1 test);
    void addBatch(List<Test1> test);
    List<Test1> getTest(Test1Example test1Example);

    Object queryUser();

}
