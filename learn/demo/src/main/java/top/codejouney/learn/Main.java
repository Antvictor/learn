package top.codejouney.learn;

/**
 * @author Antvictor
 * @date 2024/5/16
 **/
public class Main {
    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.dialButtons[1].press();
        phone.dialButtons[1].press();
        phone.dialButtons[9].press();
        phone.sendButton.press();
    }
}