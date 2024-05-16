package top.codejouney.learn.adapter;

import top.codejouney.learn.ButtonService;
import top.codejouney.learn.Dialer;

/**
 * @author Antvictor
 * @date 2024/5/16
 **/
public class PressButtonServiceAdapter implements ButtonService {

    private Dialer dialer;

    public PressButtonServiceAdapter(Dialer dialer) {
        this.dialer = dialer;
    }

    @Override
    public void buttonPress(int token) {
        dialer.enterDigit(token);
    }
}
