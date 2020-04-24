package com.zhancheng.core.vo;

import com.zhancheng.core.entity.ProductImage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project ProductVO
 * @date 2019/11/20 11:52
 */
@Data
public class ProductVO {

    @ApiModelProperty(name = "pid", value = "产品id")
    private Integer pid;
    @ApiModelProperty(name = "price", value = "价格")
    private BigDecimal price;
    @ApiModelProperty(name = "typeId", value = "类型id")
    private Integer typeId;
    @ApiModelProperty(name = "cover", value = "主图")
    private String cover;
    @ApiModelProperty(name = "title", value = "产品名称")
    private String title;
    @ApiModelProperty(name = "productIntro", value = "产品信息")
    private String productIntro;
    @ApiModelProperty(name = "productNumber", value = "产品编号")
    private String productNumber;
    @ApiModelProperty(name = "video", value = "视频")
    private String video;
    @ApiModelProperty(name = "sales", value = "销量")
    private Integer sales;
    @ApiModelProperty(name = "detailMobile", value = "手机端详情")
    private String detailMobile;
    @ApiModelProperty(name = "totalStock", value = "库存")
    private Integer totalStock;
    /**
     * 零售运费模板id
     */
    private Integer retailFreightId;

    @ApiModelProperty(name = "productImageList", value = "产品轮播图")
    private List<ProductImage> productImageList;
}
