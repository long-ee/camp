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
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 需求
 * @Author: campgem
 * @Date: 2019-08-17
 * @Version: V1.0
 */
@Data
@TableName("requirements")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "requirements对象", description = "需求")
public class Requirements {
	
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private java.lang.String id;
	
	@Excel(name = "用户ID", width = 15)
	@ApiModelProperty(value = "用户ID")
	private java.lang.String uid;
	
	@Excel(name = "分类ID", width = 15)
	@ApiModelProperty(value = "分类ID")
	private java.lang.String categoryId;
	
	@Excel(name = "需求名", width = 15)
	@ApiModelProperty(value = "需求名")
	private java.lang.String requirementName;
	
	@Excel(name = "购买价格", width = 15)
	@ApiModelProperty(value = "购买价格")
	private java.math.BigDecimal buyingPrice;
	
	@Excel(name = "需求描述", width = 15)
	@ApiModelProperty(value = "需求描述")
	private java.lang.Object requirementDescription;
	
	@Excel(name = "删除状态", width = 15)
	@ApiModelProperty(value = "删除状态")
	private java.lang.Integer delFlag;
	
	@ApiModelProperty("需求留言数")
	private Integer reviewCount;
	
	@Excel(name = "createBy", width = 15)
	@ApiModelProperty(value = "createBy")
	private java.lang.String createBy;
	
	@Excel(name = "createTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
	
	@Excel(name = "updateBy", width = 15)
	@ApiModelProperty(value = "updateBy")
	private java.lang.String updateBy;
	
	@Excel(name = "updateTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "updateTime")
	private java.util.Date updateTime;
}
