package com.campgem.modules.service.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Description: 商家详情前端显示模型对象
 * @Author: ling
 * @Date: 2019-08-21
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "商家详情前端显示模型对象", description = "商家详情前端显示模型对象")
public class BusinessDetailVo {
	
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "商家名")
	private String businessName;
	
	@ApiModelProperty("头像地址")
	private String srcFace;
	
	@ApiModelProperty("开门时间")
	private String openingTime;
	
	@ApiModelProperty("关门时间")
	private String closingTime;
	
	@ApiModelProperty("商家地址")
	private String address;
	
	@ApiModelProperty("进行中活动")
	private List<BusinessActivityInProgressVo> inProgressActivities;
}
