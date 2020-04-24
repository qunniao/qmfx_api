package com.zhancheng.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author BianShuHeng
 * @decription
 * @project RewardVO
 * @date 2019/11/26 10:51
 */
@Data
public class RewardVO {

    @ApiModelProperty(name = "id", value = "用户id")
    private Integer userId;

    @ApiModelProperty(name = "id", value = "用户昵称")
    private String nickname;

    @ApiModelProperty(name = "id", value = "下级人数")
    private Integer lowerNum;

    @ApiModelProperty(name = "lowerTotalSales", value = "下级总销量")
    private Integer lowerTotalSales;

    @ApiModelProperty(name = "lowerRecentlySales", value = "下级最近30天销量")
    private Integer lowerRecentlySales;

    @ApiModelProperty(name = "gmtCreate", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;
}
