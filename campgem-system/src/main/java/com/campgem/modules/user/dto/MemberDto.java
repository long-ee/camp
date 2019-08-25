package com.campgem.modules.user.dto;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.campgem.common.api.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

@Data
@ApiModel(value="用户信息编辑数据模型",description="用户信息编辑数据模型")
public class MemberDto extends BaseDto implements Serializable {
    /**id*/
    @ApiModelProperty(value = "id")
    private String id;
    /**用户基础信息ID*/
    @ApiModelProperty(value = "用户基础信息ID")
    private String userBaseId;
    /**城市ID*/
    @ApiModelProperty(value = "城市ID")
    private String cityId;
    /**学校ID*/
    @ApiModelProperty(value = "学校ID")
    private String universityId;
    /**会员名称*/
    @ApiModelProperty(value = "会员名称")
    private String memberName;
    /**会员类型*/
    @ApiModelProperty(value = "会员类型")
    private String memberType;
    /**营业日期*/
    @ApiModelProperty(value = "营业日期")
    private String businessDate;
    /**商家名称*/
    @ApiModelProperty(value = "商家名称")
    private String businessName;
    /**地址*/
    @ApiModelProperty(value = "地址")
    private String address;
    /**营业开始时间*/
    @ApiModelProperty(value = "营业开始时间")
    private String openingTime;
    /**营业结束时间*/
    @ApiModelProperty(value = "营业结束时间")
    private String closingTime;
    /**专业*/
    @ApiModelProperty(value = "专业")
    private String major;
    /**爱好*/
    @ApiModelProperty(value = "爱好")
    private String hobbies;
    @ApiModelProperty(value = "firstName")
    private String firstName;
    @ApiModelProperty(value = "lastName")
    private String lastName;
    /**入学时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "入学时间")
    private java.util.Date admissionDate;
    /**毕业时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "毕业时间")
    private java.util.Date graduationDate;
    /**支付账号*/
    @ApiModelProperty(value = "支付账号")
    private String payPal;
    /**拓展字段1*/
    @ApiModelProperty(value = "拓展字段1")
    private String extend_1;
    /**拓展字段2*/
    @ApiModelProperty(value = "拓展字段2")
    private String extend_2;
    /**拓展字段3*/
    @ApiModelProperty(value = "拓展字段3")
    private String extend_3;
    /**删除状态*/
    @ApiModelProperty(value = "删除状态")
    @TableLogic
    private Integer delFlag;
    /**是否允许私聊*/
    @ApiModelProperty(value = "是否允许私聊")
    private String allowChat;
    /**配送方式*/
    @ApiModelProperty("配送方式")
    private String shippingMethods;
    /**用户头像*/
    @ApiModelProperty("用户头像")
    private String face;
}
