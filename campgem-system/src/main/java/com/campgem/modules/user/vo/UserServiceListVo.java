package com.campgem.modules.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 用户服务显示模型对象
 * @Author: ling
 * @Date: 2019-08-29
 * @Version: V1.0
 */
@Data
@ApiModel(value = "用户服务显示模型对象", description = "用户服务显示模型对象")
public class UserServiceListVo {
	
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "列表图片")
	private String listImage;
	
	@ApiModelProperty(value = "服务名称")
	private String serviceName;
	
	@ApiModelProperty("价格")
	private String salePrice;
	
	@ApiModelProperty(value = "服务状态")
	private String status;
}
