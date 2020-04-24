package com.zhancheng.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author BianShuHeng
 * @decription
 * @project UserDTO
 * @date 2019/11/27 15:36
 */
@Data
public class UserDTO {

    @ApiModelProperty(name = "id", value = "用户id")
    @NotNull(message = "用户id不能为空")
    private Integer id;
    @ApiModelProperty(name = "nickname", value = "用户昵称")
    private String nickname;
    @ApiModelProperty(name = "cover", value = "头像")
    private String cover;
    @ApiModelProperty(name = "gender", value = "性别")
    private String gender;
    @ApiModelProperty(name = "region", value = "地区")
    private String region;
    @ApiModelProperty(name = "birth", value = "出生年月")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    @Pattern(regexp = "^(1[3-9]\\d{9}$)", message = "请输入正确的手机号")
    @ApiModelProperty(name = "phone", value = "手机号")
    private String phone;

    @ApiModelProperty(name = "agentLevel", value = "代理等级")
    private Integer agentLevel;

}
