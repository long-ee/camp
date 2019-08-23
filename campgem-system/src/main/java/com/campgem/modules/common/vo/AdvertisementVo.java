package com.campgem.modules.common.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 广告信息
 * @Author: ling
 * @Date: 2019-08-15
 * @Version: V1.0
 */
@Data
@TableName("advertisement")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "广告显示数据模型", description = "广告显示数据模型")
public class AdvertisementVo {
	/**
	 * id
	 */
	@TableId(type = IdType.UUID)
	@ApiModelProperty("id")
	private String id;
	
	@ApiModelProperty("广告title")
	private String title;
	
	@ApiModelProperty("类型，1图片，2视频")
	private Integer type;
	
	@ApiModelProperty("图片或视频URL地址")
	private String advertisementUrl;
	
	@ApiModelProperty("城市ID")
	private String playOrder;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "发布时间")
	private Date createTime;
}
