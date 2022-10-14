package larry.study.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @author exccedy
 * @date 2022/7/7
 **/
public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        final String[] s = {""};
        //jdk1.8之前的实现方式
        CompletableFuture<List<String>> future = CompletableFuture.supplyAsync(new Supplier<List<String>>() {
            @Override
            public List<String> get() {
                System.out.println("task started!");

                try {
                    //模拟耗时操作
                    Thread.sleep(10000);
                    s[0] = "thanks";
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<String> strings = new ArrayList<>();
                for (String s1 : s) {
                    strings.add(s1);
                }
                return strings;
            }
        }, executor);

        //采用lambada的实现方式
        future.thenAccept(e -> System.out.println(e + " ok"));
        future.thenAcceptAsync(e -> System.out.println(e + "你   ok2"));
        System.out.println("main thread is running");
        executor.shutdown();
    }
}
