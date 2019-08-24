package com.campgem.modules.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * @Description: 用户扩展信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Data
@TableName("member")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="member对象", description="用户扩展信息")
public class Member {
    
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private String id;
	/**用户基础信息ID*/
	@Excel(name = "用户基础信息ID", width = 15)
    @ApiModelProperty(value = "用户基础信息ID")
	private String userBaseId;
	/**城市ID*/
	@Excel(name = "城市ID", width = 15)
    @ApiModelProperty(value = "城市ID")
	private String cityId;
	/**学校ID*/
	@Excel(name = "学校ID", width = 15)
    @ApiModelProperty(value = "学校ID")
	private String universityId;
	/**会员名称*/
	@Excel(name = "会员名称", width = 15)
    @ApiModelProperty(value = "会员名称")
	private String memberName;
	/**会员类型*/
	@Excel(name = "会员类型", width = 15)
    @ApiModelProperty(value = "会员类型")
	private String memberType;
	/**营业日期*/
	@Excel(name = "营业日期", width = 15)
    @ApiModelProperty(value = "营业日期")
	private String businessDate;
	/**商家名称*/
	@Excel(name = "商家名称", width = 15)
    @ApiModelProperty(value = "商家名称")
	private String businessName;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String address;
	/**营业开始时间*/
	@Excel(name = "营业开始时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "营业开始时间")
	private java.util.Date openingTime;
	/**营业结束时间*/
	@Excel(name = "营业结束时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "营业结束时间")
	private java.util.Date closingTime;
	/**专业*/
	@Excel(name = "专业", width = 15)
    @ApiModelProperty(value = "专业")
	private String major;
	/**入学时间*/
	@Excel(name = "入学时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "入学时间")
	private java.util.Date admissionDate;
	/**毕业时间*/
	@Excel(name = "毕业时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "毕业时间")
	private java.util.Date graduationDate;
	/**支付账号*/
	@Excel(name = "支付账号", width = 15)
    @ApiModelProperty(value = "支付账号")
	private String payPal;
	/**拓展字段1*/
	@Excel(name = "拓展字段1", width = 15)
    @ApiModelProperty(value = "拓展字段1")
	private String extend_1;
	/**拓展字段2*/
	@Excel(name = "拓展字段2", width = 15)
    @ApiModelProperty(value = "拓展字段2")
	private String extend_2;
	/**拓展字段3*/
	@Excel(name = "拓展字段3", width = 15)
    @ApiModelProperty(value = "拓展字段3")
	private String extend_3;
	/**删除状态*/
	@Excel(name = "删除状态", width = 15)
    @ApiModelProperty(value = "删除状态")
	@TableLogic
	private Integer delFlag;
	@ApiModelProperty("配送方式")
	private String shippingMethods;
	/**是否允许私信*/
	@ApiModelProperty(value = "是否允许私信")
	private boolean allowChat;
	/**createBy*/
	@Excel(name = "createBy", width = 15)
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**createTime*/
	@Excel(name = "createTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
	/**updateTime*/
	@Excel(name = "updateTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "updateTime")
	private java.util.Date updateTime;
	/**updateBy*/
	@Excel(name = "updateBy", width = 15)
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
}
