package com.campgem.modules.trade.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel(value = "购物车商品显示数据模型")
public class GoodsOrderInfoVo implements Serializable {
	@ApiModelProperty("卖家名称")
	private String sellerName;
	
	@ApiModelProperty("身份类别，1Business，2Student/Individual")
	private Integer identity;
	
	@ApiModelProperty("商品图片")
	private String goodsIcon;
	
	@ApiModelProperty("现价")
	private BigDecimal salePrice;
	
	@ApiModelProperty("商品名")
	private String goodsName;
	
	@ApiModelProperty("商品数量")
	private Integer quantity;
	
	@ApiModelProperty(value = "规格信息", hidden = true)
	private String specification;
	
	@ApiModelProperty("规格名称")
	private String specificationName;
	
	@ApiModelProperty("税率")
	private BigDecimal taxes;
}
