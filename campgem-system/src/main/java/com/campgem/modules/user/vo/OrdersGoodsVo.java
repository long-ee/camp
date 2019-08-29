package com.campgem.modules.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 用户个人中心订单商品列表显示模型
 * @Author: ling
 * @Date: 2019-08-28
 * @Version: V1.0
 */
@Data
@ApiModel(value = "用户个人中心订单商品列表显示模型", description = "用户个人中心订单商品列表显示模型")
public class OrdersGoodsVo {
	
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "图片")
	private String listImage;
	
	@ApiModelProperty(value = "商品/服务名称")
	private String goodsName;
	
	@ApiModelProperty("商品规格")
	private String specificationName;
	
	@ApiModelProperty(value = "单价")
	private String price;
	
	@ApiModelProperty(value = "数量")
	private String quantity;
}
