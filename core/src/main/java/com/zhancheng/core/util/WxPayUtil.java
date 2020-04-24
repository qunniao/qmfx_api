package com.zhancheng.core.util;

import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.zhancheng.core.config.wx.WxConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BianShuHeng
 * @decription
 * @project WxPayUtil
 * @date 2019/9/25 16:58
 */
@Component
public class WxPayUtil {

    @Resource
    private WxConfig wxConfig;

    @Resource
    private HttpServletRequest request;

    /**
     * @param body       商品描述
     * @param outTradeMo 商户订单号
     * @param attach     附加信息
     * @param totalFee   总金额
     * @param openId     openId
     * @return
     */
    public Map<String, String> unifiedorder(String body, String outTradeMo, String attach, String totalFee, String openId) throws Exception {
        Map<String, String> map = new HashMap<>(16);

        // 小程序ID
        map.put("appid", wxConfig.getAppID());
        // 商户ID
        map.put("mch_id", wxConfig.getMchID());
        // 生成随机字符串
        map.put("nonce_str", IdUtil.simpleUUID());

        // 商品描述
        map.put("body", body);
        // 商户订单号
        map.put("out_trade_no", outTradeMo);
        // 总金额
        map.put("total_fee", totalFee);

        map.put("openid", openId);
        // 终端ip
        map.put("spbill_create_ip", ServletUtil.getClientIP(request));
        // 回调地址
        map.put("notify_url", wxConfig.getNotifyURL());
        // 交易类型
        map.put("trade_type", wxConfig.getTradeType());
        // 附加数据
        map.put("attach", attach);
        // 签名
        map.put("sign", WXPayUtil.generateSignature(map, wxConfig.getKey()));

        System.err.println(map);

        // 请求API返回的数据
        WXPay wxPay = new WXPay(wxConfig);
        Map<String, String> resp = wxPay.unifiedOrder(map);

        System.err.println(resp);
        System.out.println(resp.toString());
        Map<String, String> result = new HashMap<>();
        if ("SUCCESS".equals(resp.get("return_code")) && "SUCCESS".equals(resp.get("result_code"))) {
            if (WXPayUtil.isSignatureValid(resp, wxConfig.getKey())) {
                result.put("appId", resp.get("appid"));
                result.put("nonceStr", IdUtil.simpleUUID());
                result.put("package", "prepay_id=" + resp.get("prepay_id"));
                result.put("signType", "MD5");
                result.put("timeStamp", System.currentTimeMillis() / 1000 + "");
                String sign = WXPayUtil.generateSignature(result, wxConfig.getKey());
                System.err.println("---------------------------------------------------");
                System.err.println(sign);
                result.put("status", "SUCCESS");
                result.put("sign", sign);
                return result;
            } else {
                throw new RuntimeException("统一下单校验签名失败！");
            }
        } else {
            result.put("status", "FAIL");
            result.put("message", resp.get("return_msg"));
            return result;
        }

    }

    /**
     * 微信订单退款
     */

}
