package com.campgem.modules.trade.dto;

import com.campgem.common.api.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "需求留言数据模型", description = "需求留言数据模型")
public class RequirementReviewDto extends BaseDto {
	@ApiModelProperty("需求ID")
	@NotBlank(message = "需求ID不能为空")
	private String requirementId;
	
	@ApiModelProperty(value = "留言内容")
	@NotBlank(message = "留言内容不能为空")
	private String content;
}
