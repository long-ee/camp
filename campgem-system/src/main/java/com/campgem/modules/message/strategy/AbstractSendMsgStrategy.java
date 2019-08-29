package com.campgem.modules.message.strategy;

import com.campgem.modules.message.dto.MsgDto;
import com.campgem.modules.message.entity.enums.MsgTemplateEnum;

/**
 *
 * @author: X.Tony
 */
public abstract class AbstractSendMsgStrategy implements ISendMsgStrategy {

    /**
     * 消息组组装
     * @param msgDto
     * @return
     */
    @Override
    public MsgDto assemble(MsgDto msgDto){
        MsgTemplateEnum templateEnum = MsgTemplateEnum.getByCode(msgDto.getMsgTemplateCode());
        if(null == templateEnum){
            return msgDto;
        }
        msgDto.setMsgType(templateEnum.getMsgType().code());
        msgDto.setMsgTitle(templateEnum.getMsgTitle());
        String content = String.format(templateEnum.getMsgContent(), msgDto.getParams());
        msgDto.setMsgContent(content);
        return msgDto;
    }

    /**
     * 消息发送
     */
    @Override
    public void send(MsgDto msgDto){

    }

}