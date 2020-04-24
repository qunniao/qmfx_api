package com.zhancheng.core.constant;

/**
 * 返回状态的枚举
 *
 * @author Demon
 */
public enum CodeMsg {

    /**
     * 成功(所有正常状态的返回)
     */
    OK("SUCCESS", 200),
    SERVER_ERROR("Server error", 500),
    IMAGE_MISMATCHING("图片格式不匹配", 4105),
    RUNTIME_EXCEPTION("运行时异常", 4106),
    NULL_POINTER_EXCEPTION("空指针异常", 4107),
    CLASS_CAST_EXCEPTION("类型强制转换异常", 4108),
    IO_EXCEPTION("IO异常", 4109),
    CLASS_NOT_FOUND_EXCEPTION("指定的类不存在", 4110),
    ARITHMETIC_EXCEPTION("算术异常", 4111),
    ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION("数组下标越界", 4112),
    INDEX_OUT_OF_BOUNDS_EXCEPTION("索引越界", 4113),
    NEGATIVE_ARRAY_SIZE_EXCEPTION("数组负下标", 4114),
    ILLEGAL_ARGUMENT_EXCEPTION("方法的参数错误", 4115),
    ILLEGAL_ACCESS_EXCEPTION("没有访问权限", 4116),
    SECURITY_EXCEPTION("违背安全原则", 4117),
    EOF_EXCEPTION("文件已结束异常", 4118),
    FILE_NOT_FOUND_EXCEPTION("文件未找到", 4119),
    NUMBER_FORMAT_EXCEPTION("数字格式化异常", 4120),
    SQL_EXCEPTION("操作数据库异常", 4121),
    NO_SUCH_METHOD_EXCEPTION("方法未找到", 4122),
    ARRAY_STORE_EXCEPTION("数组存储异常", 4123),
    CLONE_NOT_SUPPORTED_EXCEPTION("不支持克隆", 4124),
    ENUM_CONSTANT_NOT_PRESENT_EXCEPTION("枚举常量不存在", 4125),
    INSTANTIATION_EXCEPTION("实例化异常", 4126),
    TYPE_NOT_PRESENT_EXCEPTION("类型不存在", 4127),
    MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION("缺少Servlet请求参数", 4128),
    HTTP_MESSAGE_NOT_READABLE_EXCEPTION("Http消息不可读", 4129),
    TYPE_MISMATCH_EXCEPTION("类型不匹配", 4130),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION("Http请求方法错误", 4131),
    HTTP_MEDIA_TYPE_NOT_ACCEPTABLE_EXCEPTION("Http媒体类型不可接受", 4132),
    CONVERSION_NOT_SUPPORTED_EXCEPTION("不支持转换", 4133),
    HTTP_MESSAGE_NOT_WRITABLE_EXCEPTION("Http消息不可写,参数格式错误", 4134),
    PARAM_VERIFY_EXCEPTION("参数校验异常", 4134),
    FREIGHT_NOT_EXISTED("运费模板不存在", 41019),
    FREIGHT_REGION_NOT_EXISTED("运费区域不存在", 41020),



    /**
     * 权限或第三方异常
     */

    TOKEN_IS_NULL("token为空", 41000),
    TOKEN_ABNORMAL("token 异常,请重新登录", 41001),
    PERMISSION_ERROR("权限不足可能没有登录", 41002),
    OPENID_IS_NULL("openid为空", 41003),
    ACCESS_TOKEN_ERROR("获取accessToken失败", 41004),
    WX_PAY_ERROR("微信支付失败,请联系管理员", 41005),
    ALIYUN_BUCKET_NOT_EXIST("阿里云文件上传bucket不存在 ", 41006),
    WECHAT_ERROR("微信服务器请求失败", 41007),
    WE_CHAT_CODE_ERROR("微信code无效", 41008),
    WECHAT_GET_USER_ERROR("微信获得用户信息失败", 41009),

    /**
     * 业务逻辑异常
     */
    ADMIN_IS_NULL("管理员不存在", 410001),
    USER_NOT_EXISTED("用户不存在!", 410002),
    PASSWORD_ERROR("账号或者密码错误", 410003),
    FROZEN_ACCOUNT("账号被冻结", 410004),
    PRODUCT_NOT_EXISTED("产品不存在", 410005),
    PRODUCT_TYPE_NOT_EXISTED("产品类型不存在", 410006),
    ORDER_IS_NULL("订单不存在", 410007),
    ORDER_PRODUCT_IS_NULL("订单产品为空", 410008),
    ORDER_ERROR("订单不存在或者订单状态异常", 410009),
    ORDER_NUMBER_ERROR("订单号为空", 4100010),
    PARAMETER_NULL("参数为空", 410011),
    PARAMETER_ERROR("参数错误", 410012),
    GOODS_NUM_ERROR("超过库存", 410013),
    PRODUCT_NUMBER_EXIST("产品货号重复", 410014),
    CART_NOT_EXIST("购物车不存在", 410015),
    CONFIG_INFO_ERROR("奖励配置信息错误", 410016),
    CONFIG_INFO_IS_NULL("奖励配置信息不存在", 410017),
    USER_WALLET_NOT_EXISTED("用户钱包不存在!", 410018),

    /**
     * 支付异常
     */
    WXPAY_IS_FAIL("支付失败", 420001),

    ;

    /**
     * 构造方法
     *
     * @param description 描述状态的详细信息
     * @param status      状态代码
     */
    CodeMsg(String description, int status) {
        this.description = description;
        this.status = status;
    }

    private Integer status;

    private String description;

    public Integer getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public void setDescription(String description) {

        this.description = description;
    }

}
