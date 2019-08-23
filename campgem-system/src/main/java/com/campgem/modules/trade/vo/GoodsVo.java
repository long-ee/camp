package com.campgem.modules.trade.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value = "商品显示数据模型")
public class GoodsVo implements Serializable {
	@TableId(type = IdType.UUID)
	@ApiModelProperty("id")
	private String id;
	
	@ApiModelProperty("卖家名称")
	private String sellerName;
	
	@ApiModelProperty("身份类别，1Business，2Student/Individual")
	private Integer identity;
	
	@ApiModelProperty("分类名")
	private String categoryName;
	
	@ApiModelProperty("城市名，identity=1有效")
	private String cityName;
	
	@ApiModelProperty("大学名，identity=2有效")
	private String universityName;
	
	@ApiModelProperty("商品图片")
	private String goodsIcon;
	
	@ApiModelProperty("结束日期")
	private Date endDate;
	
	@ApiModelProperty("新旧程度 identity是2有效 1:Brand new 2:Almost new 3:Gently used")
	private Integer quality;
	
	@ApiModelProperty("原价，identity=2有效")
	private BigDecimal originPrice;
	
	@ApiModelProperty("现价，identity=2有效")
	private BigDecimal salePrice;
	
	@ApiModelProperty("商品名")
	private String goodsName;
	
	@ApiModelProperty("状态，0:in sale -1:off shelf -2:sold -3:expired")
	private Integer status;
	
	@ApiModelProperty("税率")
	private BigDecimal taxes;
	
	@ApiModelProperty("标签，多个以,分隔")
	private String tags;
	
	@ApiModelProperty("商品描述")
	private String description;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "发布时间")
	private Date createTime;
}
