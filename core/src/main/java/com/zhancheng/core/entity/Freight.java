package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * zc_freight 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-24 10:18:14
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_freight")
public class Freight extends Model<Freight> {

	private static final long serialVersionUID = 1L;

	/**
	 * 运费模板id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 模版名称
	 */
	private String title;

	/**
	 * 计价方式1.按重量2.按件数
	 */
	private Integer valuationWay;

	/**
	 * 默认采购运费1.是0.否
	 */
	private Integer defaultStock;

	/**
	 * 默认内购运费1.是0.否
	 */
	private Integer defaultBuyins;

	/**
	 * 默认提货运费1.是0.否
	 */
	private Integer defaultTake;

	/**
	 * 默认零售运费1.是0.否
	 */
	private Integer defaultRetail;

	/**
	 * 运送方式（默认）
	 */
	private String transportWay;

	/**
	 * 是否删除 0:未删除; 1:删除
	 */
	@TableLogic
	private Integer isDeleted;

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

	@TableField(exist = false)
	private List<FreightRegion> freightRegionList;

	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
