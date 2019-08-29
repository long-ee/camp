package com.campgem.modules.message.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.message.dto.MessageVo;
import com.campgem.modules.message.entity.SysMessageSend;

/**
 * @Description: 用户通告阅读标记表
 * @Author: campgem
 * @Date:  2019-02-21
 * @Version: V1.0
 */
public interface ISysMessageSendService extends IService<SysMessageSend> {
	/**
	 * @功能：获取我的消息
	 * @param messageVo
	 * @return
	 */
	Page<MessageVo> queryUserMessageList(Page<MessageVo> page, MessageVo messageVo);


	/**
	 * 标记某条消息已读
	 * @param msgId
	 */
	void flagReadTag(String msgId);

	/**
	 * 标记全部已读
	 * @param memberId
	 */
	void flagReadTagOfUserAllMessage(String memberId);

	/**
	 * 查询某用户未读消息
	 * @param memberId
	 */
	int countUserUnReadMessage(String memberId);

}
