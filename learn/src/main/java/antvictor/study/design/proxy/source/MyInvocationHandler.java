package antvictor.study.design.proxy.source;

import java.lang.reflect.Method;

/**
 * @author Antvictor
 * @date 2024/1/10
 **/
public interface MyInvocationHandler {
    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;

}
