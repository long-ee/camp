package com.campgem.modules.bbs.dto;

import com.campgem.common.api.dto.BaseDto;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;

/**
 * @author: X.Tony
 */
@Data
@ApiModel(value="TopicReplyDto", description="话题回复请求数据模型")
public class TopicReplyDto extends BaseDto {
    @ApiModelProperty(value = "话题ID")
    @NotBlank(message = "话题ID不能为空")
    private String topicId;
    @ApiModelProperty(value = "话题回复内容")
    @NotBlank(message = "话题回复内容不能为空")
    private String replyCount;
    @ApiModelProperty(value = "话题回复人ID", hidden = true)
    private String replierId;
    @ApiModelProperty(value = "话题回复人名称", hidden = true)
    private String replierName;

    @Override
    public void paramValidation() {
       if(StringUtils.isBlank(this.replierId) || StringUtils.isBlank(this.replierName)){
           throw new JeecgBootException(StatusEnum.Unauthorized);
       }
    }
}
