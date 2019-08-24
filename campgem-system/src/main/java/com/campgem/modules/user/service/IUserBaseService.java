package com.campgem.modules.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.common.api.vo.IdentifyInfo;
import com.campgem.modules.user.entity.UserBase;
import com.campgem.modules.user.vo.UserBaseVo;

/**
 * @Description: 用户基础信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface IUserBaseService extends IService<UserBase> {
    /**
     * 根据邮箱获取用户基础信息
     * @param email
     * @return
     */
    public UserBase getUserByEmail(String email);

    /**
     * 根据认证信息获取用户信息
     * @param identifyInfo
     * @return
     */
    public UserBaseVo getUserByIdentifyInfo(IdentifyInfo identifyInfo);
}
