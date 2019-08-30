package com.campgem.modules.service.dto;

import com.campgem.common.api.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "商品留言数据模型", description = "商品留言数据模型")
public class ServiceOrderPayDto extends BaseDto {
	@ApiModelProperty("服务ID")
	@NotBlank(message = "服务ID不能为空")
	private String serviceId;
	
	@ApiModelProperty("支付方式，1PayPal，2Visa/Masterd Card")
	@NotNull(message = "支付方式不能为空")
	private Integer paymentMethod;
	
	@ApiModelProperty("预约日期")
	@NotBlank(message = "预约日期不能为空")
	private String date;
	
	@ApiModelProperty("预约时间")
	@NotBlank(message = "预约时间不能为空")
	private String time;
	
	@ApiModelProperty("Braintree支付需要")
	private String nonce;
}
