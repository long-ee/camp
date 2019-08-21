package com.campgem.modules.trade.dto;

import com.campgem.common.api.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "商品留言数据模型", description = "商品留言数据模型")
public class GoodsReviewDto extends BaseDto {
	@ApiModelProperty("商品ID")
	@NotBlank(message = "商品ID不能为空")
	private String goodsId;
	
	@ApiModelProperty(value = "留言内容")
	@NotBlank(message = "留言内容不能为空")
	private String content;
	
	@ApiModelProperty("是否公开，默认公开")
	private Integer isOpen = 1;
}
