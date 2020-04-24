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
public class QuestionQueryDTO {

    @ApiModelProperty(name = "typeId", value = "分类id")
    private Integer typeId;

}
