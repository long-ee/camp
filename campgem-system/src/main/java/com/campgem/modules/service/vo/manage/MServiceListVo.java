package com.campgem.modules.service.vo.manage;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 服务列表后端显示模型对象
 * @Author: ling
 * @Date: 2019-08-23
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "服务列表后端显示模型对象", description = "服务列表后端显示模型对象")
public class MServiceListVo {
	
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "商家名")
	private String memberName;
	
	@ApiModelProperty(value = "分类名称")
	private String categoryName;
	
	@ApiModelProperty(value = "城市")
	private String city;
	
	@ApiModelProperty(value = "服务名称")
	private String serviceName;
	
	@ApiModelProperty(value = "状态，0:Enable -1:Disable")
	private java.lang.Integer status;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
}
