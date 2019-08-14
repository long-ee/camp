package com.campgem.modules.university.entity.enums;

public enum CategoryTypeEnum {
    PRODUCT("PRODUCT"),
    CLUB("CLUB"),
    NEED("NEED"),
    ACTIVITY("ACTIVITY"),
    TOPIC("TOPIC");
    private String code;

    private CategoryTypeEnum(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
