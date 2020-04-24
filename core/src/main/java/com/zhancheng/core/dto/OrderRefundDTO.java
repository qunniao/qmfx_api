package com.zhancheng.core.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单退换货
 * zc_order 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Data
public class OrderRefundDTO{


	@ApiModelProperty(name = "orderId", value = "订单id")
	private Integer orderId;


	@ApiModelProperty(name = "orderProductId", value = "订单产品id")
	private Integer orderProductId;

	@ApiModelProperty(name = "refundType", value = "退款类型：1只退款无需退货，2退货退款，3换货")
	private Integer refundType;

	@ApiModelProperty(name = "productState", value = "货物状态：1未收到货，2已收到货")
	private Integer productState;

	@ApiModelProperty(name = "refundWhy", value = "退款原因")
	private String refundWhy;

	@ApiModelProperty(name = "refundMoney", value = "退款金额")
	private BigDecimal refundMoney;

	@ApiModelProperty(name = "note", value = "退款说明")
	private String note;

	@ApiModelProperty(name = "pic1", value = "凭证图片1")
	private String pic1;

	@ApiModelProperty(name = "pic2", value = "凭证图片2")
	private String pic2;

	@ApiModelProperty(name = "pic3", value = "凭证图片3")
	private String pic3;

	@ApiModelProperty(name = "refundWay", value = "退货方式：1自行寄回，2上门取件")
	private Integer refundWay;

	@ApiModelProperty(name = "refundDeliveryNumber", value = "退货的快递单号")
	private String refundDeliveryNumber;

	@ApiModelProperty(name = "refundDeliveryCompany", value = "退货的快递公司名称")
	private String refundDeliveryCompany;

	@ApiModelProperty(name = "refundState", value = "退货状态：0退款失败，1等待商家回应，2正在退款中，3正在退货中，4退款成功,5用户已撤销退款")
	private Integer refundState;

	@ApiModelProperty(name = "orderState", value = "订单退款之前的状态")
	private Integer orderState;
}
