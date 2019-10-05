package com.campgem.modules.trade.vo.manage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "商品列表显示数据模型")
public class MBusinessListVo implements Serializable {
	@ApiModelProperty("id")
	private String id;
	
	@ApiModelProperty("商家名称")
	private String businessName;
}
