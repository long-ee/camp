package com.campgem.modules.university.dto;

import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.api.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel(value="用户密码修改数据模型",description="用户密码修改数据模型")
public class UserPasswordModifyDto extends BaseDto implements Serializable {

    @ApiModelProperty(value = "原密码")
    @NotBlank(message = "原密码不能为空")
    private String originalPassword;

    @ApiModelProperty(value = "新密码")
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    @ApiModelProperty(value = "新密码确认")
    @NotBlank(message = "新密码确认不能为空")
    private String repeatPassword;

    @ApiModelProperty(value = "用户邮箱", hidden = true)
    private String email;

    @Override
    public void paramValidation() {
        if(StringUtils.isBlank(this.email)){
            throw new JeecgBootException("用户邮箱不能为空");
        }
    }
}
