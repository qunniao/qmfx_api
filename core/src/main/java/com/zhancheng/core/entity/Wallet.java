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
 * 钱包
 * zc_wallet 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_wallet")
@ApiModel(value = "钱包")
public class Wallet extends Model<Wallet> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id", value = "钱包id")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(name = "userId", value = "用户id")
	private Integer userId;

	@ApiModelProperty(name = "balance", value = "余额")
	private BigDecimal balance;


	@ApiModelProperty(name = "waitBalance", value = "待结算")
	private BigDecimal waitBalance;

	@ApiModelProperty(name = "password", value = "支付密码（6位数字）")
	private String password;

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
