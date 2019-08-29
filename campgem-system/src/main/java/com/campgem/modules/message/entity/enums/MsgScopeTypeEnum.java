package com.campgem.modules.message.entity.enums;


/**
 * 消息类型
 * @author: X.Tony
 */
public enum MsgScopeTypeEnum {
    /**
     * 平台所有用户
     */
    ALL("ALL", "平台所有用户"),

    STUDENT("STUDENT", "平台学生"),

    INDIVIDUAL("INDIVIDUAL", "普通用户"),

    LOCAL_BUSINESS("LOCAL_BUSINESS", "本地商家"),

    ONLINE_BUSINESS("ONLINE_BUSINESS", "线上商家"),

    CUSTOM_ASSIGN_USER("CUSTOM_ASSIGN_USER", "自定义指定用户"),
    ;


    /**
     * 枚举值
     */
    private final String code;

    /**
     * 枚举描述
     */
    private final String message;

    /**
     * @param code    枚举值
     * @param message 枚举描述
     */
    private MsgScopeTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @return Returns the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return Returns the code.
     */
    public String code() {
        return code;
    }

    /**
     * @return Returns the message.
     */
    public String message() {
        return message;
    }

    /**
     * 通过枚举<code>code</code>获得枚举
     *
     * @param code
     * @return WeekEnum
     */
    public static MsgScopeTypeEnum getByCode(String code) {
        for (MsgScopeTypeEnum _enum : values()) {
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }
        return null;
    }

    /**
     * 获取全部枚举
     *
     * @return List<WeekEnum>
     */
    public static java.util.List<MsgScopeTypeEnum> getAllEnum() {
        java.util.List<MsgScopeTypeEnum> list = new java.util.ArrayList<MsgScopeTypeEnum>(values().length);
        for (MsgScopeTypeEnum _enum : values()) {
            list.add(_enum);
        }
        return list;
    }

    /**
     * 获取全部枚举值
     *
     * @return List<String>
     */
    public static java.util.List<String> getAllEnumCode() {
        java.util.List<String> list = new java.util.ArrayList<String>(values().length);
        for (MsgScopeTypeEnum _enum : values()) {
            list.add(_enum.code());
        }
        return list;
    }

    /**
     * 通过code获取msg
     *
     * @param code 枚举值
     * @return
     */
    public static String getMsgByCode(String code) {
        if (code == null) {
            return null;
        }
        MsgScopeTypeEnum _enum = getByCode(code);
        if (_enum == null) {
            return null;
        }
        return _enum.getMessage();
    }

    /**
     * 获取枚举code
     *
     * @param _enum
     * @return
     */
    public static String getCode(MsgScopeTypeEnum _enum) {
        if (_enum == null) {
            return null;
        }
        return _enum.getCode();
    }
}
