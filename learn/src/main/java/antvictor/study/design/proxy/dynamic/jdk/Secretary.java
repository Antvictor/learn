package antvictor.study.design.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Antvictor
 * @date 2024/1/7
 **/
public class Secretary implements InvocationHandler {
    private Member target;
    public Member getInstance(Member member) {
        this.target = member;
        Class<? extends Member> clazz = member.getClass();
        return (Member) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(this.target, args);
        after();
        return result;
    }

    private void after() {
        System.out.println("指挥员工打扫卫生，将重点整理后交给Boss");
    }

    private void before() {
        System.out.println("准备会议室、茶水，约定时间");
    }
}
