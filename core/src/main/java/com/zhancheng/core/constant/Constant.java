package com.zhancheng.core.constant;

/**
 * 常量
 *
 * @author tangchao
 */
public interface Constant {
    /**
     * 佣金状态
     */
    int AWARD_STATE_DAIJIESUAN = 1;//待结算
    int AWARD_STATE_FINISH = 3;//已完成
    int AWARD_STATE_FAIL = 4;//已失效
    /**
     * 游客用户权限(没有验证)
     */
    String NOT = "-1";
    /**
     * 一般用户权限
     */
    String ORDINARY = "1";

    int WalletHistoryType_In = 1;//钱包收入
    int WalletHistoryType_Out = 0;//钱包支出

    int WalletHistoryTradeType_Invite = 1;//推荐奖励
    int WalletHistoryTradeType_Share = 2;//分享佣金
    int WalletHistoryTradeType_Transfer = 3;//转账

    /**
     * 订单退货退款状态
     */
    int OrderRefundStateNoProduct = 0;//未收到货
    int OrderRefundStateHasProduct = 1;//已收到货
    int RefundStateFail=0;//退款失败
    int RefundStateWait = 1;//等待商家回应
    int RefundStateService=2;//商家正在处理
    int RefundStateSuccess=4;//退款成功
    int RefundStateCancel = 5;//用户已撤销退款
    int RefundStateRefunding = 6;//退款申请成功，请等待

    //订单状态
    int OrderStateClose = 0;//已关闭
    int OrderStateWaitPay = 1;//待支付
    int OrderStateWaitSend = 2;//待发货
    int OrderStateWaitConfirm = 3;//待收货
    int OrderStateSuccess = 4;//交易完成
    int OrderStateRefund = 5;//退款中
}
