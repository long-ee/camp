package com.campgem.modules.university.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.api.vo.IdentifyInfo;
import com.campgem.common.api.vo.LoginUserVo;
import com.campgem.common.constant.CacheConstant;
import com.campgem.common.constant.CommonConstant;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.system.query.QueryGenerator;
import com.campgem.common.system.util.JwtUtil;
import com.campgem.common.util.BeanConvertUtils;
import com.campgem.common.util.PasswordUtils;
import com.campgem.common.util.RandomUtils;
import com.campgem.common.util.RedisUtil;
import com.campgem.modules.message.handle.impl.EmailSendMsgHandle;
import com.campgem.modules.university.dto.LocalLoginDto;
import com.campgem.modules.university.dto.ThirdPartyLoginDto;
import com.campgem.modules.university.entity.UserAuth;
import com.campgem.modules.university.entity.UserBase;
import com.campgem.modules.university.entity.enums.IdentityTypeEnum;
import com.campgem.modules.university.entity.enums.UserStatusEnum;
import com.campgem.modules.university.mapper.UserAuthMapper;
import com.campgem.modules.university.service.IUserAuthService;
import com.campgem.modules.university.service.IUserBaseService;
import com.campgem.modules.university.vo.LoginVo;
import com.campgem.modules.university.vo.UserBaseVo;
import com.campgem.modules.user.dto.PasswordResetCodeVerifyDto;
import com.campgem.modules.user.dto.PasswordResetDto;
import com.xkcoding.justauth.AuthRequestFactory;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description: 用户认证信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
@Slf4j
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements IUserAuthService {

    @Resource
    private IUserBaseService userBaseService;
    @Resource
    private AuthRequestFactory authRequestFactory;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private EmailSendMsgHandle emailSendMsgHandle;

    @Override
    @Transactional
    public LoginVo thirdPartyLogin(ThirdPartyLoginDto thirdPartyLoginDto) {
        LoginVo loginVo = new LoginVo();
        // 1、参数校验
        thirdPartyLoginDto.paramValidation();

        // 2、根据code换取第三方平台身份信息
        AuthRequest authRequest = authRequestFactory.get(AuthSource.valueOf(thirdPartyLoginDto.getIdentityType()));
        AuthCallback authCallback = new AuthCallback();
        authCallback.setCode(thirdPartyLoginDto.getCode());
        authCallback.setState(thirdPartyLoginDto.getState());
        AuthResponse<AuthUser> response = authRequest.login(authCallback);

        if(!response.ok()){
            throw new JeecgBootException("第三方平台身份信息查询是失败,原因：" + response.getMsg());
        }
        AuthUser authUser = response.getData();
        // 3、jwt token 生成
        String identifier = this.getIdentifier(authUser);
        String certificate = PasswordUtils.encryptPassword(identifier, identifier, 2);
        String token = JwtUtil.sign(identifier, certificate, thirdPartyLoginDto.getIdentityType());
        UserAuth existUserAuth =  this.getUserAuthByIdentityType(identifier, thirdPartyLoginDto.getIdentityType());
        if(null == existUserAuth){
            UserBase userBase = new UserBase();
            userBase.setDelFlag(0);
            userBase.setUsername(authUser.getUsername());
            userBase.setNickName(authUser.getNickname());
            userBase.setUserStatus(UserStatusEnum.AWAIT.code());
            userBase.setCreateTime(new Date());
            userBaseService.save(userBase);
            UserAuth thirdLoginAuth = new UserAuth();
            thirdLoginAuth.setUserBaseId(userBase.getId());
            thirdLoginAuth.setIdentityType(thirdPartyLoginDto.getIdentityType());
            thirdLoginAuth.setIdentifier(identifier);
            thirdLoginAuth.setCertificate(certificate);
            this.save(thirdLoginAuth);
        }
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
        // 设置超时时间
        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME / 1000);
        IdentifyInfo identifyInfo = new IdentifyInfo();
        identifyInfo.setIdentifier(identifier);
        identifyInfo.setIdentityType(thirdPartyLoginDto.getIdentityType());
        UserBaseVo userBaseVo = userBaseService.getUserByIdentifyInfo(identifyInfo);
        if(StringUtils.equals(UserStatusEnum.AWAIT.code(), userBaseVo.getUserStatus())){
            loginVo.setRegistry(false);
            loginVo.setToken(token);
            return loginVo;
        }
        LoginUserVo loginUser = BeanConvertUtils.convertBean(userBaseVo, LoginUserVo.class);
        loginVo.setRegistry(true);
        loginVo.setUserInfo(loginUser);
        return loginVo;
    }


    private String getIdentifier(AuthUser authUser){
        AuthToken authToken = authUser.getToken();
        if(null != authToken && StringUtils.isNotBlank(authToken.getOpenId())){
            return authToken.getOpenId();
        }
        return authUser.getUuid();
    }

    @Override
    public LoginVo localLogin(LocalLoginDto loginDto) {
        IdentifyInfo identifyInfo = new IdentifyInfo();
        identifyInfo.setIdentifier(loginDto.getEmail());
        identifyInfo.setIdentityType(IdentityTypeEnum.EMAIL.code());
        UserBaseVo userBaseVo = userBaseService.getUserByIdentifyInfo(identifyInfo);
        if(null == userBaseVo){
            throw new JeecgBootException("邮箱用户未注册");
        }
        String encryptPassword = PasswordUtils.encryptPassword(loginDto.getEmail(), loginDto.getPassword(), 2);
        if(!StringUtils.equals(userBaseVo.getCertificate(), encryptPassword)){
            throw new JeecgBootException("用户账号或密码错误");
        }
        String token = JwtUtil.sign(loginDto.getEmail(), loginDto.getPassword(), IdentityTypeEnum.EMAIL.code());
        LoginUserVo loginUser = BeanConvertUtils.convertBean(userBaseVo, LoginUserVo.class);
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
        // 设置超时时间
        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME / 1000);
        LoginVo loginVo = new LoginVo(true, token, loginUser);
        return loginVo;
    }

    @Override
    public UserAuth getUserAuthByIdentityType(String identifier, String identityType) {
        if(StringUtils.isBlank(identifier) || StringUtils.isBlank(identityType)){
            return null;
        }
        UserAuth queryObj = new UserAuth();
        queryObj.setIdentifier(identifier);
        queryObj.setIdentityType(identityType);
        queryObj.setDelFlag(0);
        QueryWrapper<UserAuth> queryWrapper = QueryGenerator.initQueryWrapper(queryObj, null);
        return this.getOne(queryWrapper);
    }

    @Override
    public void getPasswordResetValidityCode(String email) {
        UserAuth userAuth = this.getUserAuthByIdentityType(email, IdentityTypeEnum.EMAIL.code());
        if(null == userAuth){
            throw new JeecgBootException(StatusEnum.NoSuchEmailExistError);
        }
        // 1、生成随机验证码
        String validityCode = RandomUtils.generateStr(4);
        // 2、邮件发送
        String esReceiver = email;
        String esTitle = "Campgem密码重置";
        String esContent = "您的账户正在申请重置密码，验证码为"+ validityCode+"，请在操作页面中输入此验证码后，重新设置新密码.";
        emailSendMsgHandle.SendMsg(esReceiver, esTitle, esContent);
        // 3、将验证码存入缓存(超时5分钟)
        redisUtil.set(CacheConstant.PASSWORD_RESET_EMAIL_VALIDITY_CACHE_PRFIX + email, validityCode, 600);

        // 4、添加随机码，防止接口恶意调用
        String randCode = RandomUtils.generateStr(8);
        redisUtil.set(CacheConstant.PASSWORD_RESET_RAND_CODE_CACHE_PRFIX + email, randCode, 300);
    }

    @Override
    public void passwordReset(PasswordResetDto passwordResetDto) {
        UserAuth userAuth = this.getUserAuthByIdentityType(passwordResetDto.getEmail(), IdentityTypeEnum.EMAIL.code());
        if(null == userAuth){
            throw new JeecgBootException(StatusEnum.NoSuchEmailExistError);
        }
        String randCode = (String) redisUtil.get(CacheConstant.PASSWORD_RESET_RAND_CODE_CACHE_PRFIX + passwordResetDto.getEmail());
        if(StringUtils.isBlank(randCode)){
            throw new JeecgBootException(StatusEnum.InvalidResetPasswordTokenError);
        }

        if(!StringUtils.equals(passwordResetDto.getNewPassword(), passwordResetDto.getRepeatPassword())){
            throw new JeecgBootException(StatusEnum.ResetPasswordBadError);
        }

        String newPassword = PasswordUtils.encryptPassword(passwordResetDto.getEmail(), passwordResetDto.getNewPassword(), 2);
        if(StringUtils.equals(userAuth.getCertificate(), newPassword)){
            throw new JeecgBootException(StatusEnum.ResetPasswordError);
        }

        userAuth.setCertificate(newPassword);
        this.updateById(userAuth);
    }

    @Override
    public void passwordResetCodeVerify(PasswordResetCodeVerifyDto verifyDto) {
        String validityCode = (String) redisUtil.get(CacheConstant.PASSWORD_RESET_EMAIL_VALIDITY_CACHE_PRFIX + verifyDto.getEmail());
        if(StringUtils.isBlank(validityCode) || !StringUtils.equals(verifyDto.getValidityCode(), validityCode)){
            throw new JeecgBootException(StatusEnum.InvalidResetPasswordTokenError);
        }
    }
}
