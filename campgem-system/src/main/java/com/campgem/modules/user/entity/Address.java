package com.campgem.modules.user.entity;

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
 * @Description: 用户地址管理
 * @Author: campgem
 * @Date: 2019-08-18
 * @Version: V1.0
 */
@Data
@TableName("address")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "address对象", description = "用户地址管理")
public class Address {
	
	/**
	 * id
	 */
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private java.lang.String id;
	/**
	 * uid
	 */
	@Excel(name = "uid", width = 15)
	@ApiModelProperty(value = "uid")
	private java.lang.String uid;
	/**
	 * firstName
	 */
	@Excel(name = "firstName", width = 15)
	@ApiModelProperty(value = "firstName")
	private java.lang.String firstName;
	/**
	 * lastName
	 */
	@Excel(name = "lastName", width = 15)
	@ApiModelProperty(value = "lastName")
	private java.lang.String lastName;
	/**
	 * phone
	 */
	@Excel(name = "phone", width = 15)
	@ApiModelProperty(value = "phone")
	private java.lang.String phone;
	/**
	 * 国家
	 */
	@Excel(name = "国家", width = 15)
	@ApiModelProperty(value = "国家")
	private java.lang.String country;
	/**
	 * 州
	 */
	@Excel(name = "州", width = 15)
	@ApiModelProperty(value = "州")
	private java.lang.String state;
	/**
	 * 城市
	 */
	@Excel(name = "城市", width = 15)
	@ApiModelProperty(value = "城市")
	private java.lang.String city;
	/**
	 * 街道地址
	 */
	@Excel(name = "街道地址", width = 15)
	@ApiModelProperty(value = "街道地址")
	private java.lang.String streetAddress;
	/**
	 * 具体地址
	 */
	@Excel(name = "具体地址", width = 15)
	@ApiModelProperty(value = "具体地址")
	private java.lang.String detailAddress;
	/**
	 * 邮政编码
	 */
	@Excel(name = "邮政编码", width = 15)
	@ApiModelProperty(value = "邮政编码")
	private java.lang.String postcode;
	/**
	 * 账单地址是否与收货地址一样
	 */
	@Excel(name = "账单地址是否与收货地址一样", width = 15)
	@ApiModelProperty(value = "账单地址是否与收货地址一样")
	private java.lang.Integer isBillingSame;
	/**
	 * 账单收货人名
	 */
	@Excel(name = "账单收货人名", width = 15)
	@ApiModelProperty(value = "账单收货人名")
	private java.lang.String bFirstName;
	/**
	 * 账单收货人姓
	 */
	@Excel(name = "账单收货人姓", width = 15)
	@ApiModelProperty(value = "账单收货人姓")
	private java.lang.String bLastName;
	/**
	 * 账单手机
	 */
	@Excel(name = "账单手机", width = 15)
	@ApiModelProperty(value = "账单手机")
	private java.lang.String bPhone;
	/**
	 * bCountry
	 */
	@Excel(name = "bCountry", width = 15)
	@ApiModelProperty(value = "bCountry")
	private java.lang.String bCountry;
	/**
	 * bState
	 */
	@Excel(name = "bState", width = 15)
	@ApiModelProperty(value = "bState")
	private java.lang.String bState;
	/**
	 * bCity
	 */
	@Excel(name = "bCity", width = 15)
	@ApiModelProperty(value = "bCity")
	private java.lang.String bCity;
	/**
	 * bStreetAddress
	 */
	@Excel(name = "bStreetAddress", width = 15)
	@ApiModelProperty(value = "bStreetAddress")
	private java.lang.String bStreetAddress;
	/**
	 * bDetailAddress
	 */
	@Excel(name = "bDetailAddress", width = 15)
	@ApiModelProperty(value = "bDetailAddress")
	private java.lang.String bDetailAddress;
	/**
	 * bPostcode
	 */
	@Excel(name = "bPostcode", width = 15)
	@ApiModelProperty(value = "bPostcode")
	private java.lang.String bPostcode;
	/**
	 * delFlag
	 */
	@Excel(name = "delFlag", width = 15)
	@ApiModelProperty(value = "delFlag", hidden = true)
	private java.lang.Integer delFlag;
	
	@ApiModelProperty("是否默认")
	private Integer isDefault;
	/**
	 * createTime
	 */
	@Excel(name = "createTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
}
