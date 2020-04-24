package com.zhancheng.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project WalletHistoryDTO
 * @date 2019/11/28 21:00
 */
@Data
public class AwardQueryDTO {

    @ApiModelProperty(name = "userId", value = "用户id", example = "1")
    private Integer userId;

    @ApiModelProperty(name = "shareId", value = "佣金获得者id", example = "1")
    private Integer shareId;

}
