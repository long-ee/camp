package com.campgem.modules.university.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="社团查询请求数据模型", description="社团查询请求数据模型")
public class ClubQueryDto implements Serializable {
    /** 社团ID **/
    @ApiModelProperty(value = "社团ID")
    private String clubId;
    /** 社团类别ID **/
    @ApiModelProperty(value = "社团类别ID", hidden = true)
    private String categoryId;
    /** 社团所属学校ID **/
    @ApiModelProperty(value = "社团所属学校ID")
    private String universityId;
    /** 社团名称 **/
    @ApiModelProperty(value = "社团名称")
    private String clubName;
    /** 社团创建人 **/
    @ApiModelProperty(value = "社团创建人/或者社团成员", hidden = true)
    private String memberId;
}
