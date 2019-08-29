package com.campgem.modules.message.entity.enums;

import java.util.ArrayList;
import java.util.List;

/**
 *消息模板配置
 * @author: X.Tony
 */
public enum MsgTemplateEnum {

    TOPIC_LETTER("TOPIC_LETTER", MsgTypeEnum.TOPIC_LETTER, "收到一条站内信", "%s:%s")
   ;
    /**
     * 枚举值
     */
    private final String code;

    /**
     * 枚举描述(默认为消息标题)
     */
    private final String msgTitle;

    /**
     * 消息标题（英文）
     */
    private final String msgContent;


    private final MsgTypeEnum msgType;


    MsgTemplateEnum(String code, MsgTypeEnum msgType, String msgTitle, String msgContent) {
        this.code = code;
        this.msgTitle = msgTitle;
        this.msgContent = msgContent;
        this.msgType = msgType;
    }

    public String getCode() {
        return code;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public MsgTypeEnum getMsgType() {
        return msgType;
    }

    /**
     * 通过枚举<code>code</code>获得枚举
     *
     * @param code
     * @return SmsVerifyCodeTypeEnum
     */
    public static MsgTemplateEnum getByCode(String code) {
        for (MsgTemplateEnum _enum : values()) {
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }
        return null;
    }

    /**
     * 获取全部枚举
     *
     * @return List<SmsVerifyCodeTypeEnum>
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
            list.add(_enum.getCode());
        }
        return list;
    }
}
