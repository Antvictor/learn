package antvictor.study.design.singleton;

/**
 * 饿汉式
 * 优点：线程安全，执行效率高
 * 缺点：类加载时就初始化，浪费内存
 * @author Antvictor
 * @date 2023/12/20
 **/
public class HungrySingleton {
    private HungrySingleton() {
        if (null != instance) {
            throw new RuntimeException("单例模式，不能实例化");
        }
    }

    private static HungrySingleton instance = new HungrySingleton();

    public static HungrySingleton getInstance() {
        return instance;
    }
}
