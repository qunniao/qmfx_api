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

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 提现审核
 * zc_withdraw_review 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_withdraw_review")
@ApiModel(value = "提现审核")
@Valid
public class WithdrawReview extends Model<WithdrawReview> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id", value = "提现审核id")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(name = "userId", value = "用户id")
	@NotNull(message = "用户id不能为空")
	private Integer userId;

	@ApiModelProperty(name = "trueName", value = "姓名")
	private String trueName;

	@ApiModelProperty(name = "account", value = "提现账户")
	@NotNull(message = "提现账户不能为空")
	private String account;

	@ApiModelProperty(name = "amount", value = "提现金额")
	@NotNull(message = "提现金额不能为空")
	private BigDecimal amount;

	@ApiModelProperty(name = "reviewState", value = "提现状态：0.未审核1.审核通过2.拒绝")
	private Integer reviewState;

	@ApiModelProperty(name = "refuseInfo", value = "拒绝原因")
	private String refuseInfo;

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
