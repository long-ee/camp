package com.campgem.common.constant;

/**
 * @author: huangxutao
 * @date: 2019-06-14
 * @description: 缓存常量
 */
public interface CacheConstant {

	/**
	 * 字典信息缓存
	 */
    public static final String DICT_CACHE = "dictCache";

	/**
	 * 权限信息缓存
	 */
    public static final String PERMISSION_CACHE = "permission";

	/**
	 * 登录用户规则缓存
	 */
    public static final String LOGIN_USER_RULES_CACHE = "loginUser_cacheRules";


	/**
	 * 邮箱注册验证码cache key prefix
	 */
	public static String REGISTER_EMAIL_VALIDITY_CACHE_PRFIX = "email_validity_";


	/**
	 * 重置密码cache key prefix
	 */
	public static String PASSWORD_RESET_EMAIL_VALIDITY_CACHE_PRFIX = "password_reset_validity_";


	/**
	 * 重置密码随机码（防止恶意重置）
	 */
	public static String PASSWORD_RESET_RAND_CODE_CACHE_PRFIX = "password_reset_rand_code_";

}
