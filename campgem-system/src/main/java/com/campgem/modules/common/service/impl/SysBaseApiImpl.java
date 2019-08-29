package com.campgem.modules.common.service.impl;

import com.campgem.common.system.api.ISysBaseAPI;
import com.campgem.common.system.vo.LoginUser;
import com.campgem.common.util.oConvertUtils;
import com.campgem.modules.user.entity.SysUser;
import com.campgem.modules.user.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: 底层共通业务API，提供其他独立模块调用
 * @Author: campgem
 * @Date:2019-4-20 
 * @Version:V1.0
 */
@Slf4j
@Service
public class SysBaseApiImpl implements ISysBaseAPI {

	@Resource
	private SysUserMapper userMapper;

	@Override
	public LoginUser getUserByName(String username) {
		if(oConvertUtils.isEmpty(username)) {
			return null;
		}
		LoginUser loginUser = new LoginUser();
		SysUser sysUser = userMapper.getUserByName(username);
		if(sysUser==null) {
			return null;
		}
		BeanUtils.copyProperties(sysUser, loginUser);
		return loginUser;
	}

}
