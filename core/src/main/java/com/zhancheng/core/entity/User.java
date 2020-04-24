package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户
 * zc_user 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_user")
@ApiModel(value = "用户")
public class User extends Model<User> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id", value = "用户id")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(name = "openid", value = "微信id")
	@JsonIgnore
	private String openid;

	@ApiModelProperty(name = "agentLevel", value = "代理等级id")
	private Integer agentLevel;

	@ApiModelProperty(name = "nickname", value = "昵称")
	private String nickname;

	@ApiModelProperty(name = "cover", value = "头像")
	private String cover;

	@ApiModelProperty(name = "phone", value = "手机号")
	private String phone;

	@ApiModelProperty(name = "trueName", value = "姓名")
	private String trueName;

	@ApiModelProperty(name = "gender", value = "性别 1.男0.女")
	private Integer gender;

	@ApiModelProperty(name = "birth", value = "生日")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date birth;

	@ApiModelProperty(name = "region", value = "地区")
	private String region;

	@ApiModelProperty(name = "email", value = "邮箱")
	private String email;

	@ApiModelProperty(name = "inviterId", value = "邀请人id")
	private Integer inviterId;

	@ApiModelProperty(name = "inviters", value = "邀请人数")
	private Integer inviters;

	@ApiModelProperty(name = "sale", value = "总销售额")
	private BigDecimal sale;

	@ApiModelProperty(name = "remark", value = "个人简介")
	private String remark;

	@ApiModelProperty(name = "isDeleted", value = "是否删除 0:未删除; 1:删除 ")
	@TableLogic
	private Integer isDeleted;

	@ApiModelProperty(name = "gmtCreate", value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtCreate;

	@ApiModelProperty(name = "gmtModified", value = "修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtModified;

	@ApiModelProperty(name = "gmtLogin", value = "登录时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtLogin;

	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
