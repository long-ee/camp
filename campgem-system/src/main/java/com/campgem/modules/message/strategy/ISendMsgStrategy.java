package com.campgem.modules.message.strategy;

import com.campgem.modules.message.dto.MsgDto;

/**
 * @author: X.Tony
 */
public interface ISendMsgStrategy {
    /**
     * 组装消息
     * @param msgDto
     * @return
     */
    MsgDto assemble(MsgDto msgDto);

    /**
     * 发送消息
     * @param msgDto
     */
    void send(MsgDto msgDto);
}
