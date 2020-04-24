package com.zhancheng.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author BianShuHeng
 * @decription
 * @project CommissionListVO
 * @date 2019/11/22 15:33
 */
@Data
public class CommissionListVO {

    @ApiModelProperty(name = "id", value = "用户id")
    private Integer id;
    @ApiModelProperty(name = "orderId", value = "订单id")
    private Integer orderId;
    @ApiModelProperty(name = "nickname", value = "用户昵称")
    private String nickname;
    @ApiModelProperty(name = "productNum", value = "商品数量")
    private Integer productNum;
    @ApiModelProperty(name = "tradeType", value = "交易类型：1.收入2.支出提现")
    private Integer tradeType;
    @ApiModelProperty(name = "amount", value = "金额")
    private BigDecimal amount;
    @ApiModelProperty(name = "balance", value = "余额")
    private BigDecimal balance;
    @ApiModelProperty(name = "gmtCreate", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date gmtCreate;
}
