package com.campgem.modules.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.math.BigDecimal;

/**
 * @Description: 订单商品
 * @Author: campgem
 * @Date:   2019-08-19
 * @Version: V1.0
 */
@Data
@TableName("orders_goods")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="订单商品", description="订单商品")
public class OrdersGoods {
    
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private java.lang.String id;
	/**订单ID*/
	@Excel(name = "订单ID", width = 15)
    @ApiModelProperty(value = "订单ID")
	private java.lang.String orderId;
	@ApiModelProperty("商品ID")
	private String goodsId;
	/**商品名称*/
	@Excel(name = "商品名称", width = 15)
    @ApiModelProperty(value = "商品名称")
	private java.lang.String goodsName;
	/**商品图标*/
	@Excel(name = "商品图标", width = 15)
    @ApiModelProperty(value = "商品图标")
	private java.lang.String goodsIcon;
	@ApiModelProperty("规格ID")
	private String specificationId;
	/**规格名称*/
	@Excel(name = "规格名称", width = 15)
    @ApiModelProperty(value = "规格名称")
	private java.lang.String specificationName;
	/**单价*/
	@Excel(name = "单价", width = 15)
    @ApiModelProperty(value = "单价")
	private java.math.BigDecimal price;
	@ApiModelProperty("税金")
	private BigDecimal taxes;
	/**商品数量*/
	@Excel(name = "商品数量", width = 15)
    @ApiModelProperty(value = "商品数量")
	private java.lang.Integer quantity;
}
