package com.zhancheng.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/** 代理人信息列表
 * @author BianShuHeng
 * @decription
 * @project UserListVO
 * @date 2019/11/21 17:43
 */
@Data
public class UserAgentListVO {

    @ApiModelProperty(name = "id", value = "用户id")
    private Integer id;
    @ApiModelProperty(name = "nickname", value = "用户昵称")
    private String nickname;
    @ApiModelProperty(name = "cover", value = "头像")
    private String cover;
    @ApiModelProperty(name = "gmtCreate", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    @ApiModelProperty(name = "level", value = "代理等级")
    private String level;

    @ApiModelProperty(name = "inviterName", value = "邀请人名称")
    private String inviterName;

    @ApiModelProperty(name = "balance", value = "钱包余额")
    private BigDecimal balance;

    @ApiModelProperty(name = "inviterCount", value = "总邀请人数")
    private Integer inviterCount;

    @ApiModelProperty(name = "awardMoney", value = "总奖励金额")
    private BigDecimal awardMoney;
}
