package com.campgem.modules.message.strategy;

import com.campgem.common.util.SpringContextUtils;
import com.campgem.modules.message.dto.MsgDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author: X.Tony
 */
@Service("emailSendMsgStrategy")
public class EmailSendMsgStrategy extends AbstractSendMsgStrategy {

    @Value("${spring.mail.username}")
    private String username;

    @Override
    public void send(MsgDto msgDto) {
        JavaMailSender mailSender = (JavaMailSender) SpringContextUtils.getBean("mailSender");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(msgDto.getReceiver());
        message.setSubject(msgDto.getMsgTitle());
        message.setText(msgDto.getMsgContent());
        mailSender.send(message);
    }

}
