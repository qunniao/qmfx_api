package com.zhancheng.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author BianShuHeng
 * @decription
 * @project ProductListVO
 * @date 2019/11/20 11:12
 */
@Data
public class ProductListVO {
    @ApiModelProperty(name = "pid", value = "产品id")
    private Integer pid;
    @ApiModelProperty(name = "title", value = "产品名称")
    private String title;
    @ApiModelProperty(name = "cover", value = "产品主图")
    private String cover;
    @ApiModelProperty(name = "price", value = "产品价格")
    private BigDecimal price;
    @ApiModelProperty(name = "sales", value = "销量")
    private Integer sales;
    @ApiModelProperty(name = "gmtCreate", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;
    @ApiModelProperty(name = "retailFreightId", value = "零售运费模板id")
    private Integer retailFreightId;
    @ApiModelProperty(name = "productNumber", value = "产品编号")
    private String productNumber;
    @ApiModelProperty(name = "totalStock", value = "库存")
    private Integer totalStock;
}
