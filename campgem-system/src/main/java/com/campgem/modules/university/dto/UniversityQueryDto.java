package com.campgem.modules.university.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="学校查询请求数据模型", description="学校查询请求数据模型")
public class UniversityQueryDto implements Serializable {
    /** 学校名称 **/
    @ApiModelProperty(value = "学校名称")
    private String universityName;
}
