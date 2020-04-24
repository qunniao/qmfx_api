package com.zhancheng.core.dto;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class UserBandCardDTO {

	@ApiModelProperty(name = "id", value = "id")
	private Integer id;

	@ApiModelProperty(name = "userId", value = "用户id")
	private Integer userId;

	@ApiModelProperty(name = "cardType", value = "卡类型，1支付宝，2银行卡，3微信号")
	private Integer cardType;

	@ApiModelProperty(name = "cardNumber", value = "卡号")
	private String cardNumber;


	@ApiModelProperty(name = "realName", value = "真实姓名")
	private String realName;

	@ApiModelProperty(name = "tixian", value = "是否可提现，1可提现，0不可提现")
	private Integer tixian;

	@ApiModelProperty(name = "band", value = "银行代码")
	private String band;

}
