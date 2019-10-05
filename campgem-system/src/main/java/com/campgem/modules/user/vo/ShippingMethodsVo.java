package com.campgem.modules.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 用户配送方式
 * @Author: ling
 * @Date: 2019-08-19
 * @Version: V1.0
 */
@Data
@ApiModel(value = "用户配送方式显示模型对象", description = "用户配送方式显示模型对象")
public class ShippingMethodsVo {
	@ApiModelProperty("配送方式名称")
	private String name;
	@ApiModelProperty("配送方式价格")
	private String price;
}
