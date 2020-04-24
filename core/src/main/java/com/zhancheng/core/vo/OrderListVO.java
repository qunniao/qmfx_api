package com.zhancheng.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhancheng.core.entity.OrderProduct;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project OrderListVO
 * @date 2019/11/21 11:51
 */
@Data
public class OrderListVO {

    @ApiModelProperty(name = "id", value = "订单id")
    private Integer id;
    @ApiModelProperty(name = "nickname", value = "用户昵称")
    private String nickname;
    @ApiModelProperty(name = "userId", value = "用户id")
    private Integer userId;
    @ApiModelProperty(name = "orderNumber", value = "订单号")
    private String orderNumber;
    @ApiModelProperty(name = "gmtCreate", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;
    @ApiModelProperty(name = "payTime", value = "支付时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date payTime;
    @ApiModelProperty(name = "payMoney", value = "实付金额")
    private BigDecimal payMoney;
    @ApiModelProperty(name = "freight", value = "运费")
    private BigDecimal freight;
    @ApiModelProperty(name = "discount", value = "优惠金额")
    private BigDecimal discount;
    @ApiModelProperty(name = "totalPrice", value = "总金额")
    private BigDecimal totalPrice;
    @ApiModelProperty(name = "orderState", value = "订单状态：-1.已删除0.已关闭1.待付款2.待发货3.待收货4.已完成 5.退款中6.退款完成")
    private Integer orderState;
    @ApiModelProperty(name = "num", value = "商品数量")
    private Integer num;

    @ApiModelProperty(name = "contactName", value = "联系人")
    private String contactName;
    @ApiModelProperty(name = "contactPhone", value = "手机号")
    private String contactPhone;
    @ApiModelProperty(name = "contactAddress", value = "收货地址")
    private String contactAddress;

    @ApiModelProperty(name = "orderProductList", value = "订单商品")
    private List<OrderProductVO> orderProductList ;

    @ApiModelProperty(name = "orderLogisticVOList", value = "订单物流列表")
    private List<OrderLogisticVO> orderLogisticVOList;
}
