package com.campgem.modules.trade.entity.enums;

public enum OrderStatusEnum {
	UNPAID(0),
	PAID(1),
	SHIPPING(2),
	OFFLINE_TRADING(3);
	
	private Integer code;
	
	private OrderStatusEnum(Integer code) {
		this.code = code;
	}
	
	public Integer code() {
		return code;
	}
}
