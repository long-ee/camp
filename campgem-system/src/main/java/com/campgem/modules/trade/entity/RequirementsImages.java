package com.campgem.modules.trade.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 需求图片
 * @Author: campgem
 * @Date:   2019-08-18
 * @Version: V1.0
 */
@Data
@TableName("requirements_images")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="requirements_images对象", description="需求图片")
public class RequirementsImages {
    
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private java.lang.String id;
	/**需求ID*/
	@Excel(name = "需求ID", width = 15)
    @ApiModelProperty(value = "需求ID")
	private java.lang.String requirementId;
	/**图片地址*/
	@Excel(name = "图片地址", width = 15)
    @ApiModelProperty(value = "图片地址")
	private java.lang.String requirementImage;
}
