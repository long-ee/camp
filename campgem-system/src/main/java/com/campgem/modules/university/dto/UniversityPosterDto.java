package com.campgem.modules.university.dto;

import com.campgem.common.api.dto.BaseDto;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value="UniversityPosterDto", description="学校海报发布请求数据模型")
public class UniversityPosterDto extends BaseDto {
    @ApiModelProperty(value = "海报ID")
    private String id;
    /**学校ID*/
    @ApiModelProperty(value = "学校ID")
    @NotBlank(message = "请选择海报所属学校")
    private String universityId;
    /**海报名称*/
    @ApiModelProperty(value = "海报名称")
    @NotBlank(message = "请填写海报名称")
    private String posterName;
    /**海报图片*/
    @ApiModelProperty(value = "海报图片")
    @NotBlank(message = "请上传海报图片")
    private String posterImage;
    /**海报内容*/
    @ApiModelProperty(value = "海报内容")
    @NotBlank(message = "请填写海报内容")
    private Object posterContent;
    /**结束日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束日期")
    private java.util.Date endDate;
    /**发布者*/
    @ApiModelProperty(value = "发布者", hidden = true)
    private String publisherId;
    /**发布时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "发布时间", hidden = true)
    private java.util.Date publishTime;

}
