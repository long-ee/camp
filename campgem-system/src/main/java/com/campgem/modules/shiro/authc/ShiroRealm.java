package com.campgem.modules.shiro.authc;

import com.campgem.common.api.vo.IdentifyInfo;
import com.campgem.common.api.vo.LoginUserVo;
import com.campgem.common.constant.CommonConstant;
import com.campgem.common.system.util.JwtUtil;
import com.campgem.common.util.BeanConvertUtils;
import com.campgem.common.util.RedisUtil;
import com.campgem.common.util.oConvertUtils;
import com.campgem.modules.university.entity.enums.UserStatusEnum;
import com.campgem.modules.university.service.IUserBaseService;
import com.campgem.modules.university.vo.UserBaseVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @Description: 用户登录鉴权和获取用户授权
 * @Author: campgem
 * @Date: 2019-4-23 8:13
 * @Version: 1.1
 */
@Component
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	@Lazy
	private IUserBaseService userBaseService;
	@Autowired
	@Lazy
	private RedisUtil redisUtil;

	/**
	 * 必须重写此方法，不然Shiro会报错
	 */
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JwtToken;
	}

	/**
	 * 功能： 获取用户权限信息，包括角色以及权限。只有当触发检测用户权限时才会调用此方法，例如checkRole,checkPermission
	 * @return AuthorizationInfo 权限信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		log.info("————权限认证 [ roles、permissions]————");
		LoginUserVo loginUser = null;
		String uid = null;
		if (principals != null) {
			loginUser = (LoginUserVo) principals.getPrimaryPrincipal();
			uid = loginUser.getUid();
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// TODO 角色权限
		/*// 设置用户拥有的角色集合，比如“admin,test”
		Set<String> roleSet = sysUserService.getUserRolesSet("");
		info.setRoles(roleSet);

		// 设置用户拥有的权限集合，比如“sys:role:add,sys:user:add”
		Set<String> permissionSet = sysUserService.getUserPermissionsSet("");
		info.addStringPermissions(permissionSet);*/
		return info;
	}

	/**
	 * 功能： 用来进行身份认证，也就是说验证用户输入的账号和密码是否正确，获取身份验证信息，错误抛出异常
	 *
	 * @return 返回封装了用户信息的 AuthenticationInfo 实例
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
		log.debug("————身份认证————");
		String token = (String) auth.getCredentials();
		if (token == null) {
			throw new AuthenticationException("token为空!");
		}
		// 校验token有效性
		LoginUserVo loginUser = this.checkUserTokenIsEffect(token);
		return new SimpleAuthenticationInfo(loginUser, token, getName());
	}

	/**
	 * 校验token的有效性
	 *
	 * @param token
	 */
	public LoginUserVo checkUserTokenIsEffect(String token) throws AuthenticationException {
		// 解密获得username，用于和数据库进行对比
		IdentifyInfo identifyInfo = JwtUtil.getIdentifierInfo(token);
		if (null == identifyInfo || StringUtils.isBlank(identifyInfo.getIdentityType())
				|| StringUtils.isBlank(identifyInfo.getIdentifier())) {
			throw new AuthenticationException("token非法无效!");
		}

		// 查询用户信息
		LoginUserVo loginUser = null;
		UserBaseVo userBaseVo = userBaseService.getUserByIdentifyInfo(identifyInfo);
		if (userBaseVo == null) {
			throw new AuthenticationException("用户不存在!");
		}

		// 校验token是否超时失效 & 或者账号密码是否错误
		if (!jwtTokenRefresh(token, userBaseVo.getIdentifier(), userBaseVo.getCertificate(), userBaseVo.getIdentityType())) {
			throw new AuthenticationException("Token失效，请重新登录!");
		}

		// 判断用户状态1
		if (StringUtils.equals(UserStatusEnum.AWAIT.code(), userBaseVo.getUserStatus())) {
			throw new AuthenticationException("账号信息待完善, 请注册完善!");
		}

		// 判断用户状态2
		if (StringUtils.equals(UserStatusEnum.FROZEN.code(), userBaseVo.getUserStatus())) {
			throw new AuthenticationException("账号已被冻结, 请联系管理员!");
		}
		loginUser = BeanConvertUtils.convertBean(userBaseVo, LoginUserVo.class);
		return loginUser;
	}

	/**
	 * JWTToken刷新生命周期 （解决用户一直在线操作，提供Token失效问题）
	 * 1、登录成功后将用户的JWT生成的Token作为k、v存储到cache缓存里面(这时候k、v值一样)
	 * 2、当该用户再次请求时，通过JWTFilter层层校验之后会进入到doGetAuthenticationInfo进行身份验证
	 * 3、当该用户这次请求JWTToken值还在生命周期内，则会通过重新PUT的方式k、v都为Token值，缓存中的token值生命周期时间重新计算(这时候k、v值一样)
	 * 4、当该用户这次请求jwt生成的token值已经超时，但该token对应cache中的k还是存在，则表示该用户一直在操作只是JWT的token失效了，程序会给token对应的k映射的v值重新生成JWTToken并覆盖v值，该缓存生命周期重新计算
	 * 5、当该用户这次请求jwt在生成的token值已经超时，并在cache中不存在对应的k，则表示该用户账户空闲超时，返回用户信息已失效，请重新登录。
	 * 6、每次当返回为true情况下，都会给Response的Header中设置Authorization，该Authorization映射的v为cache对应的v值。
	 * 7、注：当前端接收到Response的Header中的Authorization值会存储起来，作为以后请求token使用
	 * 参考方案：https://blog.csdn.net/qq394829044/article/details/82763936
	 *
	 * @param identifier
	 * @param certificate
	 * @param identityType
	 * @return
	 */

	public boolean jwtTokenRefresh(String token, String identifier, String certificate, String identityType) {
		String cacheToken = String.valueOf(redisUtil.get(CommonConstant.PREFIX_USER_TOKEN + token));
		if (oConvertUtils.isNotEmpty(cacheToken)) {
			// 校验token有效性
			if (!JwtUtil.verify(token, identifier, certificate, identityType)) {
				String newAuthorization = JwtUtil.sign(identifier, certificate, identityType);
				redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, newAuthorization);
				// 设置超时时间
				redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME / 1000);
			} else {
				redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, cacheToken);
				// 设置超时时间
				redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME / 1000);
			}
			return true;
		}
		return false;
	}

}
