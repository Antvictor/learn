package top.codejouney.learn;

/**
 * @author Antvictor
 * @date 2024/5/16
 **/
public class Button {
    public final static int SEND_BUTTON = -99;

    private Dialer dialer;

    public Button(Dialer dialer) {
        this.dialer = dialer;
    }

    /**
     * 不符合开闭原则，如果需要支持新的按键，需要修改代码
     * @param token
     */
    public void press(int token) {
        switch (token) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                dialer.enterDigit(1);
                break;
            case SEND_BUTTON:
                dialer.dial();
                break;
            default:
                throw new IllegalArgumentException("Invalid token: " + token);
        }
    }
}
