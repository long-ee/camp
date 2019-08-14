package com.campgem.modules.university.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 用户基础信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Data
@TableName("user_base")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="user_base对象", description="用户基础信息")
public class UserBase {
    
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private String id;
	/**用户来源*/
	@Excel(name = "用户来源", width = 15)
    @ApiModelProperty(value = "用户来源")
	private String registerSource;
	/**用户名称*/
	@Excel(name = "用户名称", width = 15)
    @ApiModelProperty(value = "用户名称")
	private String username;
	/**用户状态*/
	@Excel(name = "用户状态", width = 15)
    @ApiModelProperty(value = "用户状态")
	private String userStatus;
	/**性别*/
	@Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
	private String gender;
	/**生日*/
	@Excel(name = "生日", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "生日")
	private java.util.Date birthday;
	/**邮箱*/
	@Excel(name = "邮箱", width = 15)
    @ApiModelProperty(value = "邮箱")
	private String email;
	/**邮箱绑定时间*/
	@Excel(name = "邮箱绑定时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "邮箱绑定时间")
	private java.util.Date emailBindTime;
	/**手机*/
	@Excel(name = "手机", width = 15)
    @ApiModelProperty(value = "手机")
	private String mobile;
	/**手机绑定时间*/
	@Excel(name = "手机绑定时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "手机绑定时间")
	private java.util.Date mobileBindTime;
	/**用户昵称*/
	@Excel(name = "用户昵称", width = 15)
    @ApiModelProperty(value = "用户昵称")
	private String nickName;
	/**头像*/
	@Excel(name = "头像", width = 15)
    @ApiModelProperty(value = "头像")
	private String face;
	/**头像200x200*/
	@Excel(name = "头像200x200", width = 15)
    @ApiModelProperty(value = "头像200x200")
	private String face200;
	/**头像原文件*/
	@Excel(name = "头像原文件", width = 15)
    @ApiModelProperty(value = "头像原文件")
	private String srcFace;
	/**删除状态*/
	@Excel(name = "删除状态", width = 15)
    @ApiModelProperty(value = "删除状态")
	@TableLogic
	private Integer delFlag;
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
