package com.zhancheng.applet.service;

import java.util.Map;

/**
 * @author BianShuHeng
 * @decription
 * @project WxPayService
 * @date 2019/11/8 17:36
 */
public interface WxPayService {

   /**
    * 微信支付
    * @param orderId
    * @param uid
    * @return
    * @throws Exception
    */
   Map<String, String> agentWxPay(Integer orderId, Integer uid) throws Exception;

   /**
    * 微信支付回调
    * @param notifyXml 回调信息
    * @return
    */
   String wxPayUnifiedNotify(String notifyXml);

   /**
    * 微信统一下单
    *
    * @param orderNumber 订单号
    * @param uid         用户id
    * @return
    */
   Map<String, String> wxPay(String orderNumber, Integer uid) throws Exception;
}
