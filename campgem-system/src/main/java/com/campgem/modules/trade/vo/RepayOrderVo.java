package com.campgem.modules.trade.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @Description: 未支付订单状态判断
 * @Author: ling
 * @Date: 2019-08-20
 * @Version: V1.0
 */
@Data
@ApiModel(value = "未支付订单状态判断", description = "未支付订单状态判断")
public class RepayOrderVo {
	/**
	 * 订单状态
	 */
	private Integer status;
	
	/**
	 * 支付方式
	 */
	private Integer paymentMethod;
	
	/**
	 * 订单商品ID列表
	 */
	private List<String> goodsIds;
}
