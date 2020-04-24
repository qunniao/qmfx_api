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
 * 收货地址
 * zc_user_address 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_user_address")
@ApiModel(value = "收货地址")
public class UserAddress extends Model<UserAddress> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id", value = "收货地址id")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(name = "userId", value = "用户id")
	private Integer userId;

	@ApiModelProperty(name = "contactName", value = "联系人姓名")
	private String contactName;

	@ApiModelProperty(name = "contactPhone", value = "联系人手机")
	private String contactPhone;

	@ApiModelProperty(name = "contactAddress", value = "联系人详细地址")
	private String contactAddress;

	@ApiModelProperty(name = "provinceId", value = "省id")
	private Integer provinceId;

	@ApiModelProperty(name = "cityId", value = "城市id")
	private Integer cityId;

	@ApiModelProperty(name = "countyId", value = "区县id")
	private Integer countyId;

	@ApiModelProperty(name = "city", value = "城市名")
	private String city;

	@ApiModelProperty(name = "county", value = "区县名")
	private String county;

	@ApiModelProperty(name = "isDefault", value = "是否默认地址 1.是0.否")
	private Boolean isDefault;

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
