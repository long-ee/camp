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
 * @Description: 心愿
 * @Author: campgem
 * @Date: 2019-08-18
 * @Version: V1.0
 */
@Data
@TableName("wishes")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "心愿单对象", description = "心愿单对象")
public class Wishes {
	
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private java.lang.String id;
	
	@Excel(name = "用户ID", width = 15)
	@ApiModelProperty(value = "用户ID")
	private java.lang.String uid;
	
	@Excel(name = "添加的商品ID", width = 15)
	@ApiModelProperty(value = "添加的商品ID")
	private java.lang.String goodsId;
	
	@Excel(name = "商品的规格ID", width = 15)
	@ApiModelProperty(value = "商品的规格ID")
	private java.lang.String specificationId;
	
	@ApiModelProperty(value = "删除状态", hidden = true)
	@TableLogic
	private Integer delFlag;
	
	@Excel(name = "添加时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "添加时间")
	private java.util.Date createTime;
}
