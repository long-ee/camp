package com.campgem.common.util;

import com.campgem.common.api.vo.LoginUserVo;

public class SecurityUtils {

    /**
     * 获取当前用户uid
     * @return
     */
    public static String getCurrentUserUid(){
        LoginUserVo currentUser = (LoginUserVo) org.apache.shiro.SecurityUtils.getSubject().getPrincipal();
        return currentUser.getUid();
    }

    /**
     * 获取当前会员uid
     * @return
     */
    public static String getCurrentUserMemberId(){
        LoginUserVo currentUser = (LoginUserVo) org.apache.shiro.SecurityUtils.getSubject().getPrincipal();
        return currentUser.getMemberId();
    }

    /**
     * 获取当前用户类型
     * @return
     */
    public static String getCurrentUserMemberType(){
        LoginUserVo currentUser = (LoginUserVo) org.apache.shiro.SecurityUtils.getSubject().getPrincipal();
        return currentUser.getMemberType();
    }

    /**
     * 获取当前用户
     * @return
     */
    public static LoginUserVo getCurrentUser(){
        LoginUserVo currentUser = (LoginUserVo) org.apache.shiro.SecurityUtils.getSubject().getPrincipal();
        return currentUser;
    }
}
