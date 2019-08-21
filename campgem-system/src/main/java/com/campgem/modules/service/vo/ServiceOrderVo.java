package com.campgem.modules.service.vo;

import com.campgem.modules.service.entity.ServiceImages;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 服务详情下单显示模型对象
 * @Author: ling
 * @Date: 2019-08-21
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "服务详情下单显示模型对象", description = "服务详情下单显示模型对象")
public class ServiceOrderVo {
	
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "商家名")
	private String businessName;
	
	@ApiModelProperty(value = "价格")
	private java.math.BigDecimal salePrice;
	
	@ApiModelProperty("税率")
	private BigDecimal taxes;
	
	@ApiModelProperty(value = "服务名称")
	private String serviceName;
	
	@ApiModelProperty("图片列表")
	private List<ServiceImages> images;
}
