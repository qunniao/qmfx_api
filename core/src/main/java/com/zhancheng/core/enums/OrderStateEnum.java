package com.zhancheng.core.enums;

/**
 * @author BianShuHeng
 * @decription
 * @project OrderStateEnum
 * @date 2019/10/30 14:15
 */
public enum OrderStateEnum {

    DELETE(-1, "已删除"),
    CLOSE(0, "已关闭"),
    AWAIT_PAYMENT(1, "待付款"),
    AWAIT_SEND_OUT (2, "待发货"),
    AWAIT_RECEIVING(3, "待收货"),
    COMPLETED(4, "已完成"),
    REFUNDING(5, "退款中"),
    REFUND_CCOMPLETE(6, "退款完成"),
    COMPLETED_CLOUDPRODUCT(7, "已转入用户云库存")

    ;

    private int state;
    private String remark;

    OrderStateEnum(int state, String remark) {
        this.state = state;
        this.remark = remark;
    }

    public int getState() {
        return state;
    }

    public String getRemark() {
        return remark;
    }
}
