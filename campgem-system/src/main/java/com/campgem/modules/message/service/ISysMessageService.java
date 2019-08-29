package com.campgem.modules.message.service;

import com.campgem.common.system.base.service.JeecgService;
import com.campgem.modules.message.entity.SysMessage;

/**
 * 消息管理
 * @author X.Tony
 */
public interface ISysMessageService extends JeecgService<SysMessage> {

	/**
	 * 添加系统通告消息
	 * @param sysMessage
	 */
	void sendNoticeMessage(SysMessage sysMessage);

	/**
	 * 发送站内信
	 * @param sysMessage
	 */
	void sendTopicLetter(SysMessage sysMessage);

	/**
	 * 消息持久化
	 * @param sysMessage
	 */
	public void saveMsg(SysMessage sysMessage);


}
