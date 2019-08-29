package com.campgem.modules.message.entity.enums;

import com.campgem.common.util.oConvertUtils;

/**
 * 发送消息类型枚举
 */
public enum MsgSendTypeEnum {
	//推送方式：1邮件 2平台消息
	EMAIL("EMAIL", "emailSendMsgStrategy"),
	PLATFORM_MSG("PLATFORM_MSG", "platformServiceSendMsgStrategy");

	private String type;

	private String implClass;

	private MsgSendTypeEnum(String type, String implClass) {
		this.type = type;
		this.implClass = implClass;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImplClass() {
		return implClass;
	}

	public void setImplClass(String implClass) {
		this.implClass = implClass;
	}

	public static MsgSendTypeEnum getByType(String type) {
		if (oConvertUtils.isEmpty(type)) {
			return null;
		}
		for (MsgSendTypeEnum val : values()) {
			if (val.getType().equals(type)) {
				return val;
			}
		}
		return null;
	}
}
