package com.campgem.modules.user.entity;

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
 * @Description: 用户认证信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Data
@TableName("user_auth")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="user_auth对象", description="用户认证信息")
public class UserAuth {
    
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private String id;
	/**用户基础信息ID*/
	@Excel(name = "用户基础信息ID", width = 15)
    @ApiModelProperty(value = "用户基础信息ID")
	private String userBaseId;
	/**用户认证（邮箱、手机等）*/
	@Excel(name = "用户认证（邮箱、手机等）", width = 15)
    @ApiModelProperty(value = "用户认证（邮箱、手机等）")
	private String identifier;
	/**用户认证类别*/
	@Excel(name = "用户认证类别", width = 15)
    @ApiModelProperty(value = "用户认证类别")
	private String identityType;
	/**用户凭证（token）*/
	@Excel(name = "用户凭证（token）", width = 15)
    @ApiModelProperty(value = "用户凭证（token）")
	private String certificate;
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
