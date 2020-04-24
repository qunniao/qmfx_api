package com.zhancheng.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project OrderCartDTO
 * @date 2019/11/23 15:06
 */
@Data
public class OrderCartDTO {

    @ApiModelProperty(name = "userId", value = "用户id")
    private Integer userId;
    @ApiModelProperty(name = "contactName", value = "收货人姓名")
    private String contactName;
    @ApiModelProperty(name = "contactPhone", value = "收货人手机号")
    private String contactPhone;
    @ApiModelProperty(name = "contactAddress", value = "收货人地址")
    private String contactAddress;
    @ApiModelProperty(name = "freight", value = "运费")
    private BigDecimal freight;
    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
    @ApiModelProperty(name = "deliveryWay", value = "配送方式1.快递2.无需物流3.自提；默认为1")
    private Integer deliveryWay;
    @ApiModelProperty(name = "cidList", value = "购物车id集合")
    private List<Integer> cidList;
}
