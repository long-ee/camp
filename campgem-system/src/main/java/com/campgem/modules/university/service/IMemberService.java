package com.campgem.modules.university.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.modules.service.vo.BusinessDetailVo;
import com.campgem.modules.university.dto.MemberQueryDto;
import com.campgem.modules.university.dto.UserPasswordModifyDto;
import com.campgem.modules.university.dto.UserRegistrationDto;
import com.campgem.modules.university.entity.Member;
import com.campgem.modules.university.vo.MemberVo;
import com.campgem.modules.user.vo.ShippingMethodsVo;

import java.util.List;

/**
 * @Description: 用户扩展信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface IMemberService extends IService<Member> {
    /**
     * @param queryDto
     * @return
     */
    IPage<MemberVo> queryPageList(Page page, MemberQueryDto queryDto);

    /**
     * @param id
     * @return
     */
    MemberVo queryDetails(String id);

    /**
     * 获取邮箱验证码
     * @param email
     * @return
     */
    public void getEmailValidityCode(String email);

    /**
     * 根据用户基础信息ID查询会员信息
     * @param userBaseId
     * @return
     */
    public MemberVo getMemberByUserBaseId(String userBaseId);

    /**
     * 会员注册
     * @param userRegistrationDto
     * @return
     */
    public MemberVo registration(UserRegistrationDto userRegistrationDto) throws JeecgBootException;

    /**
     * 会员密码修改
     * @param passwordModifyDto
     * @return
     */
    public MemberVo modifyPassword(UserPasswordModifyDto passwordModifyDto);
    
    /**
     * 查询商家配送方式
     */
	List<ShippingMethodsVo> queryShoppingMethods();
    
    /**
     * 更新商家配送方式
     */
    boolean updateUserShoppingMethods(List<ShippingMethodsVo> toJSONString);
    
    /**
     * D14 商家主页
     */
	BusinessDetailVo queryBusinessDetail(String businessId);
}
