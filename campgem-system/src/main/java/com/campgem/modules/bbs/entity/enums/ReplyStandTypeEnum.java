package com.campgem.modules.bbs.entity.enums;

/**
 * @author: X.Tony
 */
public enum  ReplyStandTypeEnum {
    LIKE("1"),
    DISLIKE("0");
    private String code;
    private ReplyStandTypeEnum(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
