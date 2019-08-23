package com.campgem.modules.common.utils;

import com.campgem.modules.university.entity.enums.MemberTypeEnum;

public class CommonUtils {
	/**
	 * 是否是商家
	 * @param type
	 * @return
	 */
	public static boolean isBusiness(String type) {
		return type.equals(MemberTypeEnum.LOCAL_BUSINESS.code()) || type.equals(MemberTypeEnum.ONLINE_BUSINESS.code());
	}
}
