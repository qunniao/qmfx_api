package com.zhancheng.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project ProductQueryDTO
 * @date 2019/10/18 14:49
 */
@Data
public class ProductQueryDTO {

    @ApiModelProperty(name = "typeId", value = "类型id")
    private Integer typeId;
    @ApiModelProperty(name = "search", value = "搜索条件")
    private String search;
    @ApiModelProperty(name = "sortType", value = "排序类型")
    private String sortType;
}
