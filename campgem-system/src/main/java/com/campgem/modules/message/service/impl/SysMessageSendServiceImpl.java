package com.campgem.modules.message.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.campgem.modules.message.entity.SysMessageSend;
import com.campgem.modules.message.mapper.SysMessageSendMapper;
import com.campgem.modules.common.dto.AnnouncementSendModel;
import com.campgem.modules.message.service.ISysMessageSendService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 用户通告阅读标记表
 * @Author: campgem
 * @Date:  2019-02-21
 * @Version: V1.0
 */
@Service
public class SysMessageSendServiceImpl extends ServiceImpl<SysMessageSendMapper, SysMessageSend> implements ISysMessageSendService {

	@Resource
	private SysMessageSendMapper sysAnnouncementSendMapper;
	
	@Override
	public List<String> queryByUserId(String userId) {
		return sysAnnouncementSendMapper.queryByUserId(userId);
	}

	@Override
	public Page<AnnouncementSendModel> getMyAnnouncementSendPage(Page<AnnouncementSendModel> page,
                                                                 AnnouncementSendModel announcementSendModel) {
		 return page.setRecords(sysAnnouncementSendMapper.getMyAnnouncementSendList(page, announcementSendModel));
	}

}
