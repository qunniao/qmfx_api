package com.zhancheng.core.enums;

/**
 * @author BianShuHeng
 * @decription
 * @project SmsEnum
 * @date 2019/11/15 16:27
 */
public enum SmsEnum {

    SMS_CXZS("SMS_161295123","创新卓顺")
    ;

    private String templateCode;
    private String sign;

    SmsEnum(String templateCode, String sign) {
        this.templateCode = templateCode;
        this.sign = sign;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public String getSign() {
        return sign;
    }
}
