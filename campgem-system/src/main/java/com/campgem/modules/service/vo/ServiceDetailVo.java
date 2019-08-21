package com.campgem.modules.service.vo;

import com.campgem.modules.service.entity.ServiceImages;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Description: 服务详情前端显示模型对象
 * @Author: ling
 * @Date: 2019-08-20
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "服务详情前端显示模型对象", description = "服务详情前端显示模型对象")
public class ServiceDetailVo {
	
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "关联的会员ID")
	private String uid;
	
	@ApiModelProperty(value = "商家名")
	private String businessName;
	
	@ApiModelProperty(value = "商家会员类型")
	private String identity;
	
	@ApiModelProperty(value = "服务分类")
	private String categoryId;
	
	@ApiModelProperty(value = "价格")
	private java.math.BigDecimal salePrice;
	
	@ApiModelProperty("平均评分")
	private Float stars;
	
	@ApiModelProperty(value = "服务名称")
	private String serviceName;
	
	@ApiModelProperty("图片列表")
	private List<ServiceImages> images;
	
	@ApiModelProperty("关联服务")
	private List<ServiceRelatedVo> relatedServices;
	
	@ApiModelProperty("开门时间")
	private String openingTime;
	
	@ApiModelProperty("关门时间")
	private String closingTime;
	
	@ApiModelProperty("商家地址")
	private String address;
	
	@ApiModelProperty(value = "标签，以,分隔")
	private String tags;
	
	@ApiModelProperty("浏览次数")
	private Integer viewCount;
	
	@ApiModelProperty("售出数量")
	private Integer saleCount;
}
