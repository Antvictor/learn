package top.codejouney.learn;

/**
 * 1.0.1 虽然Button符合开闭原则了，但可以看到，在Dialer实现的接口中，switch / if, 还是不符合开闭原则
 * @author Antvictor
 * @date 2024/5/16
 **/
public class Dialer implements ButtonService{
    public void enterDigit(int digit) {
        System.out.println("enter digit: " + digit);
    }

    public void dial() {
        System.out.println("dialing...");
    }

    @Override
    public void buttonPress(int token) {
        if (token == Button.SEND_BUTTON) {
            dial();
        } else {
            enterDigit(token);
        }
    }
}
