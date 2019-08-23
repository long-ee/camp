package com.campgem.modules.trade.dto.manage;

import com.campgem.common.api.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "【管理端】商品信息查询数据模型", description = "【管理端】商品信息查询数据模型")
public class MGoodsQueryDto extends BaseDto {
	@ApiModelProperty(value = "商品名称")
	private String goodsName;
	
	@ApiModelProperty("商家名")
	private String businessName;
}
