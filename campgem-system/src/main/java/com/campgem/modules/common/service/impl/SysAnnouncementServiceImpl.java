package com.campgem.modules.common.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.modules.bbs.dto.TopicLetterDto;
import com.campgem.modules.common.entity.SysAnnouncement;
import com.campgem.modules.common.entity.SysAnnouncementSend;
import com.campgem.modules.common.entity.enums.MsgTypeEnum;
import com.campgem.modules.common.mapper.SysAnnouncementMapper;
import com.campgem.modules.common.mapper.SysAnnouncementSendMapper;
import com.campgem.modules.common.service.ISysAnnouncementService;
import com.campgem.common.constant.CommonConstant;
import com.campgem.modules.user.dto.UserMessageReplyDto;
import com.campgem.modules.user.entity.Member;
import com.campgem.modules.user.service.IMemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 系统通告表
 * @Author: campgem
 * @Date:  2019-01-02
 * @Version: V1.0
 */
@Service
public class SysAnnouncementServiceImpl extends ServiceImpl<SysAnnouncementMapper, SysAnnouncement> implements ISysAnnouncementService {

	@Resource
	private SysAnnouncementMapper sysAnnouncementMapper;
	@Resource
	private SysAnnouncementSendMapper sysAnnouncementSendMapper;
	@Resource
	private IMemberService memberService;
	
	@Transactional
	@Override
	public void saveAnnouncement(SysAnnouncement sysAnnouncement) {
		if(sysAnnouncement.getMsgType().equals(MsgTypeEnum.ALL.code())) {
			sysAnnouncementMapper.insert(sysAnnouncement);
		}else if(sysAnnouncement.getMsgType().equals(MsgTypeEnum.USER_TYPE.code())){
			// 1.插入通告表记录
			sysAnnouncementMapper.insert(sysAnnouncement);
			// 2.插入用户通告阅读标记表记录
			List<Member> memberList = memberService.queryMemberByTypes(sysAnnouncement.getUserType());
			List<String> userIds = memberList.stream().map(Member::getId).collect(Collectors.toList());
			String anntId = sysAnnouncement.getId();
			Date refDate = new Date();
			userIds.forEach(userId ->{
				SysAnnouncementSend announcementSend = new SysAnnouncementSend();
				announcementSend.setAnntId(anntId);
				announcementSend.setUserId(userId);
				announcementSend.setReadFlag(CommonConstant.NO_READ_FLAG);
				announcementSend.setReadTime(refDate);
				sysAnnouncementSendMapper.insert(announcementSend);
			});
		}else{
			// 1.插入通告表记录
			sysAnnouncementMapper.insert(sysAnnouncement);
			// 2.插入用户通告阅读标记表记录
			String userId = sysAnnouncement.getUserIds();
			String[] userIds = userId.substring(0, (userId.length()-1)).split(",");
			String anntId = sysAnnouncement.getId();
			Date refDate = new Date();
			for(int i=0;i<userIds.length;i++) {
				SysAnnouncementSend announcementSend = new SysAnnouncementSend();
				announcementSend.setAnntId(anntId);
				announcementSend.setUserId(userIds[i]);
				announcementSend.setReadFlag(CommonConstant.NO_READ_FLAG);
				announcementSend.setReadTime(refDate);
				sysAnnouncementSendMapper.insert(announcementSend);
			}
		}
	}
	
