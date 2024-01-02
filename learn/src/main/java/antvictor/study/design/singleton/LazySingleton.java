package antvictor.study.design.singleton;

/**
 * 饿汉式
 * 优点：内存使用率高
 * 缺点：线程不安全
 * 加锁后：优点：线程安全
 * 缺点：性能低，造成排队
 * @author Antvictor
 * @date 2023/12/20
 **/
public class LazySingleton {
    private static LazySingleton instance;
    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
