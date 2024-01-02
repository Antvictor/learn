package antvictor.study.design.singleton;

/**
 * 懒汉式-静态内部类
 * 优点：延迟加载，线程安全，优雅
 * 缺点：会被破坏单例（饿汉、懒汉都会被反射破坏单例，解决方案：构造方法判断是否有实例，有则报错）
 * @author Antvictor
 * @date 2023/12/20
 **/
public class LazyStaticInnerClassSingleton {
    private LazyStaticInnerClassSingleton() {
    }

    private static class LazyHolder {
        private static final LazyStaticInnerClassSingleton INSTANCE = new LazyStaticInnerClassSingleton();
    }

    public static LazyStaticInnerClassSingleton getInstance() {
        return LazyHolder.INSTANCE;
    }
}
