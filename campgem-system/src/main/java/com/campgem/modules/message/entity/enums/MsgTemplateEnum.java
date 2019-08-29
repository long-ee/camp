package com.campgem.modules.message.entity.enums;

import java.util.ArrayList;
import java.util.List;

/**
 *消息模板配置
 * @author: X.Tony
 */
public enum MsgTemplateEnum {
    NOTICE("NOTICE", "", "%s"),
    TOPIC_LETTER("TOPIC_LETTER", "收到一条站内信", "%s:%s"),
    REGISTER_EMAIL_CODE("REGISTER_EMAIL_CODE", "Campgem新用户注册", "您的注册验证码为%s，请在操作页面中输入此验证码后完成注册。"),
    RESET_PASSWORD_EMAIL_CODE("RESET_PASSWORD_EMAIL_CODE", "Campgem密码重置", "您的账户正在申请重置密码，验证码为%s，请在操作页面中输入此验证码后，重新设置新密码。")

    ;
    /**
     * 消息类型
     */
    private final String msgType;

    /**
     * 枚举描述(默认为消息标题)
     */
    private final String msgTitle;

    /**
     * 消息标题（英文）
     */
    private final String msgContent;


    MsgTemplateEnum(String msgType, String msgTitle, String msgContent) {
        this.msgTitle = msgTitle;
        this.msgContent = msgContent;
        this.msgType = msgType;
    }

    public String msgType() {
        return msgType;
    }

    public String msgTitle() {
        return msgTitle;
    }

    public String msgContent() {
        return msgContent;
    }

    /**
     * 通过枚举<code>code</code>获得枚举
     *
     * @param msgType
     * @return MsgTemplateEnum
     */
    public static MsgTemplateEnum getByMsgType(String msgType) {
        for (MsgTemplateEnum _enum : values()) {
            if (_enum.msgType().equals(msgType)) {
                return _enum;
            }
        }
        return null;
    }

    /**
     * 获取全部枚举
     *
     * @return List<MsgTemplateEnum>
     */
    public List<MsgTemplateEnum> getAllEnum() {
        List<MsgTemplateEnum> list = new ArrayList<MsgTemplateEnum>();
        for (MsgTemplateEnum _enum : values()) {
            list.add(_enum);
        }
        return list;
    }

    /**
     * 获取全部枚举值
     *
     * @return List<String>
     */
    public List<String> getAllEnumCode() {
        List<String> list = new ArrayList<String>();
        for (MsgTemplateEnum _enum : values()) {
            list.add(_enum.msgType());
        }
        return list;
    }
}
