package com.campgem.modules.user.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description: 用户地址管理
 * @Author: campgem
 * @Date: 2019-08-18
 * @Version: V1.0
 */
@Data
@ApiModel(value = "用户地址显示模型对象", description = "用户地址显示模型对象")
public class OrdersDetailVo {
	
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty("下单用户ID")
	private String uid;
	
	@ApiModelProperty("卖家/商家ID")
	private String sellerId;
	
	@ApiModelProperty(value = "订单号")
	private String orderNo;
	
	@ApiModelProperty("订单类型")
	private String orderType;
	
	@ApiModelProperty("下单人，卖家显示")
	private String memberName;
	
	@ApiModelProperty("下单人联系电话，卖家显示")
	private String mobile;
	
	@ApiModelProperty("商品/服务提供方，买家显示")
	private String sellerName;
	
	@ApiModelProperty("角色，buyer买家，seller卖家")
	private String role;
	
	@ApiModelProperty("订单金额")
	private BigDecimal payAmount;
	
	@ApiModelProperty(value = "订单状态")
	private String status;
	
	@ApiModelProperty("配送方式")
	private String shipping;
	
	@ApiModelProperty("运送单号")
	private String trackingNumber;
	
	@ApiModelProperty("地址，只会有一条或者空")
	private List<OrdersAddressVo> address;
	
	@ApiModelProperty("商品列表")
	private List<OrdersGoodsVo> goods;
	
	@ApiModelProperty("服务数据，只会有一条或者空")
	private List<OrdersServiceVo> services;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "下单时间")
	private Date createTime;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "支付时间")
	private Date paymentTime;
}
