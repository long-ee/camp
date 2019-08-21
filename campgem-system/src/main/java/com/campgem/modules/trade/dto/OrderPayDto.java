package com.campgem.modules.trade.dto;

import com.campgem.common.api.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "商品信息查询数据模型", description = "商品信息查询数据模型")
public class OrderPayDto extends BaseDto {
	@ApiModelProperty("不同商家的订单")
	@NotEmpty(message = "订单信息不能为空")
	private List<OrderInfo> orders;
	
	@ApiModelProperty("支付方式，1PayPal，2Visa/Masterd Card")
	@NotNull(message = "支付方式不能为空")
	private Integer paymentMethod;
	
	@ApiModelProperty("选择的收货地址ID")
	@NotBlank(message = "收货地址不能为空")
	private String addressId;
	
	@Data
	@ApiModel("针对不同商家的订单信息")
	public static class OrderInfo {
		@ApiModelProperty("配送方式")
		private String shipping;
		
		@ApiModelProperty(value = "用户选择结算的购物车ID列表，与goodsId不能同时为空，也不能同时存在")
		private String[] cartIds;
		
		@ApiModelProperty("用户立即购买的商品ID，与cartIds不能同时为空，也不能同时存在")
		private String goodsId;
		
		@ApiModelProperty("用户立即购买的商品的规格，可为空")
		private String specificationId;
		
		@ApiModelProperty("用户立即购买的商品的数量，默认1")
		private Integer quantity = 1;
	}
}
