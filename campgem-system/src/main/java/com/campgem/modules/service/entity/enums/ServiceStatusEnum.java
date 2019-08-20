package com.campgem.modules.service.entity.enums;

public enum ServiceStatusEnum {
	DISABLE(0),
	ENABLE(1);
	
	private Integer code;
	
	private ServiceStatusEnum(Integer code) {
		this.code = code;
	}
	
	public Integer code() {
		return code;
	}
}
