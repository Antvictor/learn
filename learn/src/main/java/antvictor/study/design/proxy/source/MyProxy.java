package antvictor.study.design.proxy.source;

/**
 * @author Antvictor
 * @date 2024/1/10
 **/
public class MyProxy {
    private static final String ln = "\r\n";
    public static Object newProxyInstance(ClassLoader classLoader,
                                             Class<?>[] interfaces,
                                          MyInvocationHandler h) {
        // 1. 动态生成源码
        String source = generateSource(interfaces, h);
        // 2. 输出到磁盘
        // 3. 编译成class文件
        // 4. 加载到JVM
        // 5. 通过字节码重组返回新的代理对象
        return null;
    }

    private static String generateSource(Class<?>[] interfaces, MyInvocationHandler h) {
        StringBuffer sb = new StringBuffer();
        String interName = interfaces[0].getName();
        String pkg = interName.substring(0, interName.lastIndexOf('.') + 1);
        sb.append(pkg + "$Proxy" + ln);
        
        return null;
    }
}
