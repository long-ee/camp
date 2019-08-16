package com.campgem.modules.trade.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value = "购物车商品显示数据模型")
public class GoodsCartVo implements Serializable {
	@TableId(type = IdType.UUID)
	@ApiModelProperty("id")
	private String id;
	
	@ApiModelProperty("卖家名称")
	private String sellerName;
	
	@ApiModelProperty("身份类别，1Business，2Student/Individual")
	private Integer identity;
	
	@ApiModelProperty("商品图片")
	private String goodsIcon;
	
	@ApiModelProperty("结束日期")
	private Date endDate;
	
	@ApiModelProperty("原价，identity=2有效")
	private BigDecimal originPrice;
	
	@ApiModelProperty("现价")
	private BigDecimal salePrice;
	
	@ApiModelProperty("商品名")
	private String goodsName;
	
	@ApiModelProperty("状态，1:in sale 2:off shelf 3:sold 4:expired")
	private Integer status;
	
	@ApiModelProperty("税率")
	private BigDecimal taxes;
}
