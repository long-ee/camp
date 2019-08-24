package com.campgem.modules.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="用户查询请求数据模型", description="用户查询请求数据模型")
public class MemberQueryDto {
    /** 用户类型 **/
    @ApiModelProperty(value = "用户类型")
    private String memberType;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "手机号")
    private String mobile;
}
