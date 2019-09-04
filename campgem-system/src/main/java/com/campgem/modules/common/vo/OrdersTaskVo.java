package com.campgem.modules.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.util.List;

/**
 * @Description: 订单定时任务模型
 * @Author: ling
 * @Date: 2019-08-26
 * @Version: V1.0
 */
@Data
@ApiModel(value = "订单定时任务模型", description = "订单定时任务模型")
public class OrdersTaskVo {
	
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty("订单类型")
	private Integer orderType;
	
	@ApiModelProperty("商品ID")
	private String goodsId;
	
	@Excel(name = "状态，0Unpaid，1Paid，2Shipping，3Offline Trading", width = 15)
	@ApiModelProperty(value = "状态，0Unpaid，1Paid，2Shipping，3Offline Trading")
	private Integer status;
	
	@ApiModelProperty("卖家/商家类型，由此判断如何恢复库存")
	private String memberType;
	
	@ApiModelProperty("订单商品列表")
	private List<OrdersGoodsTaskVo> goods;
}
