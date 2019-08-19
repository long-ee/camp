package com.campgem.modules.trade.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 订单商品
 * @Author: campgem
 * @Date:   2019-08-19
 * @Version: V1.0
 */
@Data
@TableName("order_goods")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="order_goods对象", description="订单商品")
public class OrderGoods {
    
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private java.lang.String id;
	/**订单ID*/
	@Excel(name = "订单ID", width = 15)
    @ApiModelProperty(value = "订单ID")
	private java.lang.String orderId;
	/**订单商品第*/
	@Excel(name = "订单商品第", width = 15)
    @ApiModelProperty(value = "订单商品第")
	private java.lang.String goodsId;
	/**商品名称*/
	@Excel(name = "商品名称", width = 15)
    @ApiModelProperty(value = "商品名称")
	private java.lang.String goodsName;
	/**商品图标*/
	@Excel(name = "商品图标", width = 15)
    @ApiModelProperty(value = "商品图标")
	private java.lang.String goodsIcon;
	/**规格名称*/
	@Excel(name = "规格名称", width = 15)
    @ApiModelProperty(value = "规格名称")
	private java.lang.String specificationName;
	/**单价*/
	@Excel(name = "单价", width = 15)
    @ApiModelProperty(value = "单价")
	private java.math.BigDecimal price;
	/**商品数量*/
	@Excel(name = "商品数量", width = 15)
    @ApiModelProperty(value = "商品数量")
	private java.lang.Integer quantity;
}
