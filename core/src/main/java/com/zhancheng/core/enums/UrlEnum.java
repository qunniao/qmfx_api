package com.zhancheng.core.enums;

/**
 * @author BianShuHeng
 * @decription
 * @project UrlEnum
 * @date 2019/9/25 10:30
 */

public enum UrlEnum {
    /**
     * 获取sessionId和 openId
     */
    JS_CODE2_SESSION("https://api.weixin.qq.com/sns/jscode2session"),
    GET_ACCESS_TOKEN("https://api.weixin.qq.com/sns/oauth2/access_token"),
    GET_USER_INFO("https://api.weixin.qq.com/sns/userinfo")

    ;
    /**
     * url
     */
    private String url;

    UrlEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
