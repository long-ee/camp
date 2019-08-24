package com.campgem.modules.university.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="海报查询请求数据模型", description="海报查询请求数据模型")
public class UniversityPosterQueryDto implements Serializable {
    /** 学校ID **/
    @ApiModelProperty(value = "学校ID")
    private String universityId;
    /** 学校海报名称 **/
    @ApiModelProperty(value = "学校海报名称")
    private String posterName;
    /** 学校海报ID **/
    @ApiModelProperty(value = "学校海报ID")
    private String posterId;
    @ApiModelProperty(value = "发布者", hidden = true)
    private String publisherId;
}
