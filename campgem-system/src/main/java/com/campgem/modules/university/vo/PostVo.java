package com.campgem.modules.university.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    /**话题数量*/
    @ApiModelProperty(value = "话题数量")
    private Integer topicCount;
    /**回复数量*/
    @ApiModelProperty(value = "回复数量")
    private Integer replyCount;
    /**版块管理员列表*/
    @ApiModelProperty(value = "版块管理员列表")
    private List<PostModeratorVo> moderators;
}
