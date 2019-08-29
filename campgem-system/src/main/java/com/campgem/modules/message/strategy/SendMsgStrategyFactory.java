package com.campgem.modules.message.strategy;

import com.campgem.common.util.SpringContextUtils;
import com.campgem.modules.message.entity.enums.MsgSendTypeEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息发送工厂类
 * @author: X.Tony
 */
public class SendMsgStrategyFactory {

    public static Map<String, ISendMsgStrategy>  sender = new HashMap<>();

    static {
        EmailSendMsgStrategy emailSendMsgStrategy = (EmailSendMsgStrategy) SpringContextUtils.getBean(MsgSendTypeEnum.EMAIL.getImplClass());
        PlatformServiceSendMsgStrategy platformServiceSendMsgStrategy = (PlatformServiceSendMsgStrategy) SpringContextUtils.getBean(MsgSendTypeEnum.PLATFORM_MSG.getImplClass());
        sender.put(MsgSendTypeEnum.EMAIL.getType(), emailSendMsgStrategy);
        sender.put(MsgSendTypeEnum.PLATFORM_MSG.getType(), platformServiceSendMsgStrategy);
    }

    /**
     * 获取消息发送
     * @param msgSendTypeEnum
     * @return
     */
    public static ISendMsgStrategy getInstance(MsgSendTypeEnum msgSendTypeEnum){
        return sender.get(msgSendTypeEnum.getType());
    }


}
