package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 代理等级对应的产品佣金金额
 * zc_admin 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_agent_level_product_award")
@ApiModel(value = "代理等级对应的产品佣金金额")
public class AgentLevelProductAward extends Model<AgentLevelProductAward> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id", value = "id")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(name = "agentLevelId", value = "代理等级表id")
	private Integer agentLevelId;

	@ApiModelProperty(name = "productId", value = "产品id")
	private Integer productId;

	@ApiModelProperty(name = "awardMoney", value = "佣金金额")
	private BigDecimal awardMoney;

	@ApiModelProperty(name = "awardPercent", value = "佣金比例")
	private BigDecimal awardPercent;

	@ApiModelProperty(name = "type", value = "类型.0按佣金金额，1按佣金比例 ")
	private Integer type;

	@ApiModelProperty(name = "state", value = "状态 0.关闭1.开启 ")
	private Integer state;

	@ApiModelProperty(name = "agentPrice", value = "代理价格")
	private BigDecimal agentPrice;

	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
