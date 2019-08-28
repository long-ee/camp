package com.campgem.modules.trade.entity.enums;

public enum OrderStatusEnum {
	UNPAID(0),
	PAID(1),
	SHIPPING(2),
	EVALUATED(3),
	OFFLINE_TRADING(4),
	EXPIRED(5);
	
	private Integer code;
	
	OrderStatusEnum(Integer code) {
		this.code = code;
	}
	
	public Integer code() {
		return code;
	}
}
