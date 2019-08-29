package com.campgem.modules.user.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 用户地址管理
 * @Author: campgem
 * @Date: 2019-08-18
 * @Version: V1.0
 */
@Data
@ApiModel(value = "用户地址显示模型对象", description = "用户地址显示模型对象")
public class OrdersListVo {
	
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "订单号")
	private String orderNo;
	
	@ApiModelProperty(value = "商品/服务名称")
	private String name;
	
	@ApiModelProperty("角色，buyer买家，seller卖家")
	private String role;
	
	@ApiModelProperty(value = "订单金额")
	private String payAmount;
	
	@ApiModelProperty(value = "订单状态")
	private String status;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "createTime")
	private Date createTime;
}
