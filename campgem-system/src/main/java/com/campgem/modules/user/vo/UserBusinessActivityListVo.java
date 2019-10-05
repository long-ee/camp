package com.campgem.modules.user.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 用户活动显示模型对象
 * @Author: ling
 * @Date: 2019-08-29
 * @Version: V1.0
 */
@Data
@ApiModel(value = "用户活动显示模型对象", description = "用户活动显示模型对象")
public class UserBusinessActivityListVo {
	
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "活动名称")
	private String title;
	
	@ApiModelProperty("活动发布时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
}
