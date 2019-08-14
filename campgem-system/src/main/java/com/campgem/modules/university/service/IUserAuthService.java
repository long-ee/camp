package com.campgem.modules.university.service;

import com.campgem.modules.university.dto.ThirdPartyLoginDto;
import com.campgem.modules.university.dto.LocalLoginDto;
import com.campgem.modules.university.entity.UserAuth;
import com.campgem.modules.university.vo.LoginVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 用户认证信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface IUserAuthService extends IService<UserAuth> {
    /**
     * 第三方登录调用
     * @param thirdPartyLoginDto
     * @return
     */
    public LoginVo thirdPartyLogin(ThirdPartyLoginDto thirdPartyLoginDto);

    /**
     * 系统用户登录调用
     * @param loginDto
     * @return
     */
    public LoginVo localLogin(LocalLoginDto loginDto);

    /**
     * 查询用户认证信息
     * @param identifier
     * @param identityType
     * @return
     */
    public UserAuth getUserAuthByIdentityType(String identifier, String identityType);
}
