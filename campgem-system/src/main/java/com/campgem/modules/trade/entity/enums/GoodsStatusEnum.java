package com.campgem.modules.trade.entity.enums;

public enum GoodsStatusEnum {
	IN_SALE(0),
	OFF_SHELF(-1),
	SOLD(-2),
	EXPIRED(-3);
	
	private Integer code;
	
	private GoodsStatusEnum(Integer code) {
		this.code = code;
	}
	
	public Integer code() {
		return code;
	}
}
