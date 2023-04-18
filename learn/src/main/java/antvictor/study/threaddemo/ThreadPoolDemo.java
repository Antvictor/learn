package antvictor.study.threaddemo;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolDemo {

    public static void main(String [] args){
        BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<Runnable>(10);
        RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,10,0l,TimeUnit.MICROSECONDS,workingQueue,rejectedExecutionHandler);

        AtomicInteger atomicInteger = new AtomicInteger();

        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    atomicInteger.getAndIncrement();
                    System.out.println(atomicInteger.get());

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        System.out.println("会影响主线程嘛");

    }
}
