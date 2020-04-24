package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 运费地区
 * zc_freight_region 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-24 10:18:15
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_freight_region")
public class FreightRegion extends Model<FreightRegion> {

	private static final long serialVersionUID = 1L;


	/**
	 * 
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 运费模板id
	 */
	private Integer fid;

	/**
	 * 计价方式1.按重量2.按件数
	 */
	private Integer valuationWay;

	/**
	 * 类型：1.全部默认运费2.指定地区运费
	 */
	private Integer type;

	/**
	 * 大批量购买数量(kg/件) 0.不是大批量
	 */
	private Integer isLarge;

	/**
	 * 不配送/配送区域(多个地区id) 0.全部地区
	 */
	private String region;
	/**
	 * 不配送/配送区域(多个地区地址字符串)
	 */
	private String regionInfo;

	/**
	 * 首重（g大批量kg）/首件（件）
	 */
	private Integer firstNumber;

	/**
	 * 首费（元）
	 */
	private BigDecimal firstPrice;

	/**
	 * 续重（g/大批量kg）/续件（件）
	 */
	private Integer renewNumber;

	/**
	 * 续费（元）
	 */
	private BigDecimal renewPrice;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtCreate;

	/**
	 * 修改时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtModified;

	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
