package com.campgem.modules.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "管理端登录返回实体")
public class ManageLoginVo implements Serializable {
    /** 管理员用户认证token **/
    @ApiModelProperty(value = "管理端认证Token")
    private String token;

}
