package yangchao.study.lockstudy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLockDemo {
    private List<String> test = new ArrayList<>();
    private Lock lock ;
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);


    public TestLockDemo(Lock lock) {
        this.lock = lock;
        testLock();
    }

    public void testLock() {
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            lock.lock();
            test.forEach(System.out::println);
            lock.unlock();
        },1,1,TimeUnit.SECONDS);

    }

    public List<String> getTest() {
        return test;
    }

    public void setTest(List<String> test) {
        this.test = test;
    }
}
