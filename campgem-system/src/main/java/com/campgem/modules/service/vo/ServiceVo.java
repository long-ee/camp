package com.campgem.modules.service.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 服务前端显示模型对象
 * @Author: ling
 * @Date: 2019-08-20
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "服务前端显示模型对象", description = "服务前端显示模型对象")
public class ServiceVo {
	
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "关联的会员ID")
	private String uid;
	
	@ApiModelProperty(value = "卖家名")
	private String sellerName;
	
	@ApiModelProperty(value = "卖家会员类型")
	private String identity;
	
	@ApiModelProperty(value = "服务分类")
	private String categoryId;
	
	@ApiModelProperty(value = "价格")
	private java.math.BigDecimal salePrice;
	
	@ApiModelProperty("平均评分")
	private Float stars;
	
	@ApiModelProperty(value = "服务名称")
	private String serviceName;
	
	@ApiModelProperty("列表图片")
	private String listImage;
	
	@ApiModelProperty(value = "标签，以,分隔")
	private String tags;
	
	@ApiModelProperty("浏览次数")
	private Integer viewCount;
	
	@ApiModelProperty("售出数量")
	private Integer saleCount;
}
