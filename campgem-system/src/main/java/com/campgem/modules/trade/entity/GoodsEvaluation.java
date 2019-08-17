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

import java.io.Serializable;

/**
 * @Description: 商品评价
 * @Author: ling
 * @Date: 2019-08-17
 * @Version: V1.0
 */
@Data
@TableName("goods_evaluation")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "goods_evaluation对象", description = "商品评价")
public class GoodsEvaluation implements Serializable {
	
	/**
	 * id
	 */
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private java.lang.String id;
	/**
	 * 用户ID
	 */
	@Excel(name = "用户ID", width = 15)
	@ApiModelProperty(value = "用户ID")
	private java.lang.String uid;
	/**
	 * 用户ID
	 */
	@Excel(name = "商品ID", width = 15)
	@ApiModelProperty(value = "商品ID")
	private String goodsId;
	/**
	 * 评价内容
	 */
	@Excel(name = "评价内容", width = 15)
	@ApiModelProperty(value = "评价内容")
	private java.lang.Object content;
	/**
	 * 评价星级
	 */
	@Excel(name = "评价星级", width = 15)
	@ApiModelProperty(value = "评价星级")
	private java.lang.Integer rating;
	
	@ApiModelProperty(value = "删除状态", hidden = true)
	@TableLogic
	private Integer delFlag;
	
	/**
	 * 评价时间
	 */
	@Excel(name = "评价时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "评价时间")
	private java.util.Date createTime;
}
