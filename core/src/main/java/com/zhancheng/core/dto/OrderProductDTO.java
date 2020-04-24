package com.zhancheng.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author BianShuHeng
 * @decription
 * @project OrderProductDTO
 * @date 2019/11/21 16:20
 */
@Data
public class OrderProductDTO {

    @ApiModelProperty(name = "productId", value = "商品id")
    private Integer productId;
    @ApiModelProperty(name = "productNum", value = "商品数量")
    private Integer productNum;
    @ApiModelProperty(name = "productPrice", value = "商品价格")
    private BigDecimal productPrice;
    @ApiModelProperty(name = "shareId", value = "分享人id")
    private Integer shareId;


}
