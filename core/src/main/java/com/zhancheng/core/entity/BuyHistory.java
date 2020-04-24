package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 商品购买记录
 * zc_buy_history 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:30
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_buy_history")
@ApiModel(value = "商品购买记录")
public class BuyHistory extends Model<BuyHistory> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id", value = "商品购买记录id")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(name = "userId", value = "用户id")
	private Integer userId;

	@ApiModelProperty(name = "productId", value = "商品id")
	private Integer productId;

	@ApiModelProperty(name = "buyNum", value = "购买数量")
	private Integer buyNum;

	@ApiModelProperty(name = "awardNum", value = "剩余奖励商品数量")
	private Integer awardNum;

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
