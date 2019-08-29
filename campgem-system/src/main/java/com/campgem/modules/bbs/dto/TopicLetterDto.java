package com.campgem.modules.bbs.dto;

import com.campgem.common.api.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value="TopicLetterDto", description="发送站内信请求数据模型")
public class TopicLetterDto extends BaseDto {
    @ApiModelProperty(value = "接受者会员ID")
    @NotBlank(message = "接受者会员ID不能为空")
    private String receiver;
    @ApiModelProperty(value = "信息内容")
    @NotBlank(message = "信息内容不能为空")
    private String msgContent;
}
