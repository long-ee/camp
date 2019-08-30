package com.campgem.modules.service.entity.enums;

public enum ServiceStatusEnum {
	DISABLE(-1),
	ENABLE(0);
	
	private Integer code;
	
	ServiceStatusEnum(Integer code) {
		this.code = code;
	}
	
	public Integer code() {
		return code;
	}
}
