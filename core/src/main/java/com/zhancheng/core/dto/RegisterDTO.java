package com.zhancheng.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project registerDto
 * @date 2019/11/19 18:24
 */
@Data
public class RegisterDTO {

    @ApiModelProperty(name = "code", value = "登录code",required = true)
    private String code;
    @ApiModelProperty(name = "encryptedData", value = "包括敏感数据在内的完整用户信息的加密数据",required = true)
    private String encryptedData;
    @ApiModelProperty(name = "iv", value = "加密算法的初始向量",required = true)
    private String iv;
    @ApiModelProperty(name = "inviterId", value = "邀请人id")
    private Integer inviterId;
}
