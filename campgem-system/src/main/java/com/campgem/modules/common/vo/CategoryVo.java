package com.campgem.modules.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

@Data
@ApiModel(value="类别显示数据模型", description="类别显示数据模型")
public class CategoryVo implements Serializable {
    /**id*/
    @ApiModelProperty(value = "id")
    private String id;
    /**分类图标*/
    @ApiModelProperty(value = "分类图标")
    private String categoryIcon;
    /**分类名称*/
    @ApiModelProperty(value = "分类名称")
    private String categoryName;
    /**分类类别*/
    @ApiModelProperty(value = "分类类别")
    private String categoryType;
    /**评价纬度*/
    @Excel(name = "评价纬度", width = 15)
    @ApiModelProperty(value = "评价纬度")
    private String evaluationDimension;
    /**createTime*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
    private java.util.Date createTime;
}
