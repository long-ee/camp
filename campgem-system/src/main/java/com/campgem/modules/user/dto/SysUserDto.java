package com.campgem.modules.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value="管理系统用户创建/编辑数据模型",description="管理系统用户创建/编辑数据模型")
public class SysUserDto {
    @ApiModelProperty(value = "账户ID")
    private String id;
    @ApiModelProperty(value = "账户名")
    @NotBlank(message = "账户名不能为空")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "角色ID")
    @NotBlank(message = "角色ID不能为空")
    private String roleId;
    @ApiModelProperty(value = "城市ID集合")
    private String cityIds;

}
