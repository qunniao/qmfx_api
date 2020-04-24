package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 商品
 * zc_product 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_product")
@ApiModel(value = "商品")
public class Product extends Model<Product> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "pid", value = "商品id")
	@TableId(value = "pid", type = IdType.AUTO)
	private Integer pid;

	@ApiModelProperty(name = "typeId", value = "商品类型id")
	private Integer typeId;

	@ApiModelProperty(name = "productNumber", value = "商品货号")
	private String productNumber;

	@ApiModelProperty(name = "title", value = "商品名称")
	private String title;

	@ApiModelProperty(name = "cover", value = "商品主图")
	private String cover;

	@ApiModelProperty(name = "video", value = "商品介绍视频")
	private String video;

	@ApiModelProperty(name = "productIntro", value = "商品简介")
	private String productIntro;

	@ApiModelProperty(name = "marketPrice", value = "市场价")
	private BigDecimal marketPrice;

	@ApiModelProperty(name = "price", value = "售价")
	private BigDecimal price;

	@ApiModelProperty(name = "stockPrice", value = "进货价")
	private BigDecimal stockPrice;

	@ApiModelProperty(name = "sales", value = "基础销量：系统虚拟的销量基数")
	private Integer sales;

	@ApiModelProperty(name = "flowSales", value = "流水销量，提交订单后增加")
	private Integer flowSales;

	@ApiModelProperty(name = "realSales", value = "实际销量，确认收货后增加，退换货减少")
	private Integer realSales;

	@ApiModelProperty(name = "totalStock", value = "库存数量，发货后减少")
	private Integer totalStock;

	@ApiModelProperty(name = "detailMobile", value = "手机端详情")
	private String detailMobile;

	@ApiModelProperty(name = "isRecommend", value = "推荐 1.是0.否")
	private Integer isRecommend;

	@ApiModelProperty(name = "status", value = "状态：1.上架中 -1.已下架")
	private Integer status;

	@ApiModelProperty(name = "isDeleted", value = "是否删除 0:未删除; 1:删除 ")
	@TableLogic
	private Integer isDeleted;

	@ApiModelProperty(name = "gmtCreate", value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtCreate;

	@ApiModelProperty(name = "gmtModified", value = "修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtModified;

	@ApiModelProperty(name = "productImage", value = "产品轮播图")
	@TableField(exist = false)
	private List<ProductImage> productImageList;

	/**
	 * 零售运费模板id
	 */
	private Integer retailFreightId;

	/**
	 * 代理运费模板id
	 */
	private Integer agentFreightId;


	@Override
	protected Serializable pkVal() {
		return this.pid;
}
}
