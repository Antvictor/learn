package antvictor.study.design.proxy.dynamic.jdk;

/**
 * @author Antvictor
 * @date 2024/1/7
 **/
public class Test {
    public static void main(String[] args) {
        Secretary secretary = new Secretary();
        Member boss = secretary.getInstance(new Boss());
        boss.meet();
    }
}
