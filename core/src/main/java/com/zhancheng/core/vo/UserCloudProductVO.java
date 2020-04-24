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
 * 订单商品
 * zc_order_product 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Data
public class UserCloudProductVO {

	@ApiModelProperty(name = "id", value = "id")
	private Integer id;

	@ApiModelProperty(name = "userId", value = "用户id")
	private Integer userId;


	@ApiModelProperty(name = "productId", value = "商品id")
	private Integer productId;

	@ApiModelProperty(name = "productNum", value = "商品数量")
	private Integer productNum;

	@ApiModelProperty(name = "productPrice", value = "商品价格")
	private BigDecimal productPrice;

	@ApiModelProperty(name = "gmtCreate", value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtCreate;

	@ApiModelProperty(name = "productListVO", value = "产品")
	private ProductListVO productListVO;



}
