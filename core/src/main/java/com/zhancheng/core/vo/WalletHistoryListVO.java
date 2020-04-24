package com.zhancheng.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhancheng.core.entity.WalletHistory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author BianShuHeng
 * @decription
 * @project WalletHistoryListVO
 * @date 2019/11/28 21:03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WalletHistoryListVO{

    @ApiModelProperty(name = "tradeNo", value = "交易号")
    private String tradeNo;

    @ApiModelProperty(name = "walletId", value = "钱包id")
    private Integer walletId;

    @ApiModelProperty(name = "userId", value = "用户id")
    private Integer userId;

    @ApiModelProperty(name = "tradeType", value = "交易类型：1.收入2.付款支出3.转账4.提现")
    private Integer tradeType;

    @ApiModelProperty(name = "amount", value = "交易金额")
    private BigDecimal amount;

    @ApiModelProperty(name = "balance", value = "余额")
    private BigDecimal balance;

    @ApiModelProperty(name = "tradeDesc", value = "交易描述信息")
    private String tradeDesc;

    @ApiModelProperty(name = "inOut", value = "收入还是支出")
    private Integer inOut;

    @ApiModelProperty(name = "gmtCreate", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    @ApiModelProperty(name = "nickname", value = "用户昵称")
    private String nickname;
}
