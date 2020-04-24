package com.zhancheng.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author BianShuHeng
 * @decription
 * @project VisitorListVO
 * @date 2019/11/23 11:50
 */
@Data
public class VisitorListVO {

    @ApiModelProperty(name = "id", value = "访客记录id")
    private Integer id;

    @ApiModelProperty(name = "userId", value = "用户id")
    private Integer userId;

    @ApiModelProperty(name = "nickname", value = "用户昵称")
    private String nickname;

    @ApiModelProperty(name = "orderNum", value = "订单数量")
    private Integer orderNum;

    @ApiModelProperty(name = "commissionBalance", value = "佣金")
    private Integer commissionBalance;

    @ApiModelProperty(name = "gmtCreate", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;
}
