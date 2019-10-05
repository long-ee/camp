package com.campgem.modules.university.dto;

import com.campgem.common.api.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value="社团活动创建、编辑数据模型", description="社团活动创建、编辑数据模型")
public class ClubActivityDto extends BaseDto {
    /**id*/
    @ApiModelProperty(value = "id")
    private String id;
    /**社团ID*/
    @ApiModelProperty(value = "社团ID")
    @NotBlank(message = "社团ID不能为空")
    private String clubId;
    /**活动标题*/
    @ApiModelProperty(value = "活动标题")
    @NotBlank(message = "活动标题不能为空")
    private String activityTitle;
    /**社团活动内容*/
    @ApiModelProperty(value = "社团活动内容")
    @NotBlank(message = "社团活动内容不能为空")
    private Object activityContent;
    /**活动开始日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "活动开始日期")
    @NotNull(message = "活动开始日期不能为空")
    private java.util.Date startDate;
    /**活动结束日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "活动结束日期")
    @NotNull(message = "活动结束日期不能为空")
    private java.util.Date endDate;
    /**活动持续时间*/
    @ApiModelProperty(value = "活动持续时间")
    @NotBlank(message = "活动持续时间不能为空")
    private String activityDuration;
    /**活动开始时间*/
    @ApiModelProperty(value = "活动开始时间")
    @NotBlank(message = "活动开始时间不能为空")
    private String startTime;
    /**活动结束时间*/
    @ApiModelProperty(value = "活动结束时间")
    @NotBlank(message = "活动结束时间不能为空")
    private String endTime;
    /**发布者*/
    @ApiModelProperty(value = "发布者", hidden = true)
    private String publisherId;
}
