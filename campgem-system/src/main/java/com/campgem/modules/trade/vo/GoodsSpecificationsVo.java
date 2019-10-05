package com.campgem.modules.trade.vo;

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
@ApiModel(value = "规格显示模型", description = "商品规格")
public class GoodsSpecificationsVo {
	
	/**
	 * id
	 */
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private String id;
	/**
	 * 规格名
	 */
	@Excel(name = "规格名", width = 15)
	@ApiModelProperty(value = "规格名")
	private String specificationsName;
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
	private Integer specificationsStock;
}
