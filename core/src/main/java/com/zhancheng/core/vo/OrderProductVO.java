package com.zhancheng.core.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author BianShuHeng
 * @decription
 * @project OrderProductVO
 * @date 2019/11/21 14:49
 */
@Data
public class OrderProductVO {
    @ApiModelProperty(name = "id", value = "id")
    private Integer id;
    @ApiModelProperty(name = "pid", value = "产品id")
    private Integer pid;
    @ApiModelProperty(name = "cover", value = "主图")
    private String cover;
    @ApiModelProperty(name = "productNumber", value = "货号")
    private String productNumber;
    @ApiModelProperty(name = "title", value = "商品名称")
    private String title;
    @ApiModelProperty(name = "productPrice", value = "商品价格")
    private BigDecimal productPrice;
    @ApiModelProperty(name = "productNum", value = "商品数量")
    private Integer productNum;
    @ApiModelProperty(name = "shareId", value = "分享人id")
    private Integer shareId;

}
