package com.zhancheng.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project ShipmentsDTO
 * @date 2019/11/22 11:33
 */
@Data
public class ShipmentsDTO {

    @ApiModelProperty(name = "id", value = "订单id",required = true)
    private Integer id;
    @ApiModelProperty(name = "expressCompany", value = "快递公司",required = true)
    private String expressCompany;
    @ApiModelProperty(name = "expressNumber", value = "快递单号",required = true)
    private String expressNumber;
}
