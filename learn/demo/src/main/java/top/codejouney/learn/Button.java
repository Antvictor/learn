package top.codejouney.learn;

import java.util.LinkedList;
import java.util.List;

/**
 * 1.0.1 不符合开闭原则，如果需要支持新的按键，需要修改代码
 * 1.0.2 使用策略模式，通过传入不同的策略，实现不同的按键功能，符合开闭原则
 * 1.0.3 使用观察者模式
 * @author Antvictor
 * @date 2024/5/16
 **/
public class Button {
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
        for (ButtonListen buttonListen :
                buttonListeners) {
            buttonListen.buttonPress(this.number);
        }
    }
}
