package com.campgem.modules.service.vo.manage;

import com.campgem.modules.service.entity.ServiceImages;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @Description: 服务后端添加模型对象
 * @Author: ling
 * @Date: 2019-08-23
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "服务后端添加模型对象", description = "服务后端添加模型对象")
public class MServiceVo {
	
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "商家ID")
	private String uid;
	
	@ApiModelProperty(value = "服务分类")
	private String categoryId;
	
	@ApiModelProperty(value = "价格")
	private java.math.BigDecimal salePrice;
	
	@ApiModelProperty(value = "服务名称")
	private String serviceName;
	
	@ApiModelProperty(value = "标签，以,分隔")
	private String tags;
	
	@ApiModelProperty("税率")
	private BigDecimal taxes;
	
	@ApiModelProperty("描述")
	private String description;
	
	@ApiModelProperty("图片")
	private ServiceImages[] images;
}
