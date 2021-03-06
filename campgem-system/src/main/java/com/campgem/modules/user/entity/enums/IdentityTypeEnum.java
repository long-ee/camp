package com.campgem.modules.user.entity.enums;

public enum IdentityTypeEnum {
    USERNAME("USERNAME"),
    EMAIL("EMAIL"),
    GOOGLE("GOOGLE"),
    GITHUB("GITHUB");

    private String code;

    private IdentityTypeEnum(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

}
