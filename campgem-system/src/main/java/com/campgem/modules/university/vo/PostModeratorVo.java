package com.campgem.modules.university.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="论坛版块管理员显示数据模型", description="论坛版块管理员显示数据模型")
public class PostModeratorVo {
    @ApiModelProperty(value = "管理员ID")
    private String moderatorId;
    @ApiModelProperty(value = "管理员名称")
    private String moderatorName;
}
