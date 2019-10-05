package com.campgem.modules.university.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="学校查询请求数据模型", description="学校查询请求数据模型")
public class UniversityQueryDto implements Serializable {
    /** 学校ID **/
    @ApiModelProperty(value = "学校ID")
    private String universityId;
    /** 学校名称 **/
    @ApiModelProperty(value = "学校名称")
    private String universityName;
    /**国家*/
    @ApiModelProperty(value = "国家")
    private String country;
    /**区*/
    @ApiModelProperty(value = "州")
    private String state;
    @ApiModelProperty(value = "所属城市ID")
    private String cityId;
    /**城市名称*/
    @ApiModelProperty(value = "城市名称")
    private String cityName;
}
