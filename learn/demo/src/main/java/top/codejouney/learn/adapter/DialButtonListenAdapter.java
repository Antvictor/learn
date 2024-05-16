package top.codejouney.learn.adapter;

import top.codejouney.learn.ButtonListen;
import top.codejouney.learn.Dialer;

/**
 * 1.0.2 适配器模式，Button调用ButtonService，然而Dialer没有对应的接口怎么办？那么就使用适配器，适配器实现ButtonService
 * 然后在实现接口中调用Dialer。
 *
 * @author Antvictor
 * @date 2024/5/16
 **/
public class DialButtonListenAdapter implements ButtonListen {

    private Dialer dialer;

    public DialButtonListenAdapter(Dialer dialer) {
        this.dialer = dialer;
    }

    @Override
    public void buttonPress(int token) {
        dialer.dial();
    }
}
