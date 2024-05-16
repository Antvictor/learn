package top.codejouney.learn;

import java.util.LinkedList;
import java.util.List;

/**
 * 1.0.1 不符合开闭原则，如果需要支持新的按键，需要修改代码
 * 1.0.2 使用策略模式，通过传入不同的策略，实现不同的按键功能，符合开闭原则
 * 1.0.3 使用观察者模式
 * 1.0.4 如果在按键的是否需要记录信息，变更状态，那么可能又需要在press()里面switch了，那么怎么办呢？
 * 可以使用模版方法模式，在Button中定义一个抽象方法，并在press中调用，press就相当于一个模版，对应类型的按键可以实现抽象方法
 * @author Antvictor
 * @date 2024/5/16
 **/
public  abstract class Button {
    private int number;
    public final static int SEND_BUTTON = -99;

    private List<ButtonListen> buttonListeners = new LinkedList<>();

    public Button(int number) {
        this.number = number;
    }

    public void addListener(ButtonListen buttonListen) {
        buttonListeners.add(buttonListen);
    }

    /**
     * 1.0.1 不符合开闭原则，如果需要支持新的按键，需要修改代码
     * 1.0.2 使用策略模式，通过传入不同的策略，实现不同的按键功能，符合开闭原则
     */
    public void press() {
        onPress();
        for (ButtonListen buttonListen :
                buttonListeners) {
            buttonListen.buttonPress(this.number);
        }
    }
    abstract void onPress();
}
