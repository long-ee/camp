package com.campgem.modules.university.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.api.vo.IdentifyInfo;
import com.campgem.common.system.query.QueryGenerator;
import com.campgem.modules.university.entity.UserBase;
import com.campgem.modules.university.mapper.UserBaseMapper;
import com.campgem.modules.university.service.IUserBaseService;
import com.campgem.modules.university.vo.UserBaseVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: 用户基础信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
public class UserBaseServiceImpl extends ServiceImpl<UserBaseMapper, UserBase> implements IUserBaseService {


    @Resource
    private UserBaseMapper userBaseMapper;

    @Override
    public UserBase getUserByEmail(String email) {
        if(StringUtils.isBlank(email)){
            return null;
        }
        UserBase queryObj = new UserBase();
        queryObj.setEmail(email);
        QueryWrapper<UserBase> queryWrapper = QueryGenerator.initQueryWrapper(queryObj, null);
        return this.getOne(queryWrapper);
    }

    @Override
    public UserBaseVo getUserByIdentifyInfo(IdentifyInfo identifyInfo) {
        if(StringUtils.isBlank(identifyInfo.getIdentifier())
            || StringUtils.isBlank(identifyInfo.getIdentityType())){
            return null;
        }
        return userBaseMapper.getUserByIdentifyInfo(identifyInfo);
    }
}
