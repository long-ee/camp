package com.campgem.modules.service.vo.manage;

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
public class MServiceDetailVo {
	
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "关联的会员ID")
	private String uid;
	
	@ApiModelProperty(value = "服务分类")
	private String categoryId;
	
	@ApiModelProperty(value = "价格")
	private java.math.BigDecimal salePrice;
	
	@ApiModelProperty(value = "服务名称")
	private String serviceName;
	
	@ApiModelProperty("图片列表")
	private List<ServiceImages> images;
	
	@ApiModelProperty(value = "标签，以,分隔")
	private String tags;
	
	@ApiModelProperty("税率")
	private String taxes;
	
	@ApiModelProperty("描述")
	private String description;
}
