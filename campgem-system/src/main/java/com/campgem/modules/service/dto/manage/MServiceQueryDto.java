package com.campgem.modules.service.dto.manage;

import com.campgem.common.api.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "后台服务查询数据模型", description = "后台服务查询数据模型")
public class MServiceQueryDto extends BaseDto {
	@ApiModelProperty("服务名称")
	private String serviceName;
	
	@ApiModelProperty("发布者")
	private Integer memberName;
}
