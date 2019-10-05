package com.campgem.modules.service.entity;

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
 * @Description: 服务图片
 * @Author: campgem
 * @Date:   2019-08-20
 * @Version: V1.0
 */
@Data
@TableName("service_images")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="service_images对象", description="服务图片")
public class ServiceImages {
    
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private java.lang.String id;
	/**商品ID*/
	@Excel(name = "商品ID", width = 15)
    @ApiModelProperty(value = "商品ID")
	private java.lang.String serviceId;
	/**图片地址*/
	@Excel(name = "图片地址", width = 15)
    @ApiModelProperty(value = "图片地址")
	private java.lang.String serviceImage;
	/**是否是列表图*/
	@Excel(name = "是否是列表图", width = 15)
    @ApiModelProperty(value = "是否是列表图")
	private java.lang.Integer isListImage;
}
