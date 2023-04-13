package larry.study.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import larry.study.entity.Test1;
import larry.study.entity.Test1Example;
import larry.study.mapper.Test1Mapper;
import larry.study.server.TestService;

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
    public void addBatch(List<Test1> test) {
        for (Test1 test1 : test) {
            addTest(test1);
        }
    }

    @Override
    public List<Test1> getTest(Test1Example test1Example) {
        return test1Mapper.selectByExample(test1Example);
    }

    @Override
    public Object queryUser() {
        return test1Mapper.selectByPrimaryKey(1);
    }

}
