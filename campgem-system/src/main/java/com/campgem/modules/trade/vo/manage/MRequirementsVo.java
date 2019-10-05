package com.campgem.modules.trade.vo.manage;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.campgem.modules.trade.entity.RequirementsImages;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description: 需求添加/编辑模型
 * @Author: ling
 * @Date: 2019-08-17
 * @Version: V1.0
 */
@Data
@TableName("requirements")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "需求添加/编辑模型", description = "需求添加/编辑模型")
public class MRequirementsVo {
	
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private String id;
	
	@Excel(name = "用户ID", width = 15)
	@ApiModelProperty(value = "用户ID")
	private String uid;
	
	@Excel(name = "分类ID", width = 15)
	@ApiModelProperty(value = "分类ID")
	@NotBlank(message = "分类不能为空")
	private String categoryId;
	
	@Excel(name = "需求名", width = 15)
	@ApiModelProperty(value = "需求名")
	@NotBlank(message = "需求名称不能为空")
	private String requirementName;
	
	@Excel(name = "购买价格", width = 15)
	@ApiModelProperty(value = "购买价格")
	@NotNull(message = "求购价格不能为空")
	private java.math.BigDecimal buyingPrice;
	
	@Excel(name = "需求描述", width = 15)
	@ApiModelProperty(value = "需求描述")
	@NotBlank(message = "需求描述不能为空")
	private String requirementDescription;
	
	@ApiModelProperty("需求图片")
	private RequirementsImages[] requirementsImages;
}
