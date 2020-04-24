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
 * 钱包收支明细记录
 * zc_wallet_history 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-28 18:14:10
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_wallet_history")
@ApiModel(value = "钱包收支明细记录")
public class WalletHistory extends Model<WalletHistory> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id", value = "")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(name = "tradeNo", value = "交易号")
	private String tradeNo;

	@ApiModelProperty(name = "walletId", value = "钱包id")
	private Integer walletId;

	@ApiModelProperty(name = "userId", value = "用户id")
	private Integer userId;

	@ApiModelProperty(name = "tradeType", value = "交易类型：1.收入2.付款支出3.转账4.提现")
	private Integer tradeType;

	@ApiModelProperty(name = "amount", value = "交易金额")
	private BigDecimal amount;

	@ApiModelProperty(name = "balance", value = "余额")
	private BigDecimal balance;

	@ApiModelProperty(name = "tradeDesc", value = "交易描述信息")
	private String tradeDesc;

	@ApiModelProperty(name = "inOut", value = "收入还是支出,0支出，1收入")
	private Integer inOut;

	@ApiModelProperty(name = "gmtCreate", value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtCreate;

	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
