package com.campgem.modules.university.dto;

import com.campgem.common.api.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;

/**
 * @author: X.Tony
 */
@Data
@ApiModel(value="社团创建或者修改请求数据模型", description="社团创建或者修改请求数据模型")
public class ClubCreateOrUpdateDto extends BaseDto {
    /**id*/
    private String id;
    /**社团所属分类*/
    @ApiModelProperty(value = "社团所属分类")
    @NotBlank(message = "请选择社团所属分类")
    private String categoryId;
    /**社团图标*/
    @ApiModelProperty(value = "社团图标")
    @NotBlank(message = "请上传社团图标")
    private String clubIcon;
    /**社团名称*/
    @ApiModelProperty(value = "社团名称")
    @NotBlank(message = "请填写社团名称")
    private String clubName;
    /**社团简介*/
    @ApiModelProperty(value = "社团简介")
    @NotBlank(message = "请填写社团介绍")
    private String information;
    /**社团创建人*/
    @ApiModelProperty(value = "社团创建人", hidden = true)
    private String creatorId;
    /**所属大学*/
    @ApiModelProperty(value = "所属大学", hidden = true)
    private String universityId;


}
