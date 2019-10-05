package com.campgem.modules.user.dto;

import com.campgem.common.api.dto.BaseDto;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value="用户消息回复数据模型",description="用户消息回复数据模型")
public class UserMessageReplyDto extends BaseDto {
    @ApiModelProperty(value = "被回复的消息ID")
    @NotBlank(message = "被回复的消息ID不能为空")
    private String msgId;
    @ApiModelProperty(value = "回复内容")
    @NotBlank(message = "回复内容不能为空")
    private String replyContent;
    @ApiModelProperty(value = "回复者会员ID", hidden = true)
    private String memberId;

    @Override
    public void paramValidation() {
        if(StringUtils.isBlank(this.memberId)){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
    }
}
