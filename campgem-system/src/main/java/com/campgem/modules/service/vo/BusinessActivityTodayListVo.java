package com.campgem.modules.service.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 商家活动列表显示模型对象
 * @Author: ling
 * @Date: 2019-08-21
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "商家活动列表显示模型对象", description = "商家活动列表显示模型对象")
public class BusinessActivityTodayListVo {
	
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty("活动标题")
	private String title;
	
	@ApiModelProperty("分类名")
	private String categoryName;
	
	@ApiModelProperty("封面图")
	private String listImage;
	
	@ApiModelProperty(value = "聚会地点")
	private String venue;
	
	@ApiModelProperty("活动内容")
	private String content;
}
