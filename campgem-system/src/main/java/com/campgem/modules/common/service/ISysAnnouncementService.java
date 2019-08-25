package com.campgem.modules.common.service;

import com.campgem.modules.bbs.dto.TopicDto;
import com.campgem.modules.bbs.dto.TopicLetterDto;
import com.campgem.modules.common.entity.SysAnnouncement;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.user.dto.UserMessageReplyDto;

/**
 * 消息管理
 * @author X.Tony
 */
public interface ISysAnnouncementService extends IService<SysAnnouncement> {

	public void saveAnnouncement(SysAnnouncement sysAnnouncement);

	public boolean upDateAnnouncement(SysAnnouncement sysAnnouncement);

	public void saveSysAnnouncement(String title, String msgContent);

	public Page<SysAnnouncement> querySysCementPageByUserId(Page<SysAnnouncement> page,String userId,String msgCategory);

	/**
	 * 用户消息回复
	 * @param messageReplyDto
	 */
	public void messageReply(UserMessageReplyDto messageReplyDto);

	/**
	 * 发送站内信（BBS）
	 * @param topicLetterDto
	 */
	public void sendTopicLetter(TopicLetterDto topicLetterDto);

}
