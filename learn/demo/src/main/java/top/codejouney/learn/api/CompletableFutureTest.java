package top.codejouney.learn.api;

import java.util.concurrent.CompletableFuture;

/**
 * @author Antvictor
 * @date 2025/5/6
 **/
public class CompletableFutureTest {
    static CompletableFuture<Void> completableFuture = new CompletableFuture();
    public static void test() {
        Thread thread = new Thread(() -> {
            System.out.println("end");
            completableFuture.complete(null);
        });
        completableFuture.thenRun(() -> {
            System.out.println("Get");
        });
        thread.start();

        CompletableFuture.runAsync(() -> {
            System.out.println("end");
        }).thenRun(() -> {
            System.out.println("OK");
        });
    }

    public static void main(String[] args) {
        test();
    }
}
