package com.campgem.modules.university.dto;

import com.campgem.common.api.dto.BaseDto;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.modules.university.entity.enums.ReplyStandTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;

/**
 * @author: X.Tony
 */
@Data
@ApiModel(value="TopicReplyStandDto", description="话题回复立场观点（点赞/点踩）请求数据模型")
public class TopicReplyStandDto extends BaseDto {
    @ApiModelProperty(value = "话题ID")
    @NotBlank(message = "话题ID不能为空")
    private String replyId;
    @ApiModelProperty(value = "点赞 0 点踩 1")
    @NotBlank(message = "点赞、点踩值不能为空")
    private String standValue;

    @Override
    public void paramValidation() {
        if(StringUtils.equals(ReplyStandTypeEnum.DISLIKE.code(), this.standValue) && StringUtils.equals(ReplyStandTypeEnum.LIKE.code(), this.standValue)){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
    }
}
