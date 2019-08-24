package com.campgem.modules.bbs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="论坛版块查询请求数据模型", description="论坛版块查询请求数据模型")
public class PostQueryDto implements Serializable {
    /** 版块名称 **/
    @ApiModelProperty(value = "版块名称")
    private String postName;
    /** 版块Id **/
    @ApiModelProperty(value = "版块Id")
    private String postId;
    /** 管理员ID **/
    @ApiModelProperty(value = "管理员ID", hidden = true)
    private String adminId;
}
