package com.campgem.modules.bbs.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Description: 话题信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Data
@ApiModel(value="TopicVo", description="话题显示数据模型")
public class TopicVo {
	/**id*/
    @ApiModelProperty(value = "id")
	private String id;
	/**所属版块ID*/
	@ApiModelProperty(value = "所属版块ID")
	private String postId;
	@ApiModelProperty(value = "所属版块")
	private String postName;
	/**话题内容*/
    @ApiModelProperty(value = "话题内容")
	private Object topicContent;
	/**话题标题*/
    @ApiModelProperty(value = "话题标题")
	private String topicTitle;
	/**标签*/
    @ApiModelProperty(value = "标签")
	private String tag;
	/**附件文件地址*/
    @ApiModelProperty(value = "附件文件地址")
	private String attachment;
	/**回复数量*/
    @ApiModelProperty(value = "回复数量")
	private Integer replyCount;
	/**浏览数量*/
    @ApiModelProperty(value = "浏览数量")
	private Integer viewCount;
	/**是否置顶*/
    @ApiModelProperty(value = "是否置顶")
	private String top;
	/**发布者*/
	@ApiModelProperty(value = "发布者ID")
	private String publisherId;
	/**发布者名称*/
	@ApiModelProperty(value = "发布者名称")
	private String publisherName;
	/**发布者头像*/
	@ApiModelProperty(value = "发布者头像")
	private String publisherFace;
	/**发布者城市*/
	@ApiModelProperty(value = "发布者城市")
	private String publisherCity;
	/**发布者用户类型*/
	@ApiModelProperty(value = "发布者用户类型")
	private String memberType;
	/**createTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
	/**最后回复人名称*/
	@ApiModelProperty(value = "最后回复人名称")
	private String lastReplier;
	/**最后回复时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "最后回复时间")
	private Date lastReplyTime;
}
