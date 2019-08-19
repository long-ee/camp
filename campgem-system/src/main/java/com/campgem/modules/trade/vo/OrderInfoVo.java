package com.campgem.modules.trade.vo;

import com.campgem.modules.user.vo.ShippingMethodsVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(value = "订单商品")
public class OrderInfoVo implements Serializable {
	@ApiModelProperty("卖家ID")
	private String sellerId;
	
	@ApiModelProperty("卖家名称")
	private String sellerName;
	
	@ApiModelProperty("卖家身份类别，1:Business，2:Student/Individual")
	private Integer sellerIdentity;
	
	@ApiModelProperty("卖家商品列表")
	private List<SellerGoods> goods;
	
	@ApiModelProperty("配送方式列表")
	private List<ShippingMethodsVo> shippingMethods;
	
	@Data
	@ApiModel("订单商品列表")
	public static class SellerGoods {
		@ApiModelProperty("购物车ID")
		private String cartId;
		
		@ApiModelProperty("商品图片")
		private String goodsIcon;
		
		@ApiModelProperty("现价")
		private BigDecimal salePrice;
		
		@ApiModelProperty("商品名")
		private String goodsName;
		
		@ApiModelProperty("商品数量")
		private Integer quantity;
		
		@ApiModelProperty("规格名称")
		private String specificationName;
		
		@ApiModelProperty("税率")
		private BigDecimal taxes;
	}
}
