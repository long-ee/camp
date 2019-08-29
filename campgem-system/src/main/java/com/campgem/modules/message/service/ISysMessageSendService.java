package com.campgem.modules.message.service;

import java.util.List;

import com.campgem.modules.message.entity.SysMessageSend;
import com.campgem.modules.common.dto.AnnouncementSendModel;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 用户通告阅读标记表
 * @Author: campgem
 * @Date:  2019-02-21
 * @Version: V1.0
 */
public interface ISysMessageSendService extends IService<SysMessageSend> {

	public List<String> queryByUserId(String userId);
	
	/**
	 * @功能：获取我的消息
	 * @param announcementSendModel
	 * @return
	 */
	public Page<AnnouncementSendModel> getMyAnnouncementSendPage(Page<AnnouncementSendModel> page, AnnouncementSendModel announcementSendModel);

}
