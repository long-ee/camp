package com.campgem.common.system.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.campgem.common.api.vo.IdentifyInfo;
import com.campgem.common.constant.DataBaseConstant;
import com.campgem.common.system.vo.LoginUser;
import com.campgem.common.system.vo.SysUserCacheInfo;
import com.campgem.common.util.SpringContextUtils;
import com.campgem.common.util.oConvertUtils;
import com.google.common.base.Joiner;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;

/**
 * @Author: campgem
 * @Date 2018-07-12 14:23
 * @Desc JWT工具类
 **/
public class JwtUtil {

	/** 过期时间30分钟 **/
	public static final long EXPIRE_TIME = 30 * 60 * 1000;

	/**
	 * 校验token
	 * @param token  token
	 * @param identifier 认证账号
	 * @param certificate 认证密码
	 * @param identityType 认证类型
	 * @return
	 */
	public static boolean verify(String token, String identifier, String certificate, String identityType) {
		try {
			// 根据密码生成JWT效验器
			Algorithm algorithm = Algorithm.HMAC256(certificate);
			JWTVerifier verifier = JWT.require(algorithm)
					.withClaim("identifier", identifier)
					.withClaim("identityType", identityType).build();
			// 效验TOKEN
			DecodedJWT jwt = verifier.verify(token);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	/**
	 * 获取认证信息
	 * @param token
	 * @return
	 */
	public static IdentifyInfo getIdentifierInfo(String token) {
		try {
			if(StringUtils.isBlank(token)){
				return null;
			}
			DecodedJWT jwt = JWT.decode(token);
			String identifier = jwt.getClaim("identifier").asString();
			String identityType = jwt.getClaim("identityType").asString();
			String serviceId = jwt.getClaim("serviceId").asString();
			IdentifyInfo identifyInfo = new IdentifyInfo(identifier, identityType, serviceId);
			return identifyInfo;
		} catch (JWTDecodeException e) {
			return null;
		}
	}


	/**
	 * 生成token
	 * @param identifier
	 * @param certificate
	 * @param identityType
	 * @return
	 */
	public static String sign(String identifier, String certificate, String identityType, String serviceId) {
		Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
		Algorithm algorithm = Algorithm.HMAC256(certificate);
		// 附带认证信息
		return JWT.create()
				.withClaim("identifier", identifier)
				.withClaim("identityType",identityType)
				.withClaim("serviceId",serviceId )
				.withExpiresAt(date).sign(algorithm);
	}

	/**
	 *
	 * 根据请求头获取认证信息
	 * @param request
	 * @return
	 */
	public static IdentifyInfo getIdentifierByToken(HttpServletRequest request) {
		String accessToken = request.getHeader("X-Access-Token");
		IdentifyInfo identifyInfo = getIdentifierInfo(accessToken);
		if (null == identifyInfo || StringUtils.isBlank(identifyInfo.getIdentifier()) || StringUtils.isBlank(identifyInfo.getIdentityType())) {
			return null;
		}
		return identifyInfo;
	}

	/**
	 *  从session中获取变量
	 * @param key
	 * @return
	 */
	public static String getSessionData(String key) {
		//${myVar}%
		//得到${} 后面的值
		String moshi = "";
		if(key.indexOf("}")!=-1){
			 moshi = key.substring(key.indexOf("}")+1);
		}
		String returnValue = null;
		if (key.contains("#{")) {
			key = key.substring(2,key.indexOf("}"));
		}
		if (oConvertUtils.isNotEmpty(key)) {
			HttpSession session = SpringContextUtils.getHttpServletRequest().getSession();
			returnValue = (String) session.getAttribute(key);
		}
		//结果加上${} 后面的值
		if(returnValue!=null){returnValue = returnValue + moshi;}
		return returnValue;
	}

	/**
	 * 从当前用户中获取变量
	 * @param key
	 * @param user
	 * @return
	 */
	//TODO 急待改造 sckjkdsjsfjdk
	public static String getUserSystemData(String key, SysUserCacheInfo user) {
		if(user==null) {
			user = JeecgDataAutorUtils.loadUserInfo();
		}
		//#{sys_user_code}%

		// 获取登录用户信息
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		String moshi = "";
		if(key.indexOf("}")!=-1){
			 moshi = key.substring(key.indexOf("}")+1);
		}
		String returnValue = null;
		//针对特殊标示处理#{sysOrgCode}，判断替换
		if (key.contains("#{")) {
			key = key.substring(2,key.indexOf("}"));
		} else {
			key = key;
		}
		//替换为系统登录用户帐号
		if (key.equals(DataBaseConstant.SYS_USER_CODE)|| key.equals(DataBaseConstant.SYS_USER_CODE_TABLE)) {
			if(user==null) {
				returnValue = sysUser.getUsername();
			}else {
				returnValue = user.getSysUserCode();
			}
		}
		//替换为系统登录用户真实名字
		if (key.equals(DataBaseConstant.SYS_USER_NAME)|| key.equals(DataBaseConstant.SYS_USER_NAME_TABLE)) {
			if(user==null) {
				returnValue = sysUser.getRealname();
			}else {
				returnValue = user.getSysUserName();
			}
		}

		//替换为系统用户登录所使用的机构编码
		if (key.equals(DataBaseConstant.SYS_ORG_CODE)|| key.equals(DataBaseConstant.SYS_ORG_CODE_TABLE)) {
			if(user==null) {
				returnValue = sysUser.getOrgCode();
			}else {
				returnValue = user.getSysOrgCode();
			}
		}
		//替换为系统用户所拥有的所有机构编码
		if (key.equals(DataBaseConstant.SYS_MULTI_ORG_CODE)|| key.equals(DataBaseConstant.SYS_MULTI_ORG_CODE)) {
			if(user.isOneDepart()) {
				returnValue = user.getSysMultiOrgCode().get(0);
			}else {
				returnValue = Joiner.on(",").join(user.getSysMultiOrgCode());
			}
		}
		//替换为当前系统时间(年月日)
		if (key.equals(DataBaseConstant.SYS_DATE)|| key.equals(DataBaseConstant.SYS_DATE_TABLE)) {
			returnValue = user.getSysDate();
		}
		//替换为当前系统时间（年月日时分秒）
		if (key.equals(DataBaseConstant.SYS_TIME)|| key.equals(DataBaseConstant.SYS_TIME_TABLE)) {
			returnValue = user.getSysTime();
		}
		//流程状态默认值（默认未发起）
		if (key.equals(DataBaseConstant.BPM_STATUS_TABLE)|| key.equals(DataBaseConstant.BPM_STATUS_TABLE)) {
			returnValue = "1";
		}
		if(returnValue!=null){returnValue = returnValue + moshi;}
		return returnValue;
	}
}
