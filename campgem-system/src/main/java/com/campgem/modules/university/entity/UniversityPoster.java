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

import javax.validation.constraints.NotBlank;

/**
 * @Description: 海报信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Data
@TableName("university_poster")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="university_poster对象", description="海报信息")
public class UniversityPoster {
    
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private String id;
	/**学校ID*/
	@Excel(name = "学校ID", width = 15)
    @ApiModelProperty(value = "学校ID")
	@NotBlank(message = "请选择海报所属学校")
	private String universityId;
	/**海报名称*/
	@Excel(name = "海报名称", width = 15)
    @ApiModelProperty(value = "海报名称")
	@NotBlank(message = "请填写海报名称")
	private String posterName;
	/**海报图片*/
	@Excel(name = "海报图片", width = 15)
    @ApiModelProperty(value = "海报图片")
	@NotBlank(message = "请上传海报图片")
	private String posterImage;
	/**海报内容*/
	@Excel(name = "海报内容", width = 15)
    @ApiModelProperty(value = "海报内容")
	@NotBlank(message = "请填写海报内容")
	private Object posterContent;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束日期")
	private java.util.Date endDate;
	/**发布者*/
	@Excel(name = "发布者", width = 15)
    @ApiModelProperty(value = "发布者")
	private String publisherId;
	/**发布时间*/
	@Excel(name = "发布时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "发布时间", hidden = true)
	private java.util.Date publishTime;
	/**删除状态*/
	@Excel(name = "删除状态", width = 15)
    @ApiModelProperty(value = "删除状态", hidden = true)
	@TableLogic
	private Integer delFlag;
	/**createBy*/
	@Excel(name = "createBy", width = 15)
    @ApiModelProperty(value = "createBy", hidden = true)
	private String createBy;
	/**createTime*/
	@Excel(name = "createTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime", hidden = true)
	private java.util.Date createTime;
	/**updateTime*/
	@Excel(name = "updateTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "updateTime", hidden = true)
	private java.util.Date updateTime;
	/**updateBy*/
	@Excel(name = "updateBy", width = 15)
    @ApiModelProperty(value = "updateBy", hidden = true)
	private String updateBy;
}
