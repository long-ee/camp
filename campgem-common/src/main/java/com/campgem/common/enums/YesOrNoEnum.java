package com.campgem.common.enums;

public enum  YesOrNoEnum {

    NO("0", "否"),
    YES("1", "是");
    private String code;

    private String msg;

    YesOrNoEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String code() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
