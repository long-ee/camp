package com.campgem.modules.trade.entity;

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

/**
 * @Description: 需求留言
 * @Author: campgem
 * @Date: 2019-08-18
 * @Version: V1.0
 */
@Data
@TableName("requirements_reviews")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "requirements_reviews对象", description = "需求留言")
public class RequirementsReviews {
	
	/**
	 * id
	 */
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private java.lang.String id;
	
	@ApiModelProperty("用户ID")
	private String uid;
	/**
	 * 需求ID
	 */
	@Excel(name = "需求ID", width = 15)
	@ApiModelProperty(value = "需求ID")
	private java.lang.String requirementId;
	/**
	 * 留言内容
	 */
	@Excel(name = "留言内容", width = 15)
	@ApiModelProperty(value = "留言内容")
	private java.lang.String content;
	
	@ApiModelProperty("发布者的回复内容")
	private String replyContent;
	/**
	 * delFlag
	 */
	@Excel(name = "delFlag", width = 15)
	@ApiModelProperty(value = "delFlag")
	private java.lang.Integer delFlag;
	/**
	 * 留言时间
	 */
	@Excel(name = "留言时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "留言时间")
	private java.util.Date createTime;
}
