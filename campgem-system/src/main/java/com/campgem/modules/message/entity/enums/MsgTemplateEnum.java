package com.campgem.modules.message.entity.enums;

import java.util.ArrayList;
import java.util.List;

/**
 *消息模板配置
 * @author: X.Tony
 */
public enum MsgTemplateEnum {

    // 注册消息
    REGISTER_EMAIL_CODE("REGISTER_EMAIL_CODE", "Campgem新用户注册", "您的注册验证码为%s，请在操作页面中输入此验证码后完成注册。"),
    RESET_PASSWORD_EMAIL_CODE("RESET_PASSWORD_EMAIL_CODE", "Campgem密码重置", "您的账户正在申请重置密码，验证码为%s，请在操作页面中输入此验证码后，重新设置新密码。"),

    // 订单相关消息
    GOODS_LEAVE_MESSAGE("GOODS_LEAVE_MESSAGE", "您发布的商品有新留言", "%s: %s"),
    SERVICE_LEAVE_MESSAGE("SERVICE_LEAVE_MESSAGE", "您发布的需求有新留言", "%s: %s"),
    GOODS_ORDER_NOTIFY("GOODS_ORDER_NOTIFY", "收到一笔新的商品订单", "%s"),
    SERVICE_ORDER_NOTIFY("SERVICE_ORDER_NOTIFY", "收到一笔新的服务订单", "%s"),

    // 社团消息
    TO_BE_CLUB_MANAGER_NOTIFY("TO_BE_CLUB_MANAGER_NOTIFY", "您成为了社团管理员", "%s"),
    CLUB_DISMISS("CLUB_DISMISS", "您加入的社团已被解散", "%s"),
    CLUB_ACTIVITY_NOTIFY("CLUB_ACTIVITY_NOTIFY", "您加入的社团发布了一个新的活动", "%s: %s"),

    // 站内信消息
    TOPIC_LETTER("TOPIC_LETTER", "收到一条站内信", "%s:%s"),

    // 系统通知、反馈回复、举报回复
    NOTICE("NOTICE", "", "%s"),
    FEEDBACK_REPLY("FEEDBACK_REPLY", "平台回复了您反馈的意见", "%s"),
    REPORT_REPLY("REPORT_REPLY", "平台回复了您的举报", "%s"),
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
