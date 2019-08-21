package com.campgem.modules.service.vo;

import com.campgem.modules.service.entity.BusinessActivityImages;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description: 商家活动列表显示模型对象
 * @Author: ling
 * @Date: 2019-08-21
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "商家活动列表显示模型对象", description = "商家活动列表显示模型对象")
public class BusinessActivityDetailVo {
	
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty("分类ID")
	private String categoryId;
	
	@ApiModelProperty("商家ID")
	private String uid;
	
	@ApiModelProperty("活动标题")
	private String title;
	
	@ApiModelProperty("商家名称")
	private String businessName;
	/**
	 * 开始日期
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "开始日期")
	private Date startDate;
	/**
	 * 结束日期
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "结束日期")
	private Date endDate;
	
	/**
	 * 开始时间
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
	@DateTimeFormat(pattern = "HH:mm")
	@ApiModelProperty(value = "开始时间")
	private Date startTime;
	/**
	 * 结束时间
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
	@DateTimeFormat(pattern = "HH:mm")
	@ApiModelProperty(value = "结束时间")
	private Date endTime;
	
	@ApiModelProperty("活动图片列表")
	private List<BusinessActivityImages> images;
	
	@ApiModelProperty("活动网页地址")
	private String website;
	
	@ApiModelProperty("门票价格")
	private BigDecimal price;
	
	@Excel(name = "聚会地点", width = 15)
	@ApiModelProperty(value = "聚会地点")
	private String venue;
	
	@ApiModelProperty("更多活动")
	private List<BusinessActivityMoreOrSimilarListVo> moreActivity;
	
	@ApiModelProperty("相似活动")
	private List<BusinessActivityMoreOrSimilarListVo> similarActivity;
}
