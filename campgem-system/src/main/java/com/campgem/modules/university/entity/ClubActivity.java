package com.campgem.modules.university.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 社团活动信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Data
@TableName("club_activity")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="club_activity对象", description="社团活动信息")
public class ClubActivity {
    
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private String id;
	/**社团ID*/
	@Excel(name = "社团ID", width = 15)
    @ApiModelProperty(value = "社团ID")
	private String clubId;
	/**社团活动内容*/
	@Excel(name = "社团活动内容", width = 15)
    @ApiModelProperty(value = "社团活动内容")
	private Object activityContent;
	/**活动开始日期*/
	@Excel(name = "活动开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "活动开始日期")
	private java.util.Date startDate;
	/**活动结束日期*/
	@Excel(name = "活动结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "活动结束日期")
	private java.util.Date endDate;
	/**活动持续时间*/
	@Excel(name = "活动持续时间", width = 15)
    @ApiModelProperty(value = "活动持续时间")
	private String activityDuration;
	/**活动开始时间*/
	@Excel(name = "活动开始时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "活动开始时间")
	private java.util.Date startTime;
	/**活动结束时间*/
	@Excel(name = "活动结束时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "活动结束时间")
	private java.util.Date endTime;
	/**活动标题*/
	@Excel(name = "活动标题", width = 15)
    @ApiModelProperty(value = "活动标题")
	private String activityTitle;
	/**发布者*/
	@Excel(name = "发布者", width = 15)
	@ApiModelProperty(value = "发布者")
	private String publisherId;
	/**删除状态*/
	@Excel(name = "删除状态", width = 15)
    @ApiModelProperty(value = "删除状态")
	@TableLogic
	private Integer delFlag;
	/**createBy*/
	@Excel(name = "createBy", width = 15)
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**createTime*/
	@Excel(name = "createTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
	/**updateTime*/
	@Excel(name = "updateTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "updateTime")
	private java.util.Date updateTime;
	/**updateBy*/
	@Excel(name = "updateBy", width = 15)
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
}
