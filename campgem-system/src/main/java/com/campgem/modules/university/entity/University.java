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

import javax.validation.constraints.NotBlank;

/**
 * @Description: 校园信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Data
@TableName("university")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="university对象", description="校园信息")
public class University {
    
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private String id;
	/**所属城市*/
	@Excel(name = "所属城市", width = 15)
    @ApiModelProperty(value = "所属城市")
	@NotBlank(message = "请选择学校所属城市")
	private String cityId;
	/**大学名称*/
	@Excel(name = "大学名称", width = 15)
	@NotBlank(message = "请填写学校名称")
    @ApiModelProperty(value = "大学名称")
	private String universityName;
	/**大学图片*/
	@Excel(name = "大学图片", width = 15)
    @ApiModelProperty(value = "大学图片")
	@NotBlank(message = "请上传学校图片")
	private String universityImage;
	/**官网地址*/
	@Excel(name = "官网地址", width = 15)
    @ApiModelProperty(value = "官网地址")
	@NotBlank(message = "请填写学校官网地址")
	private String officialAddress;
	/**是否启用*/
	@Excel(name = "是否启用", width = 15)
    @ApiModelProperty(value = "是否启用")
	@NotBlank(message = "请选择是否启用学校信息")
	private String enabled;
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
