package com.campgem.modules.university.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="城市查询请求数据模型", description="城市查询请求数据模型")
public class CityQueryDto implements Serializable {
    /** 城市名称 **/
    @ApiModelProperty(value = "城市名称")
    private String cityName;
}
