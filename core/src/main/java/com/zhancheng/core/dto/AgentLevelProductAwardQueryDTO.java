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
public class AgentLevelProductAwardQueryDTO {

    @ApiModelProperty(name = "agentLevelId", value = "代理等级id", example = "1")
    private Integer agentLevelId;

}
