package com.campgem.modules.trade.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel(value = "商品详情关联商品显示模型")
public class GoodsRelatedVo implements Serializable {
	@TableId(type = IdType.UUID)
	@ApiModelProperty("id")
	private String id;
	
	@ApiModelProperty("身份类别，1Business，2Student/Individual")
	private Integer identity;
	
	@ApiModelProperty("商品图片")
	private String goodsIcon;
	
	@ApiModelProperty("现价")
	private BigDecimal salePrice;
	
	@ApiModelProperty("商品名")
	private String goodsName;
}
