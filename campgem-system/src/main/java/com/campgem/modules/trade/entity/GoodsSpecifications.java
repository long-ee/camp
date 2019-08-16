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
 * @Description: 商品规格
 * @Author: ling
 * @Date: 2019-08-17
 * @Version: V1.0
 */
@Data
@TableName("goods_specifications")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "goods_specifications对象", description = "商品规格")
public class GoodsSpecifications {
	
	/**
	 * id
	 */
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private java.lang.String id;
	/**
	 * 商品ID
	 */
	@Excel(name = "商品ID", width = 15)
	@ApiModelProperty(value = "商品ID")
	private java.lang.String goodsId;
	/**
	 * 规格名
	 */
	@Excel(name = "规格名", width = 15)
	@ApiModelProperty(value = "规格名")
	private java.lang.String specificationsName;
	/**
	 * 规格价格
	 */
	@Excel(name = "规格价格", width = 15)
	@ApiModelProperty(value = "规格价格")
	private java.math.BigDecimal specificationsPrice;
	/**
	 * 库存数量
	 */
	@Excel(name = "库存数量", width = 15)
	@ApiModelProperty(value = "库存数量")
	private java.lang.Integer specificationsStock;
}
