package com.campgem.modules.trade.vo.manage;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.campgem.modules.trade.entity.GoodsImages;
import com.campgem.modules.trade.entity.GoodsSpecifications;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("goods")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "goods对象", description = "商品信息")
public class MGoodsVo {
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
	@NotBlank(message = "卖家不能为空")
	private String uid;
	
	/**
	 * 分类ID
	 */
	@ApiModelProperty("分类ID")
	@NotBlank(message = "分类不能为空")
	private String categoryId;
	
	/**
	 * 结束日期
	 */
	@ApiModelProperty("结束日期")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	/**
	 * 新旧程度
	 */
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
	@NotNull(message = "现价不能为空")
	private BigDecimal salePrice;
	
	/**
	 * 商品名
	 */
	@ApiModelProperty("商品名")
	private String goodsName;
	
	/**
	 * 状态
	 */
	@ApiModelProperty("状态，0:in sale -1:off shelf -2:sold -3:expired")
	private Integer status = 0;
	
	/**
	 * 税率
	 */
	@ApiModelProperty("税率")
	private BigDecimal taxes = new BigDecimal(0);
	
	/**
	 * 标签
	 */
	@ApiModelProperty("标签，多个以,分隔")
	private String tags;
	
	/**
	 * 商品描述
	 */
	@ApiModelProperty("商品描述")
	@NotBlank(message = "描述不能为空")
	private String description;
	
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
	
	@ApiModelProperty("上传的图片地址列表")
	@NotEmpty(message = "图片不能为空")
	private GoodsImages[] images;
	
	@ApiModelProperty("规格")
	private GoodsSpecifications[] specs;
}
