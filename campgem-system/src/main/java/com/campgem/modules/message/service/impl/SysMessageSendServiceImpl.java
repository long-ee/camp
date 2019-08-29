package com.campgem.modules.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.enums.YesOrNoEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.modules.message.dto.MessageVo;
import com.campgem.modules.message.entity.SysMessageSend;
import com.campgem.modules.message.mapper.SysMessageSendMapper;
import com.campgem.modules.message.service.ISysMessageSendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: 用户通告阅读标记表
 * @Author: campgem
 * @Date:  2019-02-21
 * @Version: V1.0
 */
@Service
public class SysMessageSendServiceImpl extends ServiceImpl<SysMessageSendMapper, SysMessageSend> implements ISysMessageSendService {

	@Resource
	private SysMessageSendMapper sysMessageSendMapper;
	
	@Override
	public Page<MessageVo> queryUserMessageList(Page<MessageVo> page, MessageVo messageVo) {
		 return page.setRecords(sysMessageSendMapper.queryUserMessageList(page, messageVo));
	}

	@Override
	public void flagReadTag(String msgId) {
		SysMessageSend messageSend = this.getById(msgId);
		if(null == messageSend){
			throw new JeecgBootException(StatusEnum.BadRequest);
		}
		messageSend.setReadFlag(YesOrNoEnum.YES.code());
		this.updateById(messageSend);
	}

	@Override
	public void flagReadTagOfUserAllMessage(String memberId) {
		LambdaUpdateWrapper<SysMessageSend> updateWrapper = new LambdaUpdateWrapper<>();
		updateWrapper.eq(SysMessageSend::getMemberId, memberId)
				.set(SysMessageSend::getReadFlag, YesOrNoEnum.YES.code());
		this.update(updateWrapper);
	}

	@Override
	public int countUserUnReadMessage(String memberId) {
		LambdaQueryWrapper<SysMessageSend> queryWrapper = new LambdaQueryWrapper();
		queryWrapper.eq(SysMessageSend::getMemberId, memberId).eq(SysMessageSend::getReadFlag, YesOrNoEnum.NO.code());
		int count = this.count(queryWrapper);
		return count;
	}

}
