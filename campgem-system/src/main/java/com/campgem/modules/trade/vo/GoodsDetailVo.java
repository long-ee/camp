package com.campgem.modules.trade.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.campgem.modules.trade.entity.GoodsImages;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "商品详情显示数据模型")
public class GoodsDetailVo implements Serializable {
	@TableId(type = IdType.UUID)
	@ApiModelProperty("id")
	private String id;
	
	@ApiModelProperty("卖家名称")
	private String sellerName;
	
	@ApiModelProperty("身份类别，1Business，2Student/Individual")
	private Integer identity;
	
	@ApiModelProperty("留言数")
	private Integer reviewCount;
	
	@ApiModelProperty("城市名，identity=1有效")
	private String cityName;
	
	@ApiModelProperty("大学名，identity=2有效")
	private String universityName;
	
	@ApiModelProperty("商品图片")
	private List<GoodsImages> goodsImages;
	
	@ApiModelProperty("商品规格")
	private List<GoodsSpecificationsVo> goodsSpecifications;
	
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
	
	@ApiModelProperty("状态，1:in sale 2:off shelf 3:sold 4:expired")
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
