package com.campgem.modules.user.dto;

import com.campgem.common.api.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: campgem
 */
@Data
@ApiModel(value="PasswordResetDto", description="密码重置请求数据模型")
public class PasswordResetDto extends BaseDto {

    @ApiModelProperty(value = "用户邮箱")
    @NotBlank(message = "用户邮箱不能为空")
    private String email;

    @ApiModelProperty(value = "新密码")
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    @ApiModelProperty(value = "新密码确认")
    @NotBlank(message = "新密码确认不能为空")
    private String repeatPassword;

}
