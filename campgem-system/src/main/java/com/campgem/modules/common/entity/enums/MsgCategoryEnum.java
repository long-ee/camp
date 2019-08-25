package com.campgem.modules.common.entity.enums;

public enum MsgCategoryEnum {
    SYSTEM("SYSTEM"),
 ;
    private String code;

    private MsgCategoryEnum(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
