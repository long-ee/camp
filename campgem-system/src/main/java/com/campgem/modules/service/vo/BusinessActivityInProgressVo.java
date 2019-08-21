package com.campgem.modules.service.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 进行中商家活动列表显示模型对象
 * @Author: ling
 * @Date: 2019-08-21
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "进行中商家活动列表显示模型对象", description = "进行中商家活动列表显示模型对象")
public class BusinessActivityInProgressVo {
	
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty("活动标题")
	private String title;
	
	@ApiModelProperty("封面图")
	private String listImage;
}
