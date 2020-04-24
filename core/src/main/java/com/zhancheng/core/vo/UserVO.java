package com.zhancheng.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author BianShuHeng
 * @decription
 * @project UserVO
 * @date 2019/11/21 17:42
 */
@Data
public class UserVO {

    @ApiModelProperty(name = "id", value = "用户id")
    private Integer id;
    @ApiModelProperty(name = "agentLevel", value = "代理等级")
    private Integer agentLevel;
    @ApiModelProperty(name = "inviterId", value = "邀请人id")
    private Integer inviterId;
    @ApiModelProperty(name = "nickname", value = "昵称")
    private String nickname;
    @ApiModelProperty(name = "cover", value = "头像")
    private String cover;
    @ApiModelProperty(name = "phone", value = "手机号")
    private String phone;
    @ApiModelProperty(name = "trueName", value = "真实姓名")
    private String trueName;
    @ApiModelProperty(name = "gender", value = "性别")
    private Integer gender;

    @ApiModelProperty(name = "birth", value = "出生年月")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birth;
    @ApiModelProperty(name = "region", value = "地区")
    private String region;
    @ApiModelProperty(name = "remark", value = "描述")
    private String remark;
}
