package com.campgem.modules.user.controller.manage;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campgem.modules.shiro.vo.DefContants;
import com.campgem.modules.user.entity.SysDepart;
import com.campgem.modules.user.entity.SysUser;
import com.campgem.modules.common.dto.SysLoginModel;
import com.campgem.modules.user.service.ISysDepartService;
import com.campgem.modules.common.service.ISysLogService;
import com.campgem.modules.user.service.ISysUserService;
import com.campgem.modules.user.entity.enums.IdentityTypeEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import com.campgem.common.api.vo.Result;
import com.campgem.common.constant.CommonConstant;
import com.campgem.common.system.api.ISysBaseAPI;
import com.campgem.common.system.util.JwtUtil;
import com.campgem.common.system.vo.LoginUser;
import com.campgem.common.util.DySmsHelper;
import com.campgem.common.util.PasswordUtil;
import com.campgem.common.util.RedisUtil;
import com.campgem.common.util.encryption.EncryptedString;
import com.campgem.common.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;

import cn.hutool.core.util.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: campgem
 * @since 2018-12-17
 */
@RestController
@RequestMapping("/api/manage/v1")
@Api(tags="用户登录")
@Slf4j
public class ManageLoginController {
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
    private RedisUtil redisUtil;

	@PostMapping(value = "/account/login")
	@ApiOperation(value="用户登录", notes="A1 登录")
	public Result<?> login(SysLoginModel sysLoginModel) throws Exception {
		Result<?> result = new Result<>();
		String username = sysLoginModel.getUsername();
		String password = sysLoginModel.getPassword();
		//1. 校验用户是否有效
		SysUser sysUser = sysUserService.getUserByName(username);
		result = sysUserService.checkUserIsEffective(sysUser);
		if(!result.isSuccess()) {
			return result;
		}
		//2. 校验用户名或密码是否正确
		String userpassword = PasswordUtil.encrypt(username, password, sysUser.getSalt());
		String syspassword = sysUser.getPassword();
		if (!syspassword.equals(userpassword)) {
			result.error500("用户名或密码错误");
			return result;
		}
		result.setSuccess(true);
		result.setMessage("登录成功");
		return result;
	}

	@PostMapping(value = "/account/logout")
	@ApiOperation(value="退出登录", notes="A1 退出登录")
	public Result<Object> logout(HttpServletRequest request,HttpServletResponse response) {
		//用户退出逻辑
		Subject subject = SecurityUtils.getSubject();
		LoginUser sysUser = (LoginUser)subject.getPrincipal();
		log.info(" 用户名:  "+sysUser.getRealname()+",退出成功！ ");
	    subject.logout();
	    String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
	    //清空用户Token缓存
	    redisUtil.del(CommonConstant.PREFIX_USER_TOKEN + token);
	    //清空用户权限缓存：权限Perms和角色集合
	    redisUtil.del(CommonConstant.LOGIN_USER_CACHERULES_ROLE + sysUser.getUsername());
	    redisUtil.del(CommonConstant.LOGIN_USER_CACHERULES_PERMISSION + sysUser.getUsername());
		return Result.ok("退出登录成功！");
	}
}