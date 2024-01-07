package antvictor.study.design.proxy.hard;

/**
 * @author Antvictor
 * @date 2024/1/7
 **/
public class Test {
    public static void main(String[] args) {
        Secretary secretary = new Secretary(new Boss());
        secretary.meet();
    }
}
