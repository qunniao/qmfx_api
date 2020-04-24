package com.zhancheng.core.entity;

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

/**
 * 用户银行卡
 * zc_user_address 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_user_bandcard")
@ApiModel(value = "用户银行卡")
public class UserBandCard extends Model<UserBandCard> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id", value = "id")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(name = "userId", value = "用户id")
	private Integer userId;

	@ApiModelProperty(name = "cardType", value = "卡类型，1支付宝，2银行卡，3微信号")
	private Integer cardType;

	@ApiModelProperty(name = "cardNumber", value = "卡号")
	private String cardNumber;

	@ApiModelProperty(name = "cardName", value = "卡名称，比如支付宝，中国邮政银行，中国建设银行。。。")
	private String cardName;

	@ApiModelProperty(name = "realName", value = "真实姓名")
	private String realName;

	@ApiModelProperty(name = "tixian", value = "是否可提现，1可提现，0不可提现")
	private Integer tixian;

	@ApiModelProperty(name = "bandCode", value = "银行代码")
	private String bandCode;

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
