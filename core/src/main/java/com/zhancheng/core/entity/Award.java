package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 奖励
 * zc_award 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:30
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_award")
@ApiModel(value = "奖励")
public class Award extends Model<Award> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id", value = "奖励id")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(name = "userId", value = "购买人id")
	private Integer userId;

	@ApiModelProperty(name = "shareId", value = "邀请人id")
	private Integer shareId;

	@ApiModelProperty(name = "orderProductId", value = "订单产品id")
	private Integer orderProductId;

	@ApiModelProperty(name = "orderAmount", value = "奖励金额")
	private BigDecimal awardMoney;


	@ApiModelProperty(name = "awardState", value = "状态：1.待结算2.待奖励3.已完成4.已失效")
	private Integer awardState;

	@ApiModelProperty(name = "gmtCreate", value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtCreate;

	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
