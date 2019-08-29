package com.campgem.modules.message.strategy;

import com.campgem.modules.message.dto.MsgDto;
import com.campgem.modules.message.entity.SysMessage;
import com.campgem.modules.message.service.ISysMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author: X.Tony
 */
@Service("platformServiceSendMsgStrategy")
public class PlatformServiceSendMsgStrategy extends AbstractSendMsgStrategy {

    @Resource
    private ISysMessageService sysMessageService;


    @Override
    public void send(MsgDto msgDto) {
        assemble(msgDto);
        SysMessage sysMessage = convert(msgDto);
        sysMessageService.saveMsg(sysMessage);
    }

    private SysMessage convert(MsgDto msgDto){
        SysMessage sysMessage = new SysMessage();
        sysMessage.setMsgContent(msgDto.getMsgContent());
        sysMessage.setMsgTitle(msgDto.getMsgTitle());
        sysMessage.setMsgType(msgDto.getMsgType());
        sysMessage.setReceiver(msgDto.getReceiver());
        sysMessage.setScope(msgDto.getScope());
        sysMessage.setSender(msgDto.getSender());
        sysMessage.setSendTime(new Date());
        return sysMessage;
    }
}
