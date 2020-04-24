package com.zhancheng.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhancheng.core.entity.AgentLevelProductAward;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class AgentLevelProductAwardListVO {

	@ApiModelProperty(name = "pid", value = "产品id")
	private Integer pid;
	@ApiModelProperty(name = "title", value = "产品名称")
	private String title;
	@ApiModelProperty(name = "cover", value = "产品主图")
	private String cover;
	@ApiModelProperty(name = "price", value = "产品价格")
	private BigDecimal price;

	@ApiModelProperty(name = "agentPrice", value = "代理价格")
	private BigDecimal agentPrice;

	@ApiModelProperty(name = "awardMoney", value = "佣金金额")
	private BigDecimal awardMoney;
}
