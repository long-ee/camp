package com.campgem.modules.service.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 服务评价
 * @Author: ling
 * @Date: 2019-08-20
 * @Version: V1.0
 */
@Data
@ApiModel(value = "服务评价显示模型对象", description = "服务评价显示模型对象")
public class ServiceEvaluationVo {
	
	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private String id;
	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "用户ID")
	private String uid;
	
	@ApiModelProperty("用户头像")
	private String srcFace;
	
	@ApiModelProperty("用户昵称")
	private String nickName;
	/**
	 * 服务ID
	 */
	@ApiModelProperty(value = "服务ID")
	private String serviceId;
	/**
	 * 评价内容
	 */
	@ApiModelProperty(value = "评价内容")
	private Object content;
	/**
	 * 安全星级
	 */
	@ApiModelProperty(value = "安全星级")
	private Integer securityRating;
	/**
	 * 环境星级
	 */
	@ApiModelProperty(value = "环境星级")
	private Integer environmentRating;
	/**
	 * 服务星级
	 */
	@ApiModelProperty(value = "服务星级")
	private Integer serviceRating;
	/**
	 * 价格星级
	 */
	@ApiModelProperty(value = "价格星级")
	private Integer priceRating;
	/**
	 * 评价时间
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "评价时间")
	private Date createTime;
}
