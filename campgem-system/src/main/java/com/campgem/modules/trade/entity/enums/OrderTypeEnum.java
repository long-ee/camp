package com.campgem.modules.trade.entity.enums;

public enum OrderTypeEnum {
	SERVICE(1),
	PRODUCT(2);
	
	private Integer code;
	
	private OrderTypeEnum(Integer code) {
		this.code = code;
	}
	
	public Integer code() {
		return code;
	}
}
