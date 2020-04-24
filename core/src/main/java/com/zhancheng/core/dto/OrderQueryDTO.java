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
public class OrderQueryDTO {

    @ApiModelProperty(name = "orderNumber", value = "订单号")
    private String orderNumber;
    @ApiModelProperty(name = "userId", value = "用户id")
    private Integer userId;
    @ApiModelProperty(name = "orderNumber", value = "订单状态：-1.已删除0.已关闭1.待付款2.待发货3.待收货4.已完成 5.退款中6.退款完成")
    private Integer orderState;
}
