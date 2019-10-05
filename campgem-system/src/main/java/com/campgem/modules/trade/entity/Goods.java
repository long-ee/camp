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
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 商品信息
 * @Author: ling
 * @Date: 2019-08-15
 * @Version: V1.0
 */
@Data
@TableName("goods")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "goods对象", description = "商品信息")
public class Goods {
	/**
	 * id
	 */
	@TableId(type = IdType.UUID)
	@ApiModelProperty("id")
	private String id;
	
	/**
	 * 分类图标
	 */
	@ApiModelProperty(value = "卖家ID", hidden = true)
	private String uid;
	
	/**
	 * 卖家名称
	 */
	@ApiModelProperty("卖家名称")
	private String memberName;
	
	/**
	 * 分类名称
	 */
	@ApiModelProperty("身份类别")
	private String memberType;
	
	/**
	 * 分类ID
	 */
	@ApiModelProperty("分类ID")
	private String categoryId;
	
	/**
	 * 城市ID
	 */
	@ApiModelProperty("城市ID")
	private String cityId;
	
	/**
	 * 大学ID
	 */
	@ApiModelProperty("大学ID")
	private String universityId;
	
	/**
	 * 结束日期
	 */
	@ApiModelProperty("结束日期")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	@ApiModelProperty("新旧程度 identity时2有效 1:Brand new 2:Almost new 3:Gently used")
	private Integer quality;
	
	/**
	 * 原价
	 */
	@ApiModelProperty("原价")
	private BigDecimal originPrice;
	
	/**
	 * 现价
	 */
	@ApiModelProperty("现价")
	private BigDecimal salePrice;
	
	@ApiModelProperty("商品库存，学生/普通会员商品")
	private Integer stock;
	
	/**
	 * 商品名
	 */
	@ApiModelProperty("商品名")
	private String goodsName;
	
	/**
	 * 状态
	 */
	@ApiModelProperty("状态，0:in sale -1:off shelf -2:sold -3:expired")
	private Integer status;
	
	/**
	 * 税率
	 */
	@ApiModelProperty("税率")
	private BigDecimal taxes;
	
	/**
	 * 标签
	 */
	@ApiModelProperty("标签，多个以,分隔")
	private String tags;
	
	/**
	 * 商品描述
	 */
	@ApiModelProperty("商品描述")
	private String description;
	
	/**
	 * 留言次数
	 */
	@ApiModelProperty("留言次数")
	private Integer reviewCount;
	
	/**
	 * 删除状态
	 */
	@ApiModelProperty(value = "删除状态", hidden = true)
	@TableLogic
	private Integer delFlag;
	
	/**
	 * createBy
	 */
	@ApiModelProperty(value = "createBy", hidden = true)
	private String createBy;
	
	/**
	 * createTime
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "发布时间")
	private Date createTime;
	
	/**
	 * updateTime
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "updateTime", hidden = true)
	private Date updateTime;
	
	/**
	 * updateBy
	 */
	@ApiModelProperty(value = "updateBy", hidden = true)
	private String updateBy;
}
