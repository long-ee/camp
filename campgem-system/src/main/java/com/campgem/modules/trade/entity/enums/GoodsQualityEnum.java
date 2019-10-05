package com.campgem.modules.trade.entity.enums;

public enum GoodsQualityEnum {
	ALL(0),
	BRAND(1),
	ALMOST(2),
	GENTLY(3);
	
	private Integer code;
	
	private GoodsQualityEnum(Integer code) {
		this.code = code;
	}
	
	public Integer code() {
		return code;
	}
}
