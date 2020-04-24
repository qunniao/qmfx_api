package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 订单
 * zc_order 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_order")
@ApiModel(value = "订单")
public class Order extends Model<Order> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id", value = "订单id")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(name = "userId", value = "用户id")
	private Integer userId;


	@ApiModelProperty(name = "orderNumber", value = "订单号")
	private String orderNumber;

	@ApiModelProperty(name = "contactName", value = "联系人")
	private String contactName;

	@ApiModelProperty(name = "contactPhone", value = "联系电话")
	private String contactPhone;

	@ApiModelProperty(name = "contactAddress", value = "收货地址")
	private String contactAddress;

	@ApiModelProperty(name = "payTime", value = "支付时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date payTime;

	@ApiModelProperty(name = "freight", value = "运费")
	private BigDecimal freight;

	@ApiModelProperty(name = "discount", value = "优惠金额")
	private BigDecimal discount;

	@ApiModelProperty(name = "totalPrice", value = "总金额")
	private BigDecimal totalPrice;

	@ApiModelProperty(name = "payNumber", value = "第三方支付单号")
	private String payNumber;

	@ApiModelProperty(name = "payMoney", value = "实付金额")
	private BigDecimal payMoney;

	@ApiModelProperty(name = "payWay", value = "支付方式：1.微信2.余额")
	private Integer payWay;

	@ApiModelProperty(name = "remark", value = "备注留言")
	private String remark;

	@ApiModelProperty(name = "deliveryWay", value = "配送方式1.快递2.无需物流3.自提")
	private Integer deliveryWay;


	@ApiModelProperty(name = "sendTime", value = "发货时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date sendTime;

	@ApiModelProperty(name = "receivingTime", value = "收货时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date receivingTime;

	@ApiModelProperty(name = "orderState", value = "订单状态：-1.已删除0.已关闭1.待付款2.待发货3.待收货4.待评价5.已完成")
	private Integer orderState;

	@ApiModelProperty(name = "isDeleted", value = "是否删除 0:未删除; 1:删除 ")
	@TableLogic
	private Integer isDeleted;

	@ApiModelProperty(name = "gmtCreate", value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtCreate;

	@ApiModelProperty(name = "gmtModified", value = "修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtModified;

	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
