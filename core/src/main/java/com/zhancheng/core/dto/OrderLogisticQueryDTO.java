package com.zhancheng.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project OrderQueryDTO
 * @date 2019/11/21 11:14
 */
@Data
public class OrderLogisticQueryDTO {

    @ApiModelProperty(name = "orderId", value = "订单id")
    private Integer orderId;

    @ApiModelProperty(name = "orderProductId", value = "订单产品id拼接字符串")
    private String orderProductId;

    @ApiModelProperty(name = "deliveryWay", value = "配送方式1.快递2.无需物流3.自提")
    private Integer deliveryWay;

    @ApiModelProperty(name = "expressCompany", value = "快递公司")
    private String expressCompany;

    @ApiModelProperty(name = "expressNumber", value = "快递公司编号")
    private String expressNumber;

    @ApiModelProperty(name = "logisticCode", value = "快递单号")
    private String logisticCode;

    @ApiModelProperty(name = "logisticState", value = "物流状态：0正在运输中，1已签收")
    private Integer logisticState;

    @ApiModelProperty(name = "logisticTrace", value = "物流跟踪信息json")
    private String logisticTrace;

    @ApiModelProperty(name = "sendType", value = "发货类型：0统一发货，1分别发货")
    private Integer sendType;

    @ApiModelProperty(name = "contactName", value = "联系人")
    private String contactName;

    @ApiModelProperty(name = "contactPhone", value = "联系电话")
    private String contactPhone;

    @ApiModelProperty(name = "contactAddress", value = "收货地址")
    private String contactAddress;
}
