package com.campgem.modules.common.entity.enums;

public enum  MsgTypeEnum {
    USER("USER"),
    ALL("ALL"),
    USER_TYPE("USER_TYPE");
    private String code;

    private MsgTypeEnum(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
