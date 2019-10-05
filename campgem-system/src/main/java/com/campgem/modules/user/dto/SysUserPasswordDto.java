package com.campgem.modules.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value="管理系统用户密码修改数据模型",description="管理系统用户密码修改数据模型")
public class SysUserPasswordDto {
    @ApiModelProperty(value = "账户ID")
    private String id;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "旧密码")
    private String oldPassword;
}
