package com.campgem.modules.service.vo.manage;

import com.campgem.modules.service.entity.ServiceImages;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Description: 服务添加/编辑模型对象
 * @Author: ling
 * @Date: 2019-08-23
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "服务添加/编辑模型对象", description = "服务添加/编辑模型对象")
public class MServiceVo {
	
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "商家ID")
	private String uid;
	
	@ApiModelProperty("分类ID")
	@NotNull(message = "分类不能为空")
	private String categoryId;
	
	@ApiModelProperty("价格")
	@NotNull(message = "价格不能为空")
	private BigDecimal salePrice;
	
	@ApiModelProperty(value = "服务名称")
	@NotNull(message = "服务名称不能为空")
	private String serviceName;
	
	@ApiModelProperty("标签")
	private String tags;
	
	@ApiModelProperty("税率")
	private BigDecimal taxes;
	
	@ApiModelProperty("服务图片")
	@NotEmpty(message = "服务图片不能为空")
	private ServiceImages[] serviceImages;
	
	@ApiModelProperty("服务描述")
	@NotNull(message = "服务描述不能为空")
	private String description;
}
