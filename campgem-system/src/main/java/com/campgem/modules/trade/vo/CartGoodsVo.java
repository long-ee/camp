package com.campgem.modules.trade.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "购物车商品")
public class CartGoodsVo implements Serializable {
	@ApiModelProperty("卖家名称")
	private String sellerName;
	
	@ApiModelProperty("商品列表")
	private List<Goods> goods;
	
	@Data
	@ApiModel(value = "购物车商品详情")
	public static class Goods {
		@ApiModelProperty("id")
		private String id;
		
		@ApiModelProperty("身份类别，1Business，2Student/Individual")
		private Integer identity;
		
		@ApiModelProperty("商品图片")
		private String goodsIcon;
		
		@ApiModelProperty("结束日期")
		private Date endDate;
		
		@ApiModelProperty("原价，identity=2有效")
		private BigDecimal originPrice;
		
		@ApiModelProperty("现价")
		private BigDecimal salePrice;
		
		@ApiModelProperty("商品名")
		private String goodsName;
		
		@ApiModelProperty("状态，1:in sale 2:off shelf 3:sold 4:expired")
		private Integer status;
		
		@ApiModelProperty("税率")
		private BigDecimal taxes;
	}
}
