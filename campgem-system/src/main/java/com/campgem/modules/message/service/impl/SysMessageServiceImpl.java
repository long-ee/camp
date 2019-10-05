package com.campgem.modules.message.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.constant.CommonConstant;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.enums.YesOrNoEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.util.BeanConvertUtils;
import com.campgem.modules.message.dto.MsgDto;
import com.campgem.modules.message.entity.SysMessage;
import com.campgem.modules.message.entity.SysMessageSend;
import com.campgem.modules.message.entity.enums.MsgScopeTypeEnum;
import com.campgem.modules.message.entity.enums.MsgSendTypeEnum;
import com.campgem.modules.message.entity.enums.MsgTemplateEnum;
import com.campgem.modules.message.mapper.SysMessageMapper;
import com.campgem.modules.message.mapper.SysMessageSendMapper;
import com.campgem.modules.message.service.ISysMessageService;
import com.campgem.modules.message.strategy.SendMsgStrategyFactory;
import com.campgem.modules.user.entity.Member;
import com.campgem.modules.user.service.IMemberService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 系统通告表
 * @Author: campgem
 * @Date:  2019-01-02
 * @Version: V1.0
 */
@Service

public class SysMessageServiceImpl extends ServiceImpl<SysMessageMapper, SysMessage> implements ISysMessageService {

	@Resource
	private SysMessageMapper sysMessageMapper;
	@Resource
	private SysMessageSendMapper sysMessageSendMapper;
	@Resource
	private IMemberService memberService;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void saveMsg(SysMessage sysMessage) {
		Member member = memberService.getById(sysMessage.getSender());
		sysMessage.setSenderName(member.getBusinessNameCommon());
		if(StringUtils.equalsIgnoreCase(MsgScopeTypeEnum.ALL.code(), sysMessage.getScope())) {
			// 发送全部
			sysMessageMapper.insert(sysMessage);
			return;
		}
		List<String> receivers = new ArrayList<>();
		if(StringUtils.equalsIgnoreCase(MsgScopeTypeEnum.CUSTOM_ASSIGN_USER.code(), sysMessage.getScope())){
			receivers = Arrays.asList(sysMessage.getReceiver());
		}else{
			List<Member> memberList = memberService.queryMemberByTypes(sysMessage.getScope());
			receivers = memberList.stream().map(Member::getId).collect(Collectors.toList());
		}
		// 发送指定对象
		sysMessageMapper.insert(sysMessage);
		if(CollectionUtils.isNotEmpty(receivers)){
			receivers.forEach(receiver ->{
				SysMessageSend announcementSend = new SysMessageSend();
				announcementSend.setMsgId(sysMessage.getId());
				announcementSend.setMemberId(receiver);
				announcementSend.setReadFlag(CommonConstant.NO_READ_FLAG);
//				announcementSend.setReadTime(refDate);
				sysMessageSendMapper.insert(announcementSend);
			});
		}
	}

	@Override
	public void sendNoticeMessage(SysMessage sysMessage) {
		MsgDto msgDto = BeanConvertUtils.copy(sysMessage, MsgDto.class);
		msgDto.setSender(CommonConstant.SYSTEM_ACCOUNT_NAME);
		msgDto.setMsgType(MsgTemplateEnum.NOTICE.msgType());
		msgDto.setMsgTitle(sysMessage.getMsgTitle());
		msgDto.setMsgContent(sysMessage.getMsgContent());
		msgDto.setNeedAssemble(false);
		SendMsgStrategyFactory.getInstance(MsgSendTypeEnum.PLATFORM_MSG).send(msgDto);
	}

	@Override
	public void sendTopicLetter(SysMessage sysMessage) {
		Member receiver =  memberService.getById(sysMessage.getReceiver());
		if(null == receiver){
			throw new JeecgBootException(StatusEnum.BadRequest);
		}
		if(StringUtils.equalsIgnoreCase(YesOrNoEnum.NO.code(), receiver.getAllowChat())){
			throw new JeecgBootException(StatusEnum.BadRequest);
		}
		MsgDto msgDto = BeanConvertUtils.copy(sysMessage, MsgDto.class);
		msgDto.setMsgType(MsgTemplateEnum.TOPIC_LETTER.msgType());
		msgDto.setScope(MsgScopeTypeEnum.CUSTOM_ASSIGN_USER.code());
		msgDto.setParams(new Object[]{sysMessage.getSenderName(), sysMessage.getMsgContent()});
		SendMsgStrategyFactory.getInstance(MsgSendTypeEnum.PLATFORM_MSG).send(msgDto);
	}

}
