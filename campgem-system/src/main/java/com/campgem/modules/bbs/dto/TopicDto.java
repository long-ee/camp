package com.campgem.modules.bbs.dto;

import com.campgem.common.api.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value="TopicDto", description="话题创建/编辑请求数据模型")
public class TopicDto extends BaseDto {
    /**id*/
    @ApiModelProperty(value = "id")
    private String id;
    /**所属版块*/
    @ApiModelProperty(value = "所属版块")
    @NotBlank(message = "所属版块不能为空")
    private String postId;
    /**话题类型*/
    @ApiModelProperty(value = "话题类型")
    @NotBlank(message = "话题类型不能为空")
    private String categoryId;
    /**话题内容*/
    @ApiModelProperty(value = "话题内容")
    @NotBlank(message = "话题内容不能为空")
    private Object topicContent;
    /**话题名称*/
    @ApiModelProperty(value = "话题标题")
    @NotBlank(message = "话题标题不能为空")
    private String topicTitle;
    /**标签*/
    @ApiModelProperty(value = "标签")
    private String tag;
    /**附件文件地址*/
    @ApiModelProperty(value = "附件文件地址")
    private String attachment;
    /**是否置顶*/
    @ApiModelProperty(value = "是否置顶")
    @NotBlank(message = "是否置顶不能为空")
    private String top;
    /**发布者ID*/
    @ApiModelProperty(value = "发布者ID", hidden = true)
    private String publisherId;
}
