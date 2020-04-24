package com.zhancheng.core.vo;

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
 * 奖励
 * zc_award 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:30
 */
@Data
public class AwardVO{


	@ApiModelProperty(name = "id", value = "奖励id")
	private Integer id;

	@ApiModelProperty(name = "userId", value = "购买人id")
	private Integer userId;

	@ApiModelProperty(name = "shareId", value = "奖励人id")
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

	@ApiModelProperty(name = "orderProductVO", value = "订单相关产品")
	private OrderProductVO orderProductVO;

	@ApiModelProperty(name = "productListVO", value = "产品")
	private ProductListVO productListVO;

	@ApiModelProperty(name = "userListVO", value = "购买用户")
	private UserListVO userListVO;

	@ApiModelProperty(name = "shareName", value = "奖励人昵称")
	private String shareName;

}
