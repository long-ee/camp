package com.campgem.modules.university.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 社团活动信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Data
@ApiModel(value="社团活动显示数据模型", description="社团活动显示数据模型")
public class ClubActivityVo {
	/**id*/
    @ApiModelProperty(value = "id")
	private String id;
	/**社团ID*/
    @ApiModelProperty(value = "社团ID")
	private String clubId;
	/**社团名称*/
	@ApiModelProperty(value = "社团名称")
	private String clubName;
	/**所属大学*/
	@ApiModelProperty(value = "所属大学")
	private String universityId;
	/**所属大学名称*/
	@ApiModelProperty(value = "所属大学名称")
	private String universityName;
	/**所属城市*/
	@ApiModelProperty(value = "所属城市")
	private String cityId;
	/**发布者*/
	@ApiModelProperty(value = "发布者")
	private String publisherId;
	/**发布者名称*/
	@ApiModelProperty(value = "发布者名称")
	private String publisherName;
	/**城市名称*/
	@ApiModelProperty(value = "城市名称")
	private String cityName;
	/**社团活动内容*/
    @ApiModelProperty(value = "社团活动内容")
	private Object activityContent;
	/**活动开始日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "活动开始日期")
	private java.util.Date startDate;
	/**活动结束日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "活动结束日期")
	private java.util.Date endDate;
	/**活动持续时间*/
    @ApiModelProperty(value = "活动持续时间")
	private String activityDuration;
	/**活动开始时间*/
    @ApiModelProperty(value = "活动开始时间")
	private java.lang.String startTime;
	/**活动结束时间*/
    @ApiModelProperty(value = "活动结束时间")
	private java.lang.String endTime;
	/**活动标题*/
    @ApiModelProperty(value = "活动标题")
	private String activityTitle;
	/**是否是社团成员*/
	@ApiModelProperty(value = "是否是社团成员")
	private boolean isClubMember;
	/**createTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private java.util.Date createTime;

}
