package com.campgem.modules.bbs.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.util.Date;

/**
 * @Description: 话题信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Data
@TableName("topic")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="topic对象", description="话题信息")
public class Topic {
    
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private String id;
	/**所属版块*/
	@Excel(name = "所属版块", width = 15)
	@ApiModelProperty(value = "所属版块")
	private String postId;
	/**话题类型*/
	@Excel(name = "话题类型", width = 15)
	@ApiModelProperty(value = "话题类型")
	private String categoryId;
	/**话题内容*/
	@Excel(name = "话题内容", width = 15)
    @ApiModelProperty(value = "话题内容")
	private Object topicContent;
	/**话题名称*/
	@Excel(name = "话题标题", width = 15)
    @ApiModelProperty(value = "话题标题")
	private String topicTitle;
	/**标签*/
	@Excel(name = "标签", width = 15)
    @ApiModelProperty(value = "标签")
	private String tag;
	/**附件文件地址*/
	@Excel(name = "附件文件地址", width = 15)
    @ApiModelProperty(value = "附件文件地址")
	private String attachment;
	/**回复数量*/
	@Excel(name = "回复数量", width = 15)
    @ApiModelProperty(value = "回复数量")
	private Integer replyCount;
	/**浏览数量*/
	@Excel(name = "浏览数量", width = 15)
    @ApiModelProperty(value = "浏览数量")
	private Integer viewCount;
	/**是否置顶*/
	@Excel(name = "是否置顶", width = 15)
    @ApiModelProperty(value = "是否置顶")
	private String top;
	/**发布者ID*/
	@Excel(name = "发布者ID", width = 15)
	@ApiModelProperty(value = "发布者ID")
	private String publisherId;
	/**删除状态*/
	@Excel(name = "删除状态", width = 15)
    @ApiModelProperty(value = "删除状态")
	@TableLogic
	private Integer delFlag;
	/**createBy*/
	@Excel(name = "createBy", width = 15)
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**createTime*/
	@Excel(name = "createTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
	/**updateTime*/
	@Excel(name = "updateTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "updateTime")
	private java.util.Date updateTime;
	/**updateBy*/
	@Excel(name = "updateBy", width = 15)
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
	/**最后回复人名称*/
	@ApiModelProperty(value = "最后回复人名称")
	private String lastReplier;
	/**最后回复时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "最后回复时间")
	private Date lastReplyTime;
}
