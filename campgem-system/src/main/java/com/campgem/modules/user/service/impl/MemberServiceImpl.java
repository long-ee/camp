package com.campgem.modules.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.api.vo.IdentifyInfo;
import com.campgem.common.constant.CacheConstant;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.util.*;
import com.campgem.modules.message.dto.MsgDto;
import com.campgem.modules.message.entity.enums.MsgSendTypeEnum;
import com.campgem.modules.message.entity.enums.MsgTemplateEnum;
import com.campgem.modules.message.strategy.SendMsgStrategyFactory;
import com.campgem.modules.service.service.IBusinessActivityService;
import com.campgem.modules.service.vo.BusinessActivityInProgressVo;
import com.campgem.modules.service.vo.BusinessDetailVo;
import com.campgem.modules.user.dto.MemberDto;
import com.campgem.modules.user.dto.MemberQueryDto;
import com.campgem.modules.user.dto.UserPasswordModifyDto;
import com.campgem.modules.user.dto.UserRegistrationDto;
import com.campgem.modules.user.entity.Member;
import com.campgem.modules.user.entity.UserAuth;
import com.campgem.modules.user.entity.UserBase;
import com.campgem.modules.user.entity.enums.IdentityTypeEnum;
import com.campgem.modules.user.entity.enums.UserStatusEnum;
import com.campgem.modules.user.mapper.MemberMapper;
import com.campgem.modules.user.service.IMemberService;
import com.campgem.modules.user.service.IUserAuthService;
import com.campgem.modules.user.service.IUserBaseService;
import com.campgem.modules.user.vo.MemberVo;
import com.campgem.modules.user.vo.ShippingMethodsVo;
import com.campgem.modules.user.vo.UserBaseVo;
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
    private IUserBaseService userBaseService;
    @Resource
    private IUserAuthService userAuthService;
    @Resource
    private IBusinessActivityService businessActivityService;
    @Resource
    private MemberMapper memberMapper;

    @Override
    public IPage<MemberVo> queryPageList(Page page, MemberQueryDto queryDto) {
        return memberMapper.queryPageList(page, queryDto);
    }

    @Override
    public MemberVo queryDetails(String id) {
        return memberMapper.queryDetails(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(MemberDto memberDto) {
        memberDto.paramValidation();
        Member member = BeanConvertUtils.convertBean(memberDto, Member.class);
        this.updateById(member);
        UserBase userBase = userBaseService.getById(member.getUserBaseId());
        userBase.setFace(memberDto.getFace());
        userBase.setUsername(memberDto.getMemberName());
        userBaseService.updateById(userBase);
    }

    @Override
    public void getEmailValidityCode(String email) {
        if(StringUtils.isBlank(email)){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
        // 1、生成随机验证码
        String validityCode = RandomUtils.generateStr(4);
        // 2、邮件发送
        MsgDto msgDto = new MsgDto();
        msgDto.setMsgType(MsgTemplateEnum.REGISTER_EMAIL_CODE.msgType());
        msgDto.setReceiver(email);
        msgDto.setParams(new Object[]{validityCode});
        SendMsgStrategyFactory.getInstance(MsgSendTypeEnum.EMAIL).send(msgDto);
        // 3、将验证码存入缓存(超时10分钟)
        redisUtil.set(CacheConstant.REGISTER_EMAIL_VALIDITY_CACHE_PRFIX + email, validityCode, 600);
    }

    @Override
    public MemberVo getMemberByUserBaseId(String userBaseId) {
        return baseMapper.getMemberByUserBaseId(userBaseId);
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
    public void modifyPassword(UserPasswordModifyDto passwordModifyDto) {
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
    
    @Override
    public BusinessDetailVo queryBusinessDetail(String businessId) {
        BusinessDetailVo detail = baseMapper.queryBusinessDetail(businessId);
        if (detail == null) {
            throw new JeecgBootException("商家不存在");
        }
        
        // 查询商家活动
        List<BusinessActivityInProgressVo> list = businessActivityService.queryBusinessActivityInProgress(businessId);
        detail.setInProgressActivities(list);
        
        return detail;
    }

    @Override
    public List<MemberVo> queryMemberByIds(String memberIds) {
        if(StringUtils.isBlank(memberIds)){
            return null;
        }
        return memberMapper.queryMemberByIds(memberIds);
    }

    @Override
    public List<Member> queryMemberByTypes(String... memberTypes) {
        LambdaQueryWrapper<Member> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Member::getMemberType, memberTypes);
        return memberMapper.selectList(queryWrapper);
    }

    @Override
    public void updateUserMessageChat(String memberId, String allowChat) {
        Member member = this.getById(memberId);
        if(null == member){
            throw new JeecgBootException(StatusEnum.NotFound);
        }
        member.setAllowChat(allowChat);
        this.updateById(member);
    }
}
