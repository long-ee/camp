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

/**
 * @Description: 商品留言屏蔽
 * @Author: ling
 * @Date: 2019-08-17
 * @Version: V1.0
 */
@Data
@TableName("goods_reviews_shields")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "goods_reviews_shields对象", description = "商品留言屏蔽")
public class GoodsReviewsShields {
	
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private java.lang.String id;
	
	@ApiModelProperty(value = "商品ID")
	private java.lang.String goodsId;
	
	@ApiModelProperty(value = "被屏蔽的用户ID")
	private java.lang.String shieldUid;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "屏蔽时间")
	private java.util.Date createTime;
}
