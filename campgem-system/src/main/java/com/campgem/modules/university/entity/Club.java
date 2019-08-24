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
 * @Description: 社团信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Data
@TableName("club")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="club对象", description="社团信息")
public class Club {
    
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private String id;
	/**社团所属分类*/
	@Excel(name = "社团所属分类", width = 15)
    @ApiModelProperty(value = "社团所属分类")
	@NotBlank(message = "请选择社团所属分类")
	private String categoryId;
	/**社团图标*/
	@Excel(name = "社团图标", width = 15)
    @ApiModelProperty(value = "社团图标")
	@NotBlank(message = "请上传社团图标")
	private String clubIcon;
	/**社团名称*/
	@Excel(name = "社团名称", width = 15)
    @ApiModelProperty(value = "社团名称")
	@NotBlank(message = "请填写社团名称")
	private String clubName;
	/**社团简介*/
	@Excel(name = "社团简介", width = 15)
    @ApiModelProperty(value = "社团简介")
	@NotBlank(message = "请填写社团介绍")
	private String information;
	/**社团创建人*/
	@Excel(name = "社团创建人", width = 15)
    @ApiModelProperty(value = "社团创建人")
	@NotBlank(message = "请选择社团创建人")
	private String creatorId;
	@Excel(name = "成员数量", width = 15)
	@ApiModelProperty(value = "成员数量")
	private Integer memberCount;
	@Excel(name = "成员用户ID", width = 15)
	@ApiModelProperty(value = "成员用户ID")
	private String memberIds;
	/**所属大学*/
	@Excel(name = "所属大学", width = 15)
    @ApiModelProperty(value = "所属大学")
	@NotBlank(message = "请选择社团所属大学")
	private String universityId;
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
