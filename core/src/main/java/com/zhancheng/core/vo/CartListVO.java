package com.zhancheng.core.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author BianShuHeng
 * @decription
 * @project CartListVO
 * @date 2019/11/23 14:30
 */
@Data
public class CartListVO {

    @ApiModelProperty(name = "id", value = "购物车id")
    private Integer id;
    @ApiModelProperty(name = "userId", value = "用户id")
    private Integer userId;

    @ApiModelProperty(name = "productId", value = "产品id")
    private Integer productId;

    @ApiModelProperty(name = "productName", value = "商品名称")
    private String productName;

    @ApiModelProperty(name = "productName", value = "商品图片")
    private String cover;

    @ApiModelProperty(name = "productNum", value = "商品数量")
    private Integer productNum;

    @ApiModelProperty(name = "productPrice", value = "商品价格")
    private BigDecimal productPrice;

    @ApiModelProperty(name = "retailFreightId", value = "零售运费模板id")
    private Integer retailFreightId;

}
