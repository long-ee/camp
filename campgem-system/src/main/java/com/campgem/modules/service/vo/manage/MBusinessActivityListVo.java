package com.campgem.modules.service.vo.manage;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 商家活动后台列表显示模型对象
 * @Author: ling
 * @Date: 2019-08-24
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "商家活动后台列表显示模型对象", description = "商家活动后台列表显示模型对象")
public class MBusinessActivityListVo {
	
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty("活动标题")
	private String title;
	
	@ApiModelProperty("商家名称")
	private String businessName;
	
	@ApiModelProperty("分类名")
	private String categoryName;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "开始日期")
	private Date startDate;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "结束日期")
	private Date endDate;
	
	@JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
	@DateTimeFormat(pattern = "HH:mm")
	@ApiModelProperty(value = "开始时间")
	private Date startTime;
	
	@JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
	@DateTimeFormat(pattern = "HH:mm")
	@ApiModelProperty(value = "结束时间")
	private Date endTime;
	
	@ApiModelProperty(value = "创建时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
}
