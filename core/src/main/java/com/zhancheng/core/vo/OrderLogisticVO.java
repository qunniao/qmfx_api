package com.zhancheng.core.vo;

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
import java.util.Date;
import java.util.List;

/**
 * 订单退换货
 * zc_order 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Data
public class OrderLogisticVO{

	@ApiModelProperty(name = "id", value = "id")
	private Integer id;

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



	@ApiModelProperty(name = "isDeleted", value = "是否删除 0:未删除; 1:删除 ")
	private Integer isDeleted;

	@ApiModelProperty(name = "gmtCreate", value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtCreate;

	@ApiModelProperty(name = "gmtModified", value = "修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtModified;

	@ApiModelProperty(name = "orderProductList", value = "订单商品")
	private List<OrderProductVO> orderProductList ;
}
