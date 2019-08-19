package com.campgem.modules.university.service;

import com.campgem.modules.university.dto.ThirdPartyLoginDto;
import com.campgem.modules.university.dto.LocalLoginDto;
import com.campgem.modules.university.entity.UserAuth;
import com.campgem.modules.university.vo.LoginVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.user.dto.PasswordResetCodeVerifyDto;
import com.campgem.modules.user.dto.PasswordResetDto;

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


    /**
     * 获取用户名密码重置验证码
     * @param email
     */
    public void getPasswordResetValidityCode(String email);


    /**
     * 用户密码重置
     * @param passwordResetDto
     */
    public void passwordReset(PasswordResetDto passwordResetDto);

    /**
     * 用户密码重置验证码验证
     * @param verifyDto
     */
    public void passwordResetCodeVerify(PasswordResetCodeVerifyDto verifyDto);
}
