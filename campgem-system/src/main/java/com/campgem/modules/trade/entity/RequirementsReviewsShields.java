package com.campgem.modules.trade.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 需求留言屏蔽
 * @Author: campgem
 * @Date: 2019-08-23
 * @Version: V1.0
 */
@Data
@TableName("requirements_reviews_shields")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "requirements_reviews_shields对象", description = "需求留言屏蔽")
public class RequirementsReviewsShields {
	
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private java.lang.String id;
	
	@ApiModelProperty(value = "需求ID")
	private java.lang.String requirementId;
	
	@Excel(name = "被屏蔽的用户ID", width = 15)
	@ApiModelProperty(value = "被屏蔽的用户ID")
	private java.lang.String shieldUid;
	
	@Excel(name = "屏蔽时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "屏蔽时间")
	private java.util.Date createTime;
}
