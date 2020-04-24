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
 * 代理等级
 * zc_agent_level 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:30
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_agent_level")
@ApiModel(value = "代理等级")
public class AgentLevel extends Model<AgentLevel> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id", value = "奖励id")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(name = "level", value = "等级")
	private String level;

	@ApiModelProperty(name = "nick", value = "等级别名")
	private String nick;

	@ApiModelProperty(name = "stockDiscount", value = "进货折扣比例")
	private BigDecimal stockDiscount;

	@ApiModelProperty(name = "awardPercent", value = "奖励比例")
	private BigDecimal awardPercent;


	@ApiModelProperty(name = "minStock", value = "最低进货金额")
	private BigDecimal minStock;

	@ApiModelProperty(name = "gmtCreate", value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtCreate;

	@ApiModelProperty(name = "levelInt", value = "等级整数")
	private Integer levelInt;

	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
