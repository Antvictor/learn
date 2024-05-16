package top.codejouney.learn;

/**
 * @author Antvictor
 * @date 2024/5/16
 **/
public class Button {
    public final static int SEND_BUTTON = -99;

    private ButtonService buttonService;

    public Button(ButtonService buttonService) {
        this.buttonService = buttonService;
    }

    /**
     * 1.0.1 不符合开闭原则，如果需要支持新的按键，需要修改代码
     * 1.0.2 使用策略模式，通过传入不同的策略，实现不同的按键功能，符合开闭原则
     * @param token
     */
    public void press(int token) {
        buttonService.buttonPress(token);
    }
}
