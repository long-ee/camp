package com.campgem.modules.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 订单商品定时任务模型
 * @Author: ling
 * @Date: 2019-08-26
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "订单商品定时任务模型", description = "订单商品定时任务模型")
public class OrdersGoodsTaskVo {
	
	/**
	 * id
	 */
//	@TableId(type = IdType.UUID)
//	@ApiModelProperty(value = "id")
//	private java.lang.String id;
	/**
	 * 订单ID
	 */
//	@Excel(name = "订单ID", width = 15)
//	@ApiModelProperty(value = "订单ID")
//	private java.lang.String orderId;
	@ApiModelProperty("商品ID")
	private String goodsId;
	/**
	 * 商品名称
	 */
//	@Excel(name = "商品名称", width = 15)
//	@ApiModelProperty(value = "商品名称")
//	private java.lang.String goodsName;
	/**
	 * 商品图标
	 */
//	@Excel(name = "商品图标", width = 15)
//	@ApiModelProperty(value = "商品图标")
//	private java.lang.String goodsIcon;
	
	@ApiModelProperty("规格ID")
	private String specificationId;
	/**
	 * 规格名称
	 */
//	@Excel(name = "规格名称", width = 15)
//	@ApiModelProperty(value = "规格名称")
//	private java.lang.String specificationName;
	/**
	 * 单价
	 */
//	@Excel(name = "单价", width = 15)
//	@ApiModelProperty(value = "单价")
//	private java.math.BigDecimal price;
//	@ApiModelProperty("税金")
//	private BigDecimal taxes;
	/**
	 * 商品数量
	 */
	@Excel(name = "商品数量", width = 15)
	@ApiModelProperty(value = "商品数量")
	private java.lang.Integer quantity;
}
