package com.campgem.modules.university.dto;

import com.campgem.common.api.dto.BaseDto;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.modules.university.entity.enums.IdentityTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel(value="第三方登录数据模型",description="第三方登录数据模型")
public class ThirdPartyLoginDto extends BaseDto implements Serializable {
    @ApiModelProperty(value = "认证类型")
    @NotBlank(message = "认证类型不能为空")
    private String identityType;

    @ApiModelProperty(value = "授权码Code")
    @NotBlank(message = "授权码Code不能为空")
    private String code;

    @ApiModelProperty(value = "客户端的状态值")
    private String state;

    @Override
    public void paramValidation() {
        IdentityTypeEnum identityType = IdentityTypeEnum.valueOf(this.getIdentityType());
        if(null == identityType){
            throw new JeecgBootException("该认证类型暂不支持");
        }
    }
}
