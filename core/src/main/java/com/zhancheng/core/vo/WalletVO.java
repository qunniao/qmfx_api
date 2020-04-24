package com.zhancheng.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author BianShuHeng
 * @decription
 * @project WalletVO
 * @date 2019/11/20 15:55
 */
@Data
public class WalletVO {

    @ApiModelProperty(name = "id", value = "钱包id")
    private Integer id;
    @ApiModelProperty(name = "userId", value = "用户id")
    private Integer userId;
    @ApiModelProperty(name = "balance", value = "余额")
    private BigDecimal balance;
    @ApiModelProperty(name = "waitBalance", value = "待结算")
    private BigDecimal waitBalance;
    @ApiModelProperty(name = "gmtCreate", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;
    @ApiModelProperty(name = "gmtModified", value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;
}
