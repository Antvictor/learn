package top.codejouney.learn;

/**
 * 1.0.3 虽然通过策略模式和适配器模式实现了解耦，但是仍然存在问题，
 * 比如，在按键的时候可能存在操作多种设备的情况，比如按键的时候同时有不同的声音、震动等。
 * 那么可以使用观察者模式来实现。
 * @author Antvictor
 * @date 2024/5/16
 **/
public interface ButtonListen {
    void buttonPress(int token);
}
