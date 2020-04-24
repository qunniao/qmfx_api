package com.zhancheng.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project OrderVO
 * @date 2019/11/21 11:15
 */
@Data
public class OrderVO {

    @ApiModelProperty(name = "id", value = "订单id")
    private Integer id;

    @ApiModelProperty(name = "contactName", value = "联系人")
    private String contactName;

    @ApiModelProperty(name = "contactPhone", value = "手机号")
    private String contactPhone;

    @ApiModelProperty(name = "userId", value = "用户id")
    private Integer userId;

    @ApiModelProperty(name = "orderNumber", value = "订单号")
    private String orderNumber;

    @ApiModelProperty(name = "payNumber", value = "微信交易号")
    private String payNumber;

    @ApiModelProperty(name = "deliveryWay", value = "配送方式1.快递2.无需物流3.自提")
    private Integer deliveryWay;

    @ApiModelProperty(name = "contactAddress", value = "收货地址")
    private String contactAddress;

    @ApiModelProperty(name = "totalPrice", value = "总金额")
    private BigDecimal totalPrice;

    @ApiModelProperty(name = "payTime", value = "支付时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date payTime;

    @ApiModelProperty(name = "freight", value = "运费")
    private BigDecimal freight;
    @ApiModelProperty(name = "discount", value = "优惠金额")
    private BigDecimal discount;
    @ApiModelProperty(name = "payWay", value = "支付方式：1.微信")
    private Integer payWay;

    @ApiModelProperty(name = "orderState", value = "订单状态：-1.已删除0.已关闭1.待付款2.待发货3.待收货4.已完成 5.退款中6.退款完成")
    private Integer orderState;

    @ApiModelProperty(name = "payMoney", value = "实付金额")
    private BigDecimal payMoney;

    @ApiModelProperty(name = "gmtCreate", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    @ApiModelProperty(name = "orderProductVOList", value = "订单产品")
    private List<OrderProductVO> orderProductVOList;

    @ApiModelProperty(name = "orderLogisticVOList", value = "物流信息")
    private List<OrderLogisticVO> orderLogisticVOList;
}
