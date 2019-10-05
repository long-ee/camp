package com.campgem.modules.user.vo;

import com.campgem.modules.trade.entity.GoodsImages;
import com.campgem.modules.trade.entity.GoodsSpecifications;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 用户商品编辑/新增模型对象
 * @Author: ling
 * @Date: 2019-08-28
 * @Version: V1.0
 */
@Data
@ApiModel(value = "用户商品编辑/新增模型对象", description = "用户商品编辑/新增模型对象")
public class UserGoodsVo {
	
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "商品名称")
	@NotNull(message = "商品名称不能为空")
	private String goodsName;
	
	@ApiModelProperty("分类ID")
	@NotNull(message = "分类不能为空")
	private String categoryId;
	
	@ApiModelProperty("标签")
	private String tags;
	
	@ApiModelProperty("商品图片")
	@NotEmpty(message = "商品图片不能为空")
	private GoodsImages[] goodsImages;
	
	@ApiModelProperty("商品描述")
	@NotNull(message = "商品描述不能为空")
	private String description;
	
	@ApiModelProperty("学生/一般用户发布商品需要的属性")
	private GeneralProperty generalProperty;
	
	@ApiModelProperty("商家发布商品需要的属性")
	private BusinessProperty businessProperty;
	
	@ApiModel("学生/一般用户商品属性")
	@Data
	public static class GeneralProperty {
		@ApiModelProperty("原价")
		private BigDecimal originPrice;
		
		@ApiModelProperty("现价")
		private BigDecimal salePrice;
		
		@ApiModelProperty("库存数量")
		private Integer stock;
		
		@ApiModelProperty("新旧程度，1:Brand new 2:Almost new 3:Gently used")
		private Integer quality;
		
		@ApiModelProperty("结束日期")
		@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date endDate;
	}
	
	@ApiModel("商家商品属性")
	@Data
	public static class BusinessProperty {
		@ApiModelProperty("税率")
		private BigDecimal taxes;
		
		@ApiModelProperty("规格列表")
		private GoodsSpecifications[] goodsSpecifications;
	}
}
