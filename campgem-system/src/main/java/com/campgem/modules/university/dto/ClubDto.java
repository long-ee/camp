package com.campgem.modules.university.dto;

import com.campgem.common.api.dto.BaseDto;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotBlank;

/**
 * @author: X.Tony
 */
@Data
@ApiModel(value="加入社团请求数据模型", description="加入社团请求数据模型")
public class ClubDto extends BaseDto {
    /**社团ID*/
    @ApiModelProperty(value = "社团ID")
    @NotBlank(message = "社团ID不能为空")
    private String clubId;
    /**会员ID*/
    @ApiModelProperty(value = "会员ID")
    @NotBlank(message = "会员ID不能为空")
    private String memberId;

    @Override
    public void paramValidation() {
        if(StringUtils.isBlank(this.clubId) || StringUtils.isBlank(this.memberId)){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
    }
}
