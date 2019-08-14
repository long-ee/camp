package com.campgem.modules.message.handle.impl;

import com.campgem.modules.message.handle.ISendMsgHandle;
import com.campgem.common.util.SpringContextUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSendMsgHandle implements ISendMsgHandle {

	@Value("${spring.mail.username}")
	private String username;

	@Override
	public void SendMsg(String es_receiver, String es_title, String es_content) {
		JavaMailSender mailSender = (JavaMailSender) SpringContextUtils.getBean("mailSender");
		SimpleMailMessage message = new SimpleMailMessage();
		// 设置发送方邮箱地址
		message.setFrom(username);
		message.setTo(es_receiver);
		message.setSubject(es_title);
		message.setText(es_content);
		mailSender.send(message);
	}
}
