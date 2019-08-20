package com.campgem.modules.common.entity.enums;

public enum AdvertisementLocationEnum {
	
	SOCIAL_HOMEPAGE("Social Homepage"),
	SERVICE_LIST("Service List"),
	TRADING_CENTER_HOMEPAGE("TradingCenterHomepage");
	private String code;
	
	private AdvertisementLocationEnum(String code) {
		this.code = code;
	}
	
	public String code() {
		return code;
	}
}
