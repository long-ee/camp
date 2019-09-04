package com.campgem.modules.message.entity.enums;

import java.util.ArrayList;
import java.util.List;

/**
 *消息模板配置
 * @author: X.Tony
 */
public enum MsgTemplateEnum {

    // 注册消息
    REGISTER_EMAIL_CODE("REGISTER_EMAIL_CODE", "Campgem new user register", "Your registration verification code is %s. Please enter this verification code in the operation page and complete the registration."),
    RESET_PASSWORD_EMAIL_CODE("RESET_PASSWORD_EMAIL_CODE", "Campgem password reset", "Your account is applying for a password reset. The password is %s. Please reset the new password after entering the password in the operation page."),

    // 订单相关消息
    GOODS_LEAVE_MESSAGE("GOODS_LEAVE_MESSAGE", "There is a new message for your goods", "%s: %s"),
    SERVICE_LEAVE_MESSAGE("SERVICE_LEAVE_MESSAGE", "New message for your requirement", "%s: %s"),
    GOODS_ORDER_NOTIFY("GOODS_ORDER_NOTIFY", "Received a new goods order", "%s"),
    SERVICE_ORDER_NOTIFY("SERVICE_ORDER_NOTIFY", "Received a new service order", "%s"),

    // 社团消息
    TO_BE_CLUB_MANAGER_NOTIFY("TO_BE_CLUB_MANAGER_NOTIFY", "You became a club administrator", "%s"),
    CLUB_DISMISS("CLUB_DISMISS", "The club you joined has been disbanded", "%s"),
    CLUB_ACTIVITY_NOTIFY("CLUB_ACTIVITY_NOTIFY", "The club you join has launched a new activity", "%s: %s"),

    // 站内信消息
    TOPIC_LETTER("TOPIC_LETTER", "Received an in-station letter", "%s:%s"),

    // 系统通知、反馈回复、举报回复
    NOTICE("NOTICE", "", "%s"),
    FEEDBACK_REPLY("FEEDBACK_REPLY", "The platform responded to your feedback", "%s"),
    REPORT_REPLY("REPORT_REPLY", "The platform responded to your report", "%s"),
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
