package com.zhancheng.core.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.config.wx.WxConfig;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.enums.UrlEnum;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BianShuHeng
 * @decription
 * @project WxUtil
 * @date 2019/9/24 18:29
 */
@Component
public class WxUtil {

    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private HttpServletRequest request;

    Logger logger = LoggerFactory.getLogger(WxUtil.class);

    /**
     * 用户登录
     *
     * @param code          前端登录code
     * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据
     * @param iv            加密算法的初始向量
     * @return 返回用户信息 nickName,avatarUrl,gender,unionid,city,province,country,openid
     * @throws Exception
     */
    public JSONObject loginByWeiXin(String code, String encryptedData, String iv) throws Exception {

        //根据code去调用接口获取用户openid和session_key
        JSONObject json = getSessionKeyAndOpenid(code);
        System.out.println("返回过来的json数据:" + json.toString());
        //会话秘钥
        String sessionKey = json.get("session_key").toString();
        //用户唯一标识
        String openid = json.get("openid").toString();

        //拿到用户session_key和用户敏感数据进行解密，拿到用户信息。
        String decrypts = decrypt(encryptedData, sessionKey, iv, "utf-8");
        JSONObject jsons = JSONUtil.parseObj(decrypts);
        jsons.put("openid", openid);
        return jsons;
    }

    /**
     * 通过code获取sessionKey 和 openid
     *
     * @param code
     * @return sessionKey, openid
     */
    public JSONObject getSessionKeyAndOpenid(String code) {

        String url = UrlEnum.JS_CODE2_SESSION.getUrl();

        Map<String, Object> map = new HashMap<>();
        map.put("appid", wxConfig.getAppID());
        map.put("secret", wxConfig.getSecret());
        map.put("js_code", code);
        map.put("grant_type", wxConfig.getGrantType());
        HttpRequest.get(url).form(map);
        HttpRequest httpRequest = new HttpRequest(url).form(map);
        String body = httpRequest.execute().body();

        JSONObject jsonObject = JSONUtil.parseObj(body);

        String openid = jsonObject.getStr("openid");
        if (StrUtil.isBlank(openid)) {
            System.err.println(jsonObject);
            throw new MyException(CodeMsg.WE_CHAT_CODE_ERROR);
        }
        return jsonObject;
    }

    /**
     * 获取AccessToken和OpenId
     * @param code
     * @return
     */
    public JSONObject getAccessToken(String code) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("appid", wxConfig.getAppID());
        paramMap.put("secret",wxConfig.getSecret());
        paramMap.put("code", code);
        paramMap.put("grant_type", "authorization_code");

        String accessToken = HttpUtil.get(UrlEnum.GET_ACCESS_TOKEN.getUrl(), paramMap);
        JSONObject jsonObject = JSONUtil.parseObj(accessToken);
        String errcode = jsonObject.getStr("errcode");
        if (ObjectUtil.isNotNull(errcode) && StrUtil.isNotBlank(errcode)){
            System.err.println(errcode);
            logger.error("获取accessToken失败{}", errcode);
            throw new MyException(CodeMsg.ACCESS_TOKEN_ERROR);
        }

        return jsonObject;
    }

    /**
     * 获取AccessToken和OpenId
     * @param openId
     * @param accessToken
     * @return
     */
    public JSONObject getUserInfo(String openId, String accessToken) {

        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("access_token", accessToken);
        paramMap.put("openid", openId);
        paramMap.put("lang", "zh_CN");

        String userInfo = HttpUtil.get("https://api.weixin.qq.com/sns/userinfo", paramMap);

        return JSONUtil.parseObj(userInfo);
    }

    /**
     * AES解密
     *
     * @param data           包括敏感数据在内的完整用户信息的加密数据，
     * @param key            秘钥
     * @param iv             加密算法的初始向量，
     * @param encodingFormat 解密后的结果需要进行的编码
     * @return String
     * @see Exception
     */
    public String decrypt(String data, String key, String iv, String encodingFormat) throws Exception {
//        initialize();

        //被加密的数据
        byte[] dataByte = Base64.decodeBase64(data);
        //加密秘钥
        byte[] keyByte = Base64.decodeBase64(key);
        //偏移量
        byte[] ivByte = Base64.decodeBase64(iv);


        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");

            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, encodingFormat);
                return result;
            }
            return null;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidParameterSpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 统一下单方法
     *
     * @param body       商品描述
     * @param attach     回传参数 该字段主要用于商户携带订单的自定义数据
     * @param outTradeNo 商户系统内部订单号
     * @param totalFee   订单总金额，单位为分
     * @param openid     微信 id
     * @return APP 发起支付需要的参数
     * @throws Exception ex
     */
    public Map<String, String> unifiedOrder(final String body, final String attach, final String outTradeNo, final String totalFee, final String openid) throws Exception {
        Map<String, String> data = new HashMap<>(13);
        data.put("appid", wxConfig.getAppID());
        data.put("mch_id", wxConfig.getMchID());
        //随机字符串
        data.put("nonce_str", WXPayUtil.generateNonceStr());
        //商品描述
        data.put("body", body);
        // 该字段主要用于商户携带订单的自定义数据
        data.put("attach", attach);
        //订单号
        data.put("out_trade_no", outTradeNo);
        //标价金额
        data.put("total_fee", totalFee);
        //用户openid
        data.put("openid", openid);
        //客户端IP
        data.put("spbill_create_ip", getIpAddr());
        //回调地址
        data.put("notify_url", wxConfig.getNotifyURL());
        //交易类型JSAPI
        data.put("trade_type", "JSAPI");
        data.put("sign", WXPayUtil.generateSignature(data, wxConfig.getKey()));
        // 请求API返回的数据
        WXPay wxPay = new WXPay(wxConfig);
        Map<String, String> resp = wxPay.unifiedOrder(data);
        System.out.println(resp.toString());
        Map<String, String> result = new HashMap<>();
        if (resp.get("return_code").equals("SUCCESS") && resp.get("result_code").equals("SUCCESS")) {
            if (WXPayUtil.isSignatureValid(resp, wxConfig.getKey())) {
                result.put("appId", resp.get("appid"));
                result.put("nonceStr", WXPayUtil.generateNonceStr());
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
     * 获取用户真实ip
     *
     * @return
     */
    public String getIpAddr() {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }
}
