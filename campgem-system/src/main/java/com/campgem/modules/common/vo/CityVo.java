package com.campgem.modules.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

@Data
@ApiModel(value="城市显示数据模型", description="城市显示数据模型")
public class CityVo implements Serializable {
    /**id*/
    @ApiModelProperty(value = "id")
    private String id;
    /**国家*/
    @ApiModelProperty(value = "国家")
    private String country;
    /**区*/
    @ApiModelProperty(value = "州")
    private String state;
    /**城市名称*/
    @ApiModelProperty(value = "城市名称")
    private String cityName;
    /**邮政编码*/
    @ApiModelProperty(value = "邮政编码")
    private String postCode;
    /**createTime*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
    private java.util.Date createTime;
}
