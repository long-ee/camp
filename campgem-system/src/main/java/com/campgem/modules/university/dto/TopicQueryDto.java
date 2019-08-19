package com.campgem.modules.university.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="TopicQueryDto", description="话题查询请求数据模型")
public class TopicQueryDto implements Serializable {
    /**话题所属版块Id*/
    @ApiModelProperty(value = "话题所属版块Id")
    private String postId;
    /**话题标题*/
    @ApiModelProperty(value = "话题标题")
    private String topicTitle;
    /**话题类型*/
    @ApiModelProperty(value = "话题类型")
    private String categoryId;
    /**话题类型*/
    @ApiModelProperty(value = "固定为“Default”-0 、“Sort by popularity”-1 、“Sort by review”-2")
    private String orderBy;
}
