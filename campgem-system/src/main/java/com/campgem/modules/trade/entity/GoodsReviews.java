package com.campgem.modules.trade.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 商品留言
 * @Author: campgem
 * @Date: 2019-08-17
 * @Version: V1.0
 */
@Data
@TableName("goods_reviews")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "goods_reviews对象", description = "商品留言")
public class GoodsReviews {
	
	/**
	 * id
	 */
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private java.lang.String id;
	
	@Excel(name = "用户ID", width = 15)
	@ApiModelProperty(value = "用户ID")
	private java.lang.String uid;
	
	@Excel(name = "商品ID", width = 15)
	@ApiModelProperty(value = "商品ID")
	private java.lang.String goodsId;
	
	@Excel(name = "留言内容", width = 15)
	@ApiModelProperty(value = "留言内容")
	private java.lang.Object content;
	
	@ApiModelProperty(value = "删除状态", hidden = true)
	@TableLogic
	private Integer delFlag;
	
	@ApiModelProperty("是否公开")
	private Integer isOpen;
	
	@Excel(name = "留言时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "留言时间")
	private java.util.Date createTime;
}
