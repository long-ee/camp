package com.campgem.modules.university.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="社团活动查询请求数据模型", description="社团活动查询请求数据模型")
public class ClubActivityQueryDto implements Serializable {
    /** 活动标题 **/
    @ApiModelProperty(value = "活动标题")
    private String activityTitle;
    /** 社团名称 **/
    @ApiModelProperty(value = "社团名称")
    private String clubName;
    /** 学校名称 **/
    @ApiModelProperty(value = "学校名称")
    private String universityName;
}
