package com.campgem.modules.user.dto;

import com.campgem.common.api.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "订单评价数据模型", description = "订单评价数据模型")
public class OrdersEvaluationDto extends BaseDto implements Serializable {
	
	@ApiModelProperty("商品评价，可能会有多个商品")
	private GoodsEvaluationDto[] goodsEvaluations;
	
	@ApiModelProperty("服务ID")
	private String serviceId;
	
	@ApiModelProperty(value = "服务评价内容")
	private String content;
	
	@ApiModelProperty("Security分数")
	private Integer securityRating;
	
	@ApiModelProperty("Environment分数")
	private Integer environmentRating;
	
	@ApiModelProperty("Service分数")
	private Integer serviceRating;
	
	@ApiModelProperty("Price分数")
	private Integer priceRating;
	
	@ApiModelProperty("Quality分数")
	private Integer qualityRating;
	
	@ApiModelProperty("After-sales分数")
	private Integer afterSalesRating;
	
	@ApiModelProperty("Maintenance level分数")
	private Integer maintenanceLevelRating;
	
	@ApiModelProperty("Funny分数")
	private Integer funnyRating;
	
	@ApiModelProperty("Taste分数")
	private Integer tasteRating;
	
	@ApiModelProperty("time分数")
	private Integer timeRating;
	
	@Data
	@ApiModel("商品评价")
	public static class GoodsEvaluationDto {
		@ApiModelProperty("商品评价内容")
		private String content;
		
		@ApiModelProperty("商品ID")
		private String goodsId;
		
		@ApiModelProperty("商品评分")
		private Integer rating;
	}
}
