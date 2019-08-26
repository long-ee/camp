package com.campgem.modules.user.controller.manage;

import com.alibaba.fastjson.JSONObject;
import com.campgem.common.api.vo.Result;
import com.campgem.common.constant.CommonConstant;
import com.campgem.common.system.util.JwtUtil;
import com.campgem.common.system.vo.LoginUser;
import com.campgem.common.util.PasswordUtil;
import com.campgem.common.util.RedisUtil;
import com.campgem.modules.common.dto.SysLoginModel;
import com.campgem.modules.shiro.vo.DefContants;
import com.campgem.modules.user.entity.SysUser;
import com.campgem.modules.user.entity.enums.IdentityTypeEnum;
import com.campgem.modules.user.service.ISysUserService;
import com.campgem.common.constant.IdentityConstant;
import com.campgem.modules.user.vo.ManageLoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public Result<JSONObject> login(SysLoginModel sysLoginModel) throws Exception {
		Result<JSONObject> result = new Result<>();
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

		//用户登录信息
		userInfo(sysUser, result);
		return result;
	}


	/**
	 * 用户信息
	 *
	 * @param sysUser
	 * @param result
	 * @return
	 */
	private Result<ManageLoginVo> userInfo(SysUser sysUser, Result<ManageLoginVo> result) {
		String password = sysUser.getPassword();
		String username = sysUser.getUsername();
		// 生成token
		String token = JwtUtil.sign(username, password, IdentityTypeEnum.USERNAME.code(), IdentityConstant.IDENTITY_SERVICE_MANAGE);
		redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
		// 设置超时时间
		redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME / 1000);

		// 获取用户部门信息
		ManageLoginVo loginVo = new ManageLoginVo();
		loginVo.setToken(token);
		result.setSuccess(true);
		result.setResult(loginVo);
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