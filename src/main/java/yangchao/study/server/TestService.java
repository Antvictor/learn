package yangchao.study.server;

import yangchao.study.entity.Test1;
import yangchao.study.entity.Test1Example;

import java.util.List;

public interface TestService {
    void addTest(Test1 test);
    List<Test1> getTest(Test1Example test1Example);
}
