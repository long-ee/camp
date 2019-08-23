package com.campgem.modules.trade.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 心愿
 * @Author: ling
 * @Date: 2019-08-18
 * @Version: V1.0
 */
@Data
@ApiModel(value = "心愿单显示模型对象", description = "心愿单显示模型对象")
public class WishesVo {
	/**
	 * id
	 */
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "添加的商品ID")
	private String goodsId;
	
	@ApiModelProperty("商品图片")
	private String goodsIcon;
	
	@ApiModelProperty("原价，identity=2有效")
	private BigDecimal originPrice;
	
	@ApiModelProperty("现价，identity=2有效")
	private BigDecimal salePrice;
	
	@ApiModelProperty("商品名")
	private String goodsName;
	
	@ApiModelProperty("税率")
	private BigDecimal taxes;
	
	@ApiModelProperty("标签，多个以,分隔")
	private String tags;
	
	@ApiModelProperty("状态，0:in sale -1:off shelf -2:sold -3:expired")
	private Integer status;
	
	@ApiModelProperty("结束日期")
	private Date endDate;
	
	@ApiModelProperty(value = "商品的规格ID")
	private String specificationId;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "添加时间")
	private Date createTime;
}
