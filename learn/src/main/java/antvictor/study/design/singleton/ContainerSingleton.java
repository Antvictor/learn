package antvictor.study.design.singleton;

import java.io.ObjectInputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 容器单例
 * 优点: 容器单例可以解决多线程并发问题
 * 缺点: 容器单例的性能比饿汉单例要差
 * @author Antvictor
 * @date 2023/12/20
 **/
public class ContainerSingleton {
    private final static Map<String, Object> container = new ConcurrentHashMap<>();

    public static Object getBean(String className) {
        if (container.containsKey(className)) {
            return container.get(className);
        } else {
            Object obj = null;
            try {
                obj = Class.forName(className).newInstance();
                container.put(className, obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }
    }
}
