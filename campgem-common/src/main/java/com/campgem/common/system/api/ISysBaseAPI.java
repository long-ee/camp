package com.campgem.common.system.api;

import com.campgem.common.system.vo.LoginUser;

/**
 * @Description: 底层共通业务API，提供其他独立模块调用
 * @Author: campgem
 * @Date:2019-4-20 
 * @Version:V1.0
 */
public interface ISysBaseAPI {

	/**
	  * 根据用户账号查询登录用户信息
	 * @param username
	 * @return
	 */
	public LoginUser getUserByName(String username);
	

}
