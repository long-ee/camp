package com.campgem.modules.service.entity;

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
 * @Description: 商家活动
 * @Author: ling
 * @Date: 2019-08-21
 * @Version: V1.0
 */
@Data
@TableName("business_activity")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "business_activity对象", description = "商家活动")
public class BusinessActivity {
	
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private java.lang.String id;
	
	@ApiModelProperty("活动标题")
	private String title;
	
	@Excel(name = "商家ID", width = 15)
	@ApiModelProperty(value = "商家ID")
	private java.lang.String uid;
	
	@Excel(name = "分类ID", width = 15)
	@ApiModelProperty(value = "分类ID")
	private java.lang.String categoryId;
	
	@Excel(name = "开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "开始日期")
	private java.util.Date startDate;
	
	@Excel(name = "结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "结束日期")
	private java.util.Date endDate;
	
	@ApiModelProperty(value = "开始时间")
	private java.util.Date startTime;
	
	@ApiModelProperty(value = "结束时间")
	private java.util.Date endTime;
	
	@Excel(name = "1一次性，2每七天", width = 15)
	@ApiModelProperty(value = "1一次性，2每七天")
	private java.lang.Integer duration;
	
	@Excel(name = "网址", width = 15)
	@ApiModelProperty(value = "网址")
	private java.lang.String website;
	
	@Excel(name = "门票价格", width = 15)
	@ApiModelProperty(value = "门票价格")
	private java.math.BigDecimal price;
	
	@Excel(name = "聚会地点", width = 15)
	@ApiModelProperty(value = "聚会地点")
	private java.lang.String venue;
	
	@Excel(name = "活动内容", width = 15)
	@ApiModelProperty(value = "活动内容")
	private java.lang.Object content;
	
	@Excel(name = "delFlag", width = 15)
	@ApiModelProperty(value = "delFlag")
	private java.lang.Integer delFlag;
	
	@Excel(name = "createBy", width = 15)
	@ApiModelProperty(value = "createBy")
	private java.lang.String createBy;
	
	@Excel(name = "createTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
	
	@Excel(name = "updateTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "updateTime")
	private java.util.Date updateTime;
	
	@Excel(name = "updateBy", width = 15)
	@ApiModelProperty(value = "updateBy")
	private java.lang.String updateBy;
}
