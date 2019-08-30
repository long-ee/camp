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
@ApiModel(value="举报/反馈数据模型",description="举报/反馈数据模型")
public class FeedbackDto extends BaseDto implements Serializable {

    @ApiModelProperty(value = "举报/反馈分类，Suggestion，Tip-off")
    @NotNull(message = "分类不能为空")
    private String category;
    
    @ApiModelProperty("举报对象")
    @NotNull(message = "举报对象不能为空")
    private String reportObject;
    
    @ApiModelProperty("被举报的用户ID")
    private String reportedUid;

    @ApiModelProperty(value = "举报/反馈内容")
    @NotBlank(message = "内容不能为空")
    private String content;
}
