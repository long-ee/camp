package com.campgem.modules.trade.dto;

import com.campgem.common.api.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "商品信息查询数据模型", description = "商品信息查询数据模型")
public class OrderInfoDto extends BaseDto {
	@ApiModelProperty(value = "用户选择结算的购物车ID列表，与goodsId不能同时为空，也不能同时存在")
	private String[] cartIds;
	
	@ApiModelProperty("用户立即购买的商品ID，与cartIds不能同时为空，也不能同时存在")
	private String goodsId;
	
	@ApiModelProperty("用户立即购买的商品的规格，可为空")
	private String specificationId;
	
	@ApiModelProperty("用户立即购买的商品的数量，默认1")
	private Integer quantity = 1;
}
