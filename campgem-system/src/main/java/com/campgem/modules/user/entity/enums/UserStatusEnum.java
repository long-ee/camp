package com.campgem.modules.user.entity.enums;

public enum UserStatusEnum {
    AWAIT("AWAIT"),
    ACTIVE("ACTIVE"),
    FROZEN("FROZEN");
    private String code;

    private UserStatusEnum(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
