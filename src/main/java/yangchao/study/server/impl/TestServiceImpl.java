package yangchao.study.server.impl;

import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import yangchao.study.entity.Test1;
import yangchao.study.entity.Test1Example;
import yangchao.study.mapper.Test1Mapper;
import yangchao.study.server.TestService;

import java.util.List;

@Service
@Component
public class TestServiceImpl implements TestService {

    @Autowired
    Test1Mapper test1Mapper;

    @Override
    public void addTest(Test1 test) {
        test1Mapper.insert(test);
    }

    @Override
    public List<Test1> getTest(Test1Example test1Example) {
        return test1Mapper.selectByExample(test1Example);
    }

}
