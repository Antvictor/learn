package com.tdchain.spring.state.constant;

/**
 * @author Antvictor
 * @date 2024/3/2
 **/
public enum OrderState {
    INIT("INIT", "新建"),
    PAYED("PAYED", "已付款"),
    DELIVERED("DELIVERED", "已发货"),
    FINISHED("FINISHED", "已完成"),
    CANCELED("CANCELED", "已取消"),
    REFUNDED("REFUNDED", "已退款"),
    CLOSED("CLOSED", "已关闭");
    private String code;
    private String desc;

    OrderState(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
