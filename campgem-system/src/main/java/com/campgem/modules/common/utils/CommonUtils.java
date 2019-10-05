package com.campgem.modules.common.utils;

import com.campgem.common.constant.CommonConstant;
import com.campgem.modules.user.entity.enums.MemberTypeEnum;

public class CommonUtils {
	/**
	 * 是否是商家
	 * @param type
	 * @return
	 */
	public static boolean isBusiness(String type) {
		return type.equals(MemberTypeEnum.LOCAL_BUSINESS.code()) || type.equals(MemberTypeEnum.ONLINE_BUSINESS.code());
	}
	
	public static boolean isPaypal(Integer paymentMethod) {
		return CommonConstant.payments.get(paymentMethod).equals("PayPal");
	}
}
