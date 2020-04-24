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
 * 邀请人层级信息
 * zc_invite_info 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 18:10:02
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_invite_info")
@ApiModel(value = "邀请人层级信息")
public class InviteInfo extends Model<InviteInfo> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id", value = "邀请人层级信息")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(name = "userId", value = "用户id")
	private Integer userId;

	@ApiModelProperty(name = "inviterId", value = "邀请人id")
	private Integer inviterId;

	@ApiModelProperty(name = "level", value = "层级")
	private Integer level;

	@ApiModelProperty(name = "orderNo", value = "邀请人序号")
	private Integer orderNo;

	@ApiModelProperty(name = "gmtCreate", value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtCreate;

	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
