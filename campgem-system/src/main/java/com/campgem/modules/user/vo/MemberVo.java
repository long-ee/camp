package com.campgem.modules.user.vo;

import com.campgem.modules.user.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MemberVo extends Member {
    @ApiModelProperty(value = "用户头像")
    private String face;
    
    @ApiModelProperty("联系电话")
    private String mobile;
}
