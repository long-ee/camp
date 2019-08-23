package com.campgem.modules.trade.vo.manage;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "商品列表显示数据模型")
public class MGoodsListVo implements Serializable {
	@ApiModelProperty("id")
	private String id;
	
	@ApiModelProperty("商家/卖家类型")
	private String memberType;
	
	@ApiModelProperty("商家/卖家名称")
	private String memberName;
	
	@ApiModelProperty("城市")
	private String city;
	
	@ApiModelProperty("商品名")
	private String goodsName;
	
	@ApiModelProperty("分类名")
	private String categoryName;
	
	@ApiModelProperty("状态，1:in sale 2:off shelf 3:sold 4:expired")
	private Integer status;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "发布时间")
	private Date createTime;
}
