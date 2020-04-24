package com.zhancheng.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author BianShuHeng
 */
@Data
public class TransferListVO {

    @ApiModelProperty(name = "id", value = "钱包收支流水id")
    private Integer id;

    @ApiModelProperty(name = "userId", value = "用户id")
    private Integer userId;

    @ApiModelProperty(name = "orderId", value = "订单id")
    private Integer orderId;

    @ApiModelProperty(name = "type", value = "类型：1.余额2.佣金")
    private Integer type;

    @ApiModelProperty(name = "tradeType", value = "交易类型：1.收入2.支出提现")
    private Integer tradeType;

    @ApiModelProperty(name = "amount", value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(name = "balance", value = "余额")
    private BigDecimal balance;

    @ApiModelProperty(name = "remark", value = "描述")
    private String remark;

    @ApiModelProperty(name = "gmtCreate", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    @ApiModelProperty(name = "gmtModified", value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;
}