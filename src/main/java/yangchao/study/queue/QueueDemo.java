package yangchao.study.queue;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueDemo {

    private Queue<TestBean> queue = new LinkedBlockingQueue<>();

    @Test
    public void demo() {
        for (int i = 0; i < 5; i++) {
            TestBean testBean = new TestBean();
            testBean.addKey(UUID.randomUUID().toString());
            System.out.println( i + ":" + JSON.toJSONString(testBean.getKey()));
            queue.add(testBean);
        }
        List<String> test = new ArrayList<>();
        queue.forEach(i ->{
            test.addAll(i.getKey());
        });

        test.forEach(System.out::println);

        System.out.println("this is 2:" + test.get(2));
        System.out.println("this is 0:" + test.get(0));
        System.out.println("this is 4:" + test.get(4));
    }


}
