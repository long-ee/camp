package com.campgem.modules.service.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 商家主页服务前端显示模型对象
 * @Author: ling
 * @Date: 2019-08-21
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "商家主页服务前端显示模型对象", description = "商家主页服务前端显示模型对象")
public class BusinessServiceVo {
	
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "价格")
	private java.math.BigDecimal salePrice;
	
	@ApiModelProperty("平均评分")
	private Float stars;
	
	@ApiModelProperty(value = "服务名称")
	private String serviceName;
	
	@ApiModelProperty("列表图片")
	private String listImage;
	
	@ApiModelProperty("浏览次数")
	private Integer viewCount;
	
	@ApiModelProperty("售出数量")
	private Integer saleCount;
}
