package com.campgem.modules.university.entity.enums;

public enum IdentityTypeEnum {
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
