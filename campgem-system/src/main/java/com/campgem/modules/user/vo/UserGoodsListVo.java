package com.campgem.modules.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 用户商品显示模型对象
 * @Author: ling
 * @Date: 2019-08-28
 * @Version: V1.0
 */
@Data
@ApiModel(value = "用户商品显示模型对象", description = "用户商品显示模型对象")
public class UserGoodsListVo {
	
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "列表图片")
	private String listImage;
	
	@ApiModelProperty(value = "商品名称")
	private String goodsName;
	
	@ApiModelProperty("价格")
	private String salePrice;
	
	@ApiModelProperty(value = "商品状态")
	private String status;
}
