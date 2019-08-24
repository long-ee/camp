package com.campgem.modules.common.entity.enums;

public enum CategoryTypeEnum {
    PRODUCT("PRODUCT"),
    SERVICE("SERVICE"),
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
