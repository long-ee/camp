package com.campgem.modules.trade.dto;

import com.campgem.common.api.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "需求信息查询数据模型", description = "需求信息查询数据模型")
public class RequirementsQueryDto extends BaseDto {
	/**
	 * 分类名称
	 **/
	@ApiModelProperty(value = "分类ID，默认all(全部)")
	private String categoryId = "all";
	
	@ApiModelProperty("排序，默认0，0default，1popular，2low to high，3high to low")
	private Integer sort = 0;
}
