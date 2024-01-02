package antvictor.study.design.singleton;

import java.io.Serializable;

/**
 * @author Antvictor
 * @date 2023/12/21
 **/
public class SerializableSingleton implements Serializable {
    private SerializableSingleton() {
    }

    private static class SingletonHolder {
        private static final SerializableSingleton INSTANCE = new SerializableSingleton();
    }

    public static SerializableSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }


    private Object readResolve() {
        return getInstance();
    }
}
