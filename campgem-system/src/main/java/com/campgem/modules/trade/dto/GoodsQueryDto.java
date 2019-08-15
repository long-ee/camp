package com.campgem.modules.trade.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "商品信息查询数据模型", description = "商品信息查询数据模型")
public class GoodsQueryDto {
	/**
	 * 分类名称
	 **/
	@ApiModelProperty(value = "分类ID，默认all(全部)")
	private String categoryId = "all";
	
	/**
	 * 新旧
	 */
	@ApiModelProperty("新旧，默认0，0全部，1Brand New，2Almost New，3Gently Use")
	private Integer quality = 0;
	
	@ApiModelProperty("排序，默认0，0default，1popular，2low to high，3high to low")
	private Integer sort = 0;
}
