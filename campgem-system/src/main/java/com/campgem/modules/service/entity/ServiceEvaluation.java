package com.campgem.modules.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 服务评价
 * @Author: campgem
 * @Date:   2019-08-20
 * @Version: V1.0
 */
@Data
@TableName("service_evaluation")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="service_evaluation对象", description="服务评价")
public class ServiceEvaluation {
    
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private String id;
	/**用户ID*/
	@Excel(name = "用户ID", width = 15)
    @ApiModelProperty(value = "用户ID")
	private String uid;
	/**服务ID*/
	@Excel(name = "服务ID", width = 15)
    @ApiModelProperty(value = "服务ID")
	private String serviceId;
	/**评价内容*/
	@Excel(name = "评价内容", width = 15)
    @ApiModelProperty(value = "评价内容")
	private Object content;
	/**安全星级*/
	@Excel(name = "安全星级", width = 15)
    @ApiModelProperty(value = "安全星级")
	private Integer securityRating;
	/**环境星级*/
	@Excel(name = "环境星级", width = 15)
    @ApiModelProperty(value = "环境星级")
	private Integer environmentRating;
	/**服务星级*/
	@Excel(name = "服务星级", width = 15)
    @ApiModelProperty(value = "服务星级")
	private Integer serviceRating;
	/**价格星级*/
	@Excel(name = "价格星级", width = 15)
    @ApiModelProperty(value = "价格星级")
	private Integer priceRating;
	/**删除状态*/
	@Excel(name = "删除状态", width = 15)
    @ApiModelProperty(value = "删除状态")
	private Integer delFlag;
	/**评价时间*/
	@Excel(name = "评价时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "评价时间")
	private Date createTime;
}
