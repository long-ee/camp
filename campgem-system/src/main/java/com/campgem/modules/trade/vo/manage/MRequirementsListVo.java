package com.campgem.modules.trade.vo.manage;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 需求
 * @Author: ling
 * @Date: 2019-08-17
 * @Version: V1.0
 */
@Data
@ApiModel(value = "需求前端显示模型对象", description = "需求前端显示模型对象")
public class MRequirementsListVo {
	
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "卖家名称")
	private String sellerName;
	
	@ApiModelProperty("卖家所在城市")
	private String cityName;
	
	@ApiModelProperty(value = "需求名")
	private String requirementName;
	
	@ApiModelProperty("0Enable，-1Disable")
	private Integer status;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "发布时间")
	private Date createTime;
}
