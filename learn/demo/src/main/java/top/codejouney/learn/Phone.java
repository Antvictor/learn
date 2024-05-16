package top.codejouney.learn;

/**
 * 1.0.3 将按钮和拨号器组装为手机
 * @author Antvictor
 * @date 2024/5/16
 **/
public class Phone {
    Button sendButton;
    Button[] dialButtons;

    private Dialer dialer;
    // 可以添加其他设备，如喇叭

    public Phone() {
        dialer = new Dialer();
        sendButton = new Button(Button.SEND_BUTTON);
        dialButtons = new Button[10];
        for (int i = 0; i < 10; i++) {
            dialButtons[i] = new Button(i);
            dialButtons[i].addListener(token -> {
                dialer.enterDigit(token);
                // 在此处操作喇叭
                System.out.println("喇叭声音: " + token);
            });
        }
        sendButton.addListener(token -> {
            dialer.dial();
            // 在此处操作喇叭
            System.out.println("喇叭声音: 嘟嘟嘟...");
        });
    }
}
