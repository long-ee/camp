package com.campgem.modules.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="分类查询请求数据模型", description="分类查询请求数据模型")
public class CategoryQueryDto implements Serializable {
    /** 分类名称 **/
    @ApiModelProperty(value = "分类名称")
    private String categoryName;
}
