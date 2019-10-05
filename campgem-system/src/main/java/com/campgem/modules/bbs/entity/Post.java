package com.campgem.modules.bbs.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.validation.constraints.NotBlank;

/**
 * @Description: 版块信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Data
@TableName("post")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="post对象", description="版块信息")
public class Post {
    
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private String id;
	/**版块图片*/
	@Excel(name = "版块图片", width = 15)
    @ApiModelProperty(value = "版块图片")
	private String postIcon;
	/**版块名称*/
	@Excel(name = "版块名称", width = 15)
    @ApiModelProperty(value = "版块名称")
	private String postName;
	/**是否启用*/
	@Excel(name = "是否启用", width = 15)
	@ApiModelProperty(value = "是否启用")
	@NotBlank(message = "请选择是否启版块")
	private String enabled;
	/**主管理员ID*/
	@Excel(name = "主管理员ID", width = 15)
	@ApiModelProperty(value = "主管理员ID", hidden = true)
	private String primaryAdminId;
	/**所有管理员ID*/
	@Excel(name = "所有管理员ID", width = 15)
	@ApiModelProperty(value = "所有管理员ID")
	private String adminIds;
	/**删除状态*/
	@Excel(name = "删除状态", width = 15)
    @ApiModelProperty(value = "删除状态", hidden = true)
	@TableLogic
	private Integer delFlag;
	/**createBy*/
	@Excel(name = "createBy", width = 15)
    @ApiModelProperty(value = "createBy", hidden = true)
	private String createBy;
	/**createTime*/
	@Excel(name = "createTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime", hidden = true)
	private java.util.Date createTime;
	/**updateTime*/
	@Excel(name = "updateTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "updateTime", hidden = true)
	private java.util.Date updateTime;
	/**updateBy*/
	@Excel(name = "updateBy", width = 15)
    @ApiModelProperty(value = "updateBy", hidden = true)
	private String updateBy;
}
