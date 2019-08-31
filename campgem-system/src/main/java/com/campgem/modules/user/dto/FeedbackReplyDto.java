package com.campgem.modules.user.dto;

import com.campgem.common.api.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value="举报/反馈消息回复数据模型",description="举报/反馈消息回复数据模型")
public class FeedbackReplyDto extends BaseDto implements Serializable {

    @ApiModelProperty(value = "举报/反馈消息ID")
    @NotNull(message = "举报/反馈消息ID")
    private String id;

    @ApiModelProperty(value = "举报/反馈回复内容")
    @NotBlank(message = "回复内容不能为空")
    private String replyContent;
}
