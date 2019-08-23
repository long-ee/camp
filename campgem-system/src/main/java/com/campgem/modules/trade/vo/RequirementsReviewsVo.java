package com.campgem.modules.trade.vo;

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

/**
 * @Description: 需求留言
 * @Author: campgem
 * @Date: 2019-08-18
 * @Version: V1.0
 */
@Data
@TableName("requirements_reviews")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "requirements_reviews对象", description = "需求留言")
public class RequirementsReviewsVo {
	
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty("用户ID")
	private String uid;
	
	@ApiModelProperty("发布者用户类型")
	private String memberType;
	
	@ApiModelProperty("用户头像")
	private String srcFace;
	
	@ApiModelProperty("用户昵称")
	private String nickName;
	
	@ApiModelProperty(value = "留言内容")
	private String content;
	
	@ApiModelProperty("发布者的回复内容")
	private String replyContent;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "留言时间")
	private java.util.Date createTime;
}
