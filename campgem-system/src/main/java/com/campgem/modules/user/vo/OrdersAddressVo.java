package com.campgem.modules.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 用户订单地址显示模型
 * @Author: ling
 * @Date: 2019-08-28
 * @Version: V1.0
 */
@Data
@ApiModel(value = "用户订单地址显示模型", description = "用户订单地址显示模型")
public class OrdersAddressVo {
	@ApiModelProperty(value = "firstName")
	private String firstName;
	
	@ApiModelProperty(value = "lastName")
	private String lastName;
	
	@ApiModelProperty(value = "phone")
	private String phone;
	
	@ApiModelProperty(value = "国家")
	private String country;
	
	@ApiModelProperty(value = "州")
	private String state;
	
	@ApiModelProperty(value = "城市")
	private String city;
	
	@ApiModelProperty(value = "街道地址")
	private String streetAddress;
	
	@ApiModelProperty(value = "具体地址")
	private String detailAddress;
	
	@ApiModelProperty(value = "邮政编码")
	private String postcode;
	
	@ApiModelProperty(value = "账单地址是否与收货地址一样，如果一样，则b开头的数据为空")
	private Integer isBillingSame;
	
	@ApiModelProperty(value = "账单收货人名")
	private String bFirstName;
	
	@ApiModelProperty(value = "账单收货人姓")
	private String bLastName;
	
	@ApiModelProperty(value = "账单手机")
	private String bPhone;
	
	@ApiModelProperty(value = "bCountry")
	private String bCountry;
	
	@ApiModelProperty(value = "bState")
	private String bState;
	
	@ApiModelProperty(value = "bCity")
	private String bCity;
	
	@ApiModelProperty(value = "bStreetAddress")
	private String bStreetAddress;
	
	@ApiModelProperty(value = "bDetailAddress")
	private String bDetailAddress;
	
	@ApiModelProperty(value = "bPostcode")
	private String bPostcode;
}
