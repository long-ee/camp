package com.campgem.modules.trade.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 购物车
 * @Author: ling
 * @Date: 2019-08-16
 * @Version: V1.0
 */
@Data
@TableName("cart")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "购物车", description = "购物车信息")
public class Cart {
	/**
	 * id
	 */
	@TableId(type = IdType.UUID)
	@ApiModelProperty("id")
	private String id;
	
	@ApiModelProperty(value = "会员ID", hidden = true)
	private String uid;
	
	@ApiModelProperty(value = "卖家ID", hidden = true)
	private String sellerId;
	
	@ApiModelProperty("卖家名称")
	private String sellerName;
	
	@ApiModelProperty("添加的商品ID")
	private String goodsId;
	
	@ApiModelProperty("规格ID")
	private String specificationsId;
	
	@ApiModelProperty("商品数量")
	private Integer quantity;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "发布时间")
	private Date createTime;
}
