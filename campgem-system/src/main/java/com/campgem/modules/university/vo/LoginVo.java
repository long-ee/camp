package com.campgem.modules.university.vo;

import com.campgem.common.api.vo.LoginUserVo;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginVo implements Serializable {
    /** 用户是否完成注册 **/
    private boolean isRegistry;
    /** 用户认证token **/
    private String token;
    /** 当前用户信息 **/
    private LoginUserVo userInfo;

    public LoginVo() {
    }

    public LoginVo(boolean isRegistry, String token, LoginUserVo userInfo) {
        this.isRegistry = isRegistry;
        this.token = token;
        this.userInfo = userInfo;
    }
}
