package com.campgem.modules.bbs.vo;

import com.campgem.modules.bbs.entity.Topic;
import com.campgem.modules.user.vo.MemberVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@ApiModel(value="论坛版块显示数据模型", description="论坛版块显示数据模型")
public class PostVo {
    /**id*/
    @ApiModelProperty(value = "id")
    private String id;
    /**版块图片*/
    @ApiModelProperty(value = "版块图片")
    private String postIcon;
    /**版块名称*/
    @ApiModelProperty(value = "版块名称")
    private String postName;
    /**是否启用*/
    @ApiModelProperty(value = "是否启用")
    private String enabled;
    /**createTime*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
    private java.util.Date createTime;
    /**今日话题数量*/
    @ApiModelProperty(value = "今日话题数量")
    private Integer todayTopicCount;
    /**话题数量*/
    @ApiModelProperty(value = "话题数量")
    private Integer topicCount;
    /**回复数量*/
    @ApiModelProperty(value = "回复数量")
    private Integer replyCount;
    /**版块最新话题*/
    @ApiModelProperty(value = "版块最新话题")
    private Topic latestTopic;
    /**版块管理员列表*/
    @ApiModelProperty(value = "版块管理员列表")
    private List<MemberVo> moderators;
    /**主版块管理员*/
    @ApiModelProperty(value = "主版块管理员")
    private MemberVo primaryModerator;
    /**主管理员ID*/
    @ApiModelProperty(value = "主管理员ID", hidden = true)
    private String primaryAdminId;
    /**所有管理员ID*/
    @ApiModelProperty(value = "所有管理员ID", hidden = true)
    private String adminIds;

}
