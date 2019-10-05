package com.campgem.modules.university.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

@Data
@ApiModel(value="学校显示数据模型", description="学校显示数据模型")
public class UniversityVo implements Serializable {
    /**id*/
    @ApiModelProperty(value = "id")
    private String id;
    /**所属城市*/
    @ApiModelProperty(value = "所属城市")
    private String cityId;
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
    /**大学名称*/
    @ApiModelProperty(value = "大学名称")
    private String universityName;
    /**大学图片*/
    @ApiModelProperty(value = "大学图片")
    private String universityImage;
    /**官网地址*/
    @ApiModelProperty(value = "官网地址")
    private String officialAddress;
    /**是否启用*/
    @ApiModelProperty(value = "是否启用")
    private String enabled;
    /**createTime*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
    private java.util.Date createTime;
}