	/**
	 * @功能：编辑消息信息
	 */
	@Transactional
	@Override
	public boolean upDateAnnouncement(SysAnnouncement sysAnnouncement) {
		// 1.更新系统信息表数据
		sysAnnouncementMapper.updateById(sysAnnouncement);
		if(sysAnnouncement.getMsgType().equals(CommonConstant.MSG_TYPE_UESR)) {
			// 2.补充新的通知用户数据
			String userId = sysAnnouncement.getUserIds();
			String[] userIds = userId.substring(0, (userId.length()-1)).split(",");
			String anntId = sysAnnouncement.getId();
			Date refDate = new Date();
			for(int i=0;i<userIds.length;i++) {
				LambdaQueryWrapper<SysAnnouncementSend> queryWrapper = new LambdaQueryWrapper<SysAnnouncementSend>();
				queryWrapper.eq(SysAnnouncementSend::getAnntId, anntId);
				queryWrapper.eq(SysAnnouncementSend::getUserId, userIds[i]);
				List<SysAnnouncementSend> announcementSends=sysAnnouncementSendMapper.selectList(queryWrapper);
				if(announcementSends.size()<=0) {
					SysAnnouncementSend announcementSend = new SysAnnouncementSend();
					announcementSend.setAnntId(anntId);
					announcementSend.setUserId(userIds[i]);
					announcementSend.setReadFlag(CommonConstant.NO_READ_FLAG);
					announcementSend.setReadTime(refDate);
					sysAnnouncementSendMapper.insert(announcementSend);
				}
			}
			// 3. 删除多余通知用户数据
			Collection<String> delUserIds = Arrays.asList(userIds);
			LambdaQueryWrapper<SysAnnouncementSend> queryWrapper = new LambdaQueryWrapper<SysAnnouncementSend>();
			queryWrapper.notIn(SysAnnouncementSend::getUserId, delUserIds);
			queryWrapper.eq(SysAnnouncementSend::getAnntId, anntId);
			sysAnnouncementSendMapper.delete(queryWrapper);
		}
		return true;
	}

	// @功能：流程执行完成保存消息通知
	@Override
	public void saveSysAnnouncement(String title, String msgContent) {
		SysAnnouncement announcement = new SysAnnouncement();
		announcement.setTitile(title);
		announcement.setMsgContent(msgContent);
		announcement.setSender("JEECG BOOT");
		announcement.setPriority(CommonConstant.PRIORITY_L);
		announcement.setMsgType(CommonConstant.MSG_TYPE_ALL);
		announcement.setSendStatus(CommonConstant.HAS_SEND);
		announcement.setSendTime(new Date());
		announcement.setDelFlag(CommonConstant.DEL_FLAG_0.toString());
		sysAnnouncementMapper.insert(announcement);
	}

	@Override
	public Page<SysAnnouncement> querySysCementPageByUserId(Page<SysAnnouncement> page, String userId,String msgCategory) {
		 return page.setRecords(sysAnnouncementMapper.querySysCementListByUserId(page, userId, msgCategory));
	}

	@Override
	public void messageReply(UserMessageReplyDto messageReplyDto) {
		messageReplyDto.paramValidation();
		SysAnnouncement sysAnnouncement = this.getById(messageReplyDto.getAnntId());
		if(null == sysAnnouncement){
			throw new JeecgBootException(StatusEnum.BadRequest);
		}
		SysAnnouncement replyMessage = new SysAnnouncement();
		replyMessage.setTitile("[回复]" + sysAnnouncement.getTitile());
		replyMessage.setMsgContent(messageReplyDto.getReplyContent());
		replyMessage.setMsgType(MsgTypeEnum.USER.code());
		replyMessage.setSendStatus("1");
		replyMessage.setSendTime(new Date());
		replyMessage.setSender(messageReplyDto.getMemberId());
		replyMessage.setUserIds(sysAnnouncement.getSender());
		replyMessage.setMsgCategory(sysAnnouncement.getMsgCategory());
		this.saveAnnouncement(replyMessage);
	}

	@Override
	public void sendTopicLetter(TopicLetterDto topicLetterDto) {
		if(StringUtils.isBlank(topicLetterDto.getSenderId())){
			throw new JeecgBootException(StatusEnum.BadRequest);
		}
		SysAnnouncement letter = new SysAnnouncement();
		letter.setTitile("收到一条站内信");
		letter.setMsgContent(topicLetterDto.getLetterContent());
		letter.setMsgType(MsgTypeEnum.USER.code());
		letter.setSendStatus("1");
		letter.setSendTime(new Date());
		letter.setSender(topicLetterDto.getSenderId());
		letter.setUserIds(topicLetterDto.getReceiverId());
		letter.setMsgCategory("TOPIC_LETTER");
		this.saveAnnouncement(letter);
	}

}
