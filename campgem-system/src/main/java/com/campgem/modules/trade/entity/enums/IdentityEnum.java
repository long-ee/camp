package com.campgem.modules.trade.entity.enums;

public enum IdentityEnum {
	BUSINESS(1),
	STUDENT_INDIVIDUAL(2);
	
	private Integer code;
	
	private IdentityEnum(Integer code) {
		this.code = code;
	}
	
	public Integer code() {
		return code;
	}
}
