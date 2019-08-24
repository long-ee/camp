package com.campgem.modules.university.dto;

import com.campgem.common.api.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value="社团管理员管理数据模型", description="社团管理员管理数据模型")
public class ClubAdminDto extends BaseDto {
    @ApiModelProperty(value = "社团ID")
    @NotBlank(message = "社团ID不能为空")
    private String clubId;
    @ApiModelProperty(value = "管理员会员ID")
    @NotBlank(message = "管理员会员ID不能为空")
    private String memberId;
}
