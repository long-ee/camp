package com.campgem.modules.service.vo.manage;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.campgem.modules.service.entity.BusinessActivityImages;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Description: 商家活动添加/编辑模型
 * @Author: campgem
 * @Date: 2019-08-21
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "商家活动添加/编辑模型", description = "商家活动添加/编辑模型")
public class MBusinessActivityVo {
	
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty("活动标题")
	@NotBlank(message = "活动标题不能为空")
	private String title;
	
	@Excel(name = "商家ID", width = 15)
	@ApiModelProperty(value = "商家ID")
	@NotBlank(message = "商家不能为空")
	private String uid;
	
	@ApiModelProperty(value = "分类ID")
	@NotBlank(message = "分类不能为空")
	private String categoryId;
	
	@ApiModelProperty(value = "日期范围，2019-08-01~2019-09-01")
	@NotBlank(message = "日期范围不能为空")
	private String dateRange;
	
	@ApiModelProperty(value = "时间范围，12:00~20:00")
	@NotBlank(message = "时间标题不能为空")
	private String timeRange;
	
	@ApiModelProperty(value = "1一次性，2每七天")
	@NotNull(message = "活动duration不能为空")
	private Integer duration;
	
	@ApiModelProperty(value = "网址")
	private String website;
	
	@ApiModelProperty(value = "门票价格")
	private java.math.BigDecimal price;
	
	@ApiModelProperty(value = "聚会地点")
	private String venue;
	
	@ApiModelProperty(value = "活动内容")
	@NotBlank(message = "活动内容不能为空")
	private String content;
	
	@ApiModelProperty("活动图片")
	@NotEmpty(message = "活动图片不能为空")
	private BusinessActivityImages[] images;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
}
