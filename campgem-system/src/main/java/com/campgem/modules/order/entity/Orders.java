package com.campgem.modules.order.entity;

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
 * @Description: 订单
 * @Author: ling
 * @Date: 2019-08-19
 * @Version: V1.0
 */
@Data
@TableName("orders")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "order对象", description = "订单")
public class Orders {
	
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private java.lang.String id;
	
	@Excel(name = "用户ID", width = 15)
	@ApiModelProperty(value = "用户ID")
	private java.lang.String uid;
	@ApiModelProperty("PayPal支付标识")
	private String payId;
	@ApiModelProperty("订单编号")
	private String orderNo;
	
	@Excel(name = "收货地址ID", width = 15)
	@ApiModelProperty(value = "收货地址ID")
	private java.lang.String addressId;
	
	@Excel(name = "订单类型，1Service,2Product", width = 15)
	@ApiModelProperty(value = "订单类型，1Service,2Product")
	private java.lang.Integer orderType;
	
	@Excel(name = "状态，0Unpaid，1Paid，2Shipping，3Offline Trading", width = 15)
	@ApiModelProperty(value = "状态，0Unpaid，1Paid，2Shipping，3Offline Trading")
	private java.lang.Integer status;
	
	@Excel(name = "卖家ID", width = 15)
	@ApiModelProperty(value = "卖家ID")
	private java.lang.String sellerId;
	
	@Excel(name = "卖家名称", width = 15)
	@ApiModelProperty(value = "卖家名称")
	private java.lang.String sellerName;
	
	@Excel(name = "商品/服务金额", width = 15)
	@ApiModelProperty(value = "商品/服务金额")
	private java.math.BigDecimal amount;
	
	@Excel(name = "运费", width = 15)
	@ApiModelProperty(value = "运费")
	private java.math.BigDecimal freightAmount;
	
	@Excel(name = "税金", width = 15)
	@ApiModelProperty(value = "税金")
	private java.math.BigDecimal taxesAmount;
	
	@ApiModelProperty("Braintree支付使用")
	private String nonce;
	
	@Excel(name = "支付金额，amount+freight_amount+taxes_amount", width = 15)
	@ApiModelProperty(value = "支付金额，amount+freight_amount+taxes_amount")
	private java.math.BigDecimal payAmount;
	
	@Excel(name = "支付方式，1PayPal，2Visa/Masterd Card", width = 15)
	@ApiModelProperty(value = "支付方式，1PayPal，2Visa/Masterd Card")
	private java.lang.Integer paymentMethod;
	
	@Excel(name = "支付时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "支付时间")
	private java.util.Date paymentTime;
	
	@Excel(name = "配送方式", width = 15)
	@ApiModelProperty(value = "配送方式")
	private java.lang.String shipping;
	
	@Excel(name = "运送单号", width = 15)
	@ApiModelProperty(value = "运送单号")
	private java.lang.String trackingNumber;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "预约时间，针对Service类型订单")
	private java.util.Date appointment;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "下单时间")
	private java.util.Date createTime;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "过期时间")
	private Date expiredTime;
}
