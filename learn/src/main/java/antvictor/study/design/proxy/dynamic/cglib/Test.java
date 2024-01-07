package antvictor.study.design.proxy.dynamic.cglib;

/**
 * @author Antvictor
 * @date 2024/1/7
 **/
public class Test {
    public static void main(String[] args) {
        Secretary secretary = new Secretary();
        Boss boss = (Boss) secretary.getInstance(Boss.class);
        boss.meet();
        Boss boss2 = new Boss();
        boss2.meet();
    }
}
