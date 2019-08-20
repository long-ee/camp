package com.campgem.modules.trade.entity.enums;

public enum GoodsStatusEnum {
	IN_SALE(1),
	OFF_SHELF(2),
	SOLD(3),
	EXPIRED(4);
	
	private Integer code;
	
	private GoodsStatusEnum(Integer code) {
		this.code = code;
	}
	
	public Integer code() {
		return code;
	}
}
