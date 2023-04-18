package antvictor.study.list;

import org.springframework.stereotype.Service;

/**
 * @author exccedy
 * @date 2022/7/25
 **/
@Service
public class TestList2 extends ListTest{

    @Override
    public void add() {
        s.add("ListTest2");
    }
}
