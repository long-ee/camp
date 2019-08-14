package com.campgem.modules.university.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="TopicQueryDto", description="话题查询请求数据模型")
public class TopicQueryDto implements Serializable {
    /**话题标题*/
    @ApiModelProperty(value = "话题标题")
    private String topicTitle;
}
