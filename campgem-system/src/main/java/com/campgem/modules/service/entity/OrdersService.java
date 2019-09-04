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

/**
 * @Description: 订单服务
 * @Author: campgem
 * @Date: 2019-08-28
 * @Version: V1.0
 */
@Data
@TableName("orders_service")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "orders_service对象", description = "订单服务")
public class OrdersService {
	
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private java.lang.String id;
	
	@Excel(name = "订单ID", width = 15)
	@ApiModelProperty(value = "订单ID")
	private java.lang.String orderId;
	
	@Excel(name = "服务ID", width = 15)
	@ApiModelProperty(value = "服务ID")
	private java.lang.String serviceId;
	
	@Excel(name = "服务名称", width = 15)
	@ApiModelProperty(value = "服务名称")
	private java.lang.String serviceName;
	
	@Excel(name = "服务图标", width = 15)
	@ApiModelProperty(value = "服务图标")
	private java.lang.String serviceIcon;
	
	@Excel(name = "预约时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "预约时间")
	private java.util.Date appointmentTime;
	
	@Excel(name = "税金", width = 15)
	@ApiModelProperty(value = "税金")
	private java.math.BigDecimal taxes;
	
	@Excel(name = "单价", width = 15)
	@ApiModelProperty(value = "单价")
	private java.math.BigDecimal price;
}
