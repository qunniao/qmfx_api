package com.zhancheng.core.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project CartListVO
 * @date 2019/11/23 14:30
 */
@Data
public class CartConfirmListVO {



    @ApiModelProperty(name = "cartList", value = "购物车列表")
    private List<CartListVO> cartList;

    @ApiModelProperty(name = "discount", value = "优惠金额")
    private BigDecimal discount;

    @ApiModelProperty(name = "freight", value = "运费")
    private BigDecimal freight;
}
