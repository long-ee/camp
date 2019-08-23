package com.campgem.modules.trade.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.campgem.modules.trade.entity.RequirementsImages;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Description: 需求
 * @Author: ling
 * @Date: 2019-08-17
 * @Version: V1.0
 */
@Data
@ApiModel(value = "需求前端显示模型对象", description = "需求前端显示模型对象")
public class RequirementsDetailVo {
	
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "发布人ID")
	private String uid;
	
	@ApiModelProperty("发布人名称")
	private String memberName;
	
	@ApiModelProperty("发布人类型")
	private String memberType;
	
	@ApiModelProperty(value = "需求名")
	private String requirementName;
	
	@ApiModelProperty("需求图片")
	private List<RequirementsImages> requirementsImages;
	
	@ApiModelProperty(value = "购买价格")
	private java.math.BigDecimal buyingPrice;
	
	@ApiModelProperty("浏览次数")
	private Integer reviewCount;
	
	@ApiModelProperty(value = "需求描述")
	private String requirementDescription;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "createTime")
	private Date createTime;
}
