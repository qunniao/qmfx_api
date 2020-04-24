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
@TableName("zc_award_way")
@ApiModel(value = "奖励方式")
public class AwardWay extends Model<AwardWay> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id", value = "奖励id")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(name = "pid", value = "产品id")
	private Integer pid;

	@ApiModelProperty(name = "money", value = "奖励金额")
	private BigDecimal money;

	@ApiModelProperty(name = "percent", value = "奖励比例")
	private BigDecimal percent;

	@ApiModelProperty(name = "type", value = "奖励类型，1按金额奖励，2按比例奖励")
	private Integer type;

	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
