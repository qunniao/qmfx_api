package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 订单转入云库存记录
 * zc_order_product 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_order_cloud_product_history")
@ApiModel(value = "订单转入云库存记录")
public class OrderCloudProductHistory extends Model<OrderCloudProductHistory> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id", value = "订单商品id")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(name = "userId", value = "用户id")
	private Integer userId;

	@ApiModelProperty(name = "orderId", value = "订单id")
	private Integer orderId;

	@ApiModelProperty(name = "note", value = "备注")
	private String note;


	@ApiModelProperty(name = "gmtCreate", value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtCreate;


	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
