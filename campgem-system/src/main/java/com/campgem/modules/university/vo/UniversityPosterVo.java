package com.campgem.modules.university.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

@Data
@ApiModel(value="海报显示数据模型", description="海报显示数据模型")
public class UniversityPosterVo implements Serializable {
    /**id*/
    @ApiModelProperty(value = "id")
    private String id;
    /**学校ID*/
    @ApiModelProperty(value = "学校ID")
    private String universityId;
    /**大学名称*/
    @ApiModelProperty(value = "大学名称")
    private String universityName;
    /**所属城市*/
    @ApiModelProperty(value = "所属城市")
    private String cityId;
    /**城市名称*/
    @ApiModelProperty(value = "城市名称")
    private String cityName;
    /**海报名称*/
    @Excel(name = "海报名称", width = 15)
    @ApiModelProperty(value = "海报名称")
    private String posterName;
    /**海报图片*/
    @ApiModelProperty(value = "海报图片")
    private String posterImage;
    /**海报内容*/
    @ApiModelProperty(value = "海报内容")
    private Object posterContent;
    /**结束日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束日期")
    private java.util.Date endDate;
    /**发布者*/
    @ApiModelProperty(value = "发布者")
    private String publisherId;
    /**发布者*/
    @ApiModelProperty(value = "发布者")
    private String publisherName;
    /**发布时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "发布时间")
    private java.util.Date publishTime;
    /**createTime*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
    private java.util.Date createTime;
}
