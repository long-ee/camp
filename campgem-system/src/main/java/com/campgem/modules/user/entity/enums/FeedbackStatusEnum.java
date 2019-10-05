package com.campgem.modules.user.entity.enums;

public enum FeedbackStatusEnum {
    UNREPLY(0),
    REPLIED(-1);

    private Integer code;

    FeedbackStatusEnum(Integer code) {
        this.code = code;
    }

    public Integer code() {
        return code;
    }

}
