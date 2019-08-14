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
 * @Description: 分类信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Data
@TableName("category")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="category对象", description="分类信息")
public class Category {
    
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private String id;
	/**分类图标*/
	@Excel(name = "分类图标", width = 15)
    @ApiModelProperty(value = "分类图标")
	private String categoryIcon;
	/**分类名称*/
	@Excel(name = "分类名称", width = 15)
    @ApiModelProperty(value = "分类名称")
	@NotBlank(message = "请填写分类名称")
	private String categoryName;
	/**分类类别*/
	@Excel(name = "分类类别", width = 15)
    @ApiModelProperty(value = "分类类别")
	@NotBlank(message = "请选择分类所属类别")
	private String categoryType;
	/**评价纬度*/
	@Excel(name = "评价纬度", width = 15)
    @ApiModelProperty(value = "评价纬度")
	private String evaluationDimension;
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
