package com.tdchain.spring.state.constant;

/**
 * @author Antvictor
 * @date 2024/3/2
 **/
public enum OrderEvent {

    PAY("支付"),
    DELIVER("发货"),
    RECEIVE("收货"),
    REFUND("退款"),
    CANCEL("取消"),
    CLOSE("关闭"),
    FINISH("完成"),
    ;
    private String name;
    OrderEvent(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
