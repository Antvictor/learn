package antvictor.study.design.singleton;

/**
 * @author Antvictor
 * @date 2023/12/21
 **/
public class ThreadLocalSingleton {
    private static ThreadLocal<ThreadLocalSingleton> threadLocal = ThreadLocal.withInitial(() -> new ThreadLocalSingleton());

    private ThreadLocalSingleton() {
    }

    public static ThreadLocalSingleton getInstance() {
        return threadLocal.get();
    }
}
