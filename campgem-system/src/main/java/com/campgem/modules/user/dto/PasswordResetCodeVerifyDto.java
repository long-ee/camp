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
@ApiModel(value="PasswordResetCodeVerifyDto", description="密码重置邮箱验证码验证请求数据模型")
public class PasswordResetCodeVerifyDto extends BaseDto {

    @ApiModelProperty(value = "用户邮箱")
    @NotBlank(message = "用户邮箱不能为空")
    private String email;

    @ApiModelProperty(value = "密码重置邮箱验证码")
    @NotBlank(message = "密码重置邮箱验证码不能为空")
    private String validityCode;

}
