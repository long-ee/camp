package com.campgem.modules.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 举报/反馈
 * @Author: ling
 * @Date: 2019-08-30
 * @Version: V1.0
 */
@Data
@TableName("feedback")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "feedback对象", description = "举报/反馈")
public class Feedback {
	
	/**
	 * id
	 */
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private String id;
	/**
	 * 反馈/举报者ID
	 */
	@Excel(name = "反馈/举报者ID", width = 15)
	@ApiModelProperty(value = "反馈/举报者ID")
	private String uid;
	/**
	 * 举报分类，Suggestion, Tip-off
	 */
	@Excel(name = "举报分类，Suggestion, Tip-off", width = 15)
	@ApiModelProperty(value = "举报分类，Suggestion, Tip-off")
	private String category;
	/**
	 * 举报类型，Product，Service，Requirement，Product Review，Service Evaluation
	 */
	@Excel(name = "举报类型，Product，Service，Requirement，Product Review，Service Evaluation", width = 15)
	@ApiModelProperty(value = "举报类型，Product，Service，Requirement，Product Review，Service Evaluation")
	private String type;
	/**
	 * 被举报人ID
	 */
	@Excel(name = "被举报人ID", width = 15)
	@ApiModelProperty(value = "被举报人ID")
	private String reportedUid;
	/**
	 * 举报对象
	 */
	@Excel(name = "举报对象", width = 15)
	@ApiModelProperty(value = "举报对象")
	private String reportObject;
	/**
	 * 举报内容
	 */
	@Excel(name = "举报内容", width = 15)
	@ApiModelProperty(value = "举报内容")
	private Object content;
	/**
	 * 回复的内容
	 */
	@Excel(name = "回复的内容", width = 15)
	@ApiModelProperty(value = "回复的内容")
	private Object replyContent;
	/**
	 * 状态，0待反馈，-1已反馈
	 */
	@Excel(name = "状态，0待反馈，-1已反馈", width = 15)
	@ApiModelProperty(value = "状态，0待反馈，-1已反馈")
	private Integer status;
	/**
	 * delFlag
	 */
	@Excel(name = "delFlag", width = 15)
	@ApiModelProperty(value = "delFlag")
	private Integer delFlag;
	/**
	 * createBy
	 */
	@Excel(name = "createBy", width = 15)
	@ApiModelProperty(value = "createBy")
	private String createBy;
	/**
	 * createTime
	 */
	@Excel(name = "createTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "createTime")
	private Date createTime;
	/**
	 * updateBy
	 */
	@Excel(name = "updateBy", width = 15)
	@ApiModelProperty(value = "updateBy")
	private String updateBy;
	/**
	 * updateTime
	 */
	@Excel(name = "updateTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "updateTime")
	private Date updateTime;
}
