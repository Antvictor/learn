package antvictor.study.design.singleton;

/**
 * 双重检查锁式单例
 * 优点：线程安全，效率高
 * 缺点：可读性不高，不优雅
 * @author Antvictor
 * @date 2023/12/20
 **/
public class LazyDoubleCheckSingleton {
    private volatile static LazyDoubleCheckSingleton instance;
    private LazyDoubleCheckSingleton() {
    }
    public static LazyDoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (LazyDoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new LazyDoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
