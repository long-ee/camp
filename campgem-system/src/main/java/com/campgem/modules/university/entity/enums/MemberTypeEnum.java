package com.campgem.modules.university.entity.enums;

public enum MemberTypeEnum {
    STUDENT("STUDENT"),
    INDIVIDUAL("INDIVIDUAL"),
    LOCAL_BUSINESS("LOCAL_BUSINESS"),
    ONLINE_BUSINESS("ONLINE_BUSINESS");

    private String code;

    private MemberTypeEnum(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

}
