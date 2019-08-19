package com.campgem.modules.university.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.api.vo.IdentifyInfo;
import com.campgem.common.constant.CacheConstant;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.util.*;
import com.campgem.modules.message.handle.impl.EmailSendMsgHandle;
import com.campgem.modules.university.dto.MemberQueryDto;
import com.campgem.modules.university.dto.UserPasswordModifyDto;
import com.campgem.modules.university.dto.UserRegistrationDto;
import com.campgem.modules.university.entity.Member;
import com.campgem.modules.university.entity.UserAuth;
import com.campgem.modules.university.entity.UserBase;
import com.campgem.modules.university.entity.enums.IdentityTypeEnum;
import com.campgem.modules.university.entity.enums.UserStatusEnum;
import com.campgem.modules.university.mapper.MemberMapper;
import com.campgem.modules.university.service.IMemberService;
import com.campgem.modules.university.service.IUserAuthService;
import com.campgem.modules.university.service.IUserBaseService;
import com.campgem.modules.university.vo.MemberVo;
import com.campgem.modules.university.vo.UserBaseVo;
import com.campgem.modules.user.vo.ShippingMethodsVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description: 用户扩展信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    @Resource
    private RedisUtil redisUtil;
    @Resource
    private EmailSendMsgHandle emailSendMsgHandle;
    @Resource
    private IUserBaseService userBaseService;
    @Resource
    private IUserAuthService userAuthService;

    @Override
    public IPage<MemberVo> queryPageList(Page page, MemberQueryDto queryDto) {
        return null;
    }

    @Override
    public MemberVo queryDetails(String id) {
        return null;
    }

    @Override
    public void getEmailValidityCode(String email) {
        if(StringUtils.isBlank(email)){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
        // 1、生成随机验证码
        String validityCode = RandomUtils.generateStr(4);
        // TODO 后优化为异步任务发送方式
        // 2、邮件发送
        String esReceiver = email;
        String esTitle = "Campgem新用户注册";
        String esContent = "您的注册验证码为"+ validityCode+"，请在操作页面中输入此验证码后完成注册";
        emailSendMsgHandle.SendMsg(esReceiver, esTitle, esContent);
        // 3、将验证码存入缓存(超时10分钟)
        redisUtil.set(CacheConstant.REGISTER_EMAIL_VALIDITY_CACHE_PRFIX + email, validityCode, 600);
    }

    @Override
    public MemberVo getMemberByUserBaseId(String userBaseId) {
        //TODO
        return null;
    }

    @Override
    @Transactional
    public MemberVo registration(UserRegistrationDto userRegistrationDto) throws JeecgBootException{
        // 1.1、参数校验
        userRegistrationDto.paramValidation();

        // 1.2、校验邮箱是否已经注册
        String email = userRegistrationDto.getEmail();
        UserBase existUserBase = userBaseService.getUserByEmail(email);
        if(null != existUserBase){
            throw new JeecgBootException("邮箱已经注册");
        }
        // 2、邮箱验证码校验
        String validityCode = (String) redisUtil.get(CacheConstant.REGISTER_EMAIL_VALIDITY_CACHE_PRFIX + email);
        if(StringUtils.isBlank(validityCode) || !StringUtils.equals(userRegistrationDto.getEmailValidityCode(), validityCode)){
            throw new JeecgBootException("验证码错误或已过期");
        }

        // 3、验证用户名密码重复校验
        if(!StringUtils.equals(userRegistrationDto.getPassword(), userRegistrationDto.getConfirmPassword())){
            throw new JeecgBootException("用户密码不一致");
        }

        // 4、 初始化UserBase、UserAuth
        // 只有当第三方登录的时候才会先有UserAuth信息
        IdentifyInfo identifyInfo = userRegistrationDto.getIdentifyInfo();
        String userBaseId = null;
        if(null == identifyInfo){
            UserBase userBase = new UserBase();
            userBase.setEmail(userRegistrationDto.getEmail());
            userBase.setEmailBindTime(new Date());
            userBase.setDelFlag(0);
            userBase.setMobile(userRegistrationDto.getMobile());
            userBase.setMobileBindTime(new Date());
            userBase.setUsername(userRegistrationDto.getUsername());
            userBase.setNickName(userRegistrationDto.getUsername());
            userBase.setUserStatus(UserStatusEnum.ACTIVE.code());
            userBase.setCreateTime(new Date());
            userBaseService.save(userBase);

            userBaseId = userBase.getId();
            UserAuth userAuth = new UserAuth();
            userAuth.setUserBaseId(userBaseId);
            userAuth.setIdentifier(email);
            // 密码加密 MD5 2
            String password = PasswordUtils.encryptPassword(email, userRegistrationDto.getPassword(), 2);
            userAuth.setCertificate(password);
            userAuth.setIdentityType(IdentityTypeEnum.EMAIL.code());
            userAuth.setDelFlag(0);
            userAuth.setCreateTime(new Date());
            userAuthService.save(userAuth);
        }else {
            UserBaseVo userBaseVo = userBaseService.getUserByIdentifyInfo(identifyInfo);
            if(null == userBaseVo){
                throw new JeecgBootException("用户认证信息不存在");
            }
            // 更新用户状态
            UserBase userBase = userBaseService.getById(userBaseVo.getUid());
            if(null == userBase){
                throw new JeecgBootException("用户基础信息不存在");
            }
            userBase.setUserStatus(UserStatusEnum.ACTIVE.code());
            userBaseService.updateById(userBase);
        }

        // 5、 插入Member,注册成功
        Member member = BeanConvertUtils.copy(userRegistrationDto, Member.class);
        member.setDelFlag(0);
        member.setUserBaseId(userBaseId);
        member.setMemberName(userRegistrationDto.getUsername());
        this.save(member);

        // 6、返回Member信息
        MemberVo memberVo = BeanConvertUtils.copy(member, MemberVo.class);
        return memberVo;
    }

    @Override
    public MemberVo modifyPassword(UserPasswordModifyDto passwordModifyDto) {
        passwordModifyDto.paramValidation();
        if(!StringUtils.equals(passwordModifyDto.getNewPassword(), passwordModifyDto.getRepeatPassword())){
            throw new JeecgBootException("新密码两次输入不一致");
        }
        UserAuth userAuth = userAuthService.getUserAuthByIdentityType(passwordModifyDto.getEmail(), IdentityTypeEnum.EMAIL.code());
        if(null == userAuth){
            throw new JeecgBootException("用户认证信息不存在");
        }
        String oldEncryptPassword = userAuth.getCertificate();
        String originalEncryptPassword =  PasswordUtils.encryptPassword(passwordModifyDto.getEmail(), passwordModifyDto.getOriginalPassword(), 2);
        if(!StringUtils.equals(oldEncryptPassword, originalEncryptPassword)){
            throw new JeecgBootException("原密码不正确");
        }
        String newEncryptPassword = PasswordUtils.encryptPassword(passwordModifyDto.getEmail(), passwordModifyDto.getNewPassword(), 2);
        userAuth.setCertificate(newEncryptPassword);
        userAuthService.updateById(userAuth);
        return this.getMemberByUserBaseId(userAuth.getUserBaseId());
    }
    
    @Override
    public List<ShippingMethodsVo> queryShoppingMethods() {
        String methods = baseMapper.queryShoppingMethods(SecurityUtils.getCurrentUserUid());
        return JSON.parseObject(methods, new TypeReference<List<ShippingMethodsVo>>() {});
    }
    
    @Override
    public boolean updateUserShoppingMethods(List<ShippingMethodsVo> vos) {
        return baseMapper.updateUserShoppingMethods(vos, SecurityUtils.getCurrentUserUid());
    }
}
