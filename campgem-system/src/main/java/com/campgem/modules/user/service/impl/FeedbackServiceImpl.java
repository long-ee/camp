package com.campgem.modules.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.constant.CommonConstant;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.util.BeanConvertUtils;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.message.dto.MsgDto;
import com.campgem.modules.message.entity.enums.MsgScopeTypeEnum;
import com.campgem.modules.message.entity.enums.MsgSendTypeEnum;
import com.campgem.modules.message.entity.enums.MsgTemplateEnum;
import com.campgem.modules.message.strategy.SendMsgStrategyFactory;
import com.campgem.modules.user.dto.FeedbackDto;
import com.campgem.modules.user.dto.FeedbackReplyDto;
import com.campgem.modules.user.entity.Feedback;
import com.campgem.modules.user.entity.enums.FeedbackStatusEnum;
import com.campgem.modules.user.mapper.FeedbackMapper;
import com.campgem.modules.user.service.IFeedbackService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 举报/反馈
 * @Author: ling
 * @Date: 2019-08-30
 * @Version: V1.0
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements IFeedbackService {
	
	private static List<String> feedbackCategory = new ArrayList<String>() {{
		add("Suggestion");
		add("Tip-off");
	}};
	
	@Override
	public boolean userFeedbackReport(FeedbackDto feedbackDto) {
		if (!feedbackCategory.contains(feedbackDto.getCategory())) {
			throw new JeecgBootException(StatusEnum.FeedbackCategoryUnknownError);
		}
		
		Feedback feedback = BeanConvertUtils.copy(feedbackDto, Feedback.class);
		feedback.setUid(SecurityUtils.getCurrentUserUid());
		feedback.setCreateTime(new Date());
		
		return save(feedback);
	}

	@Override
	public void reply(FeedbackReplyDto replyDto) {
		Feedback feedback = this.getById(replyDto.getId());
		if(null == feedback) {
			throw new JeecgBootException(StatusEnum.NotFound);
		}
		// 修改反馈状态
		feedback.setStatus(FeedbackStatusEnum.REPLIED.code());
		this.updateById(feedback);
		// 发送消息
		MsgDto msgDto = new MsgDto();
		msgDto.setSender(CommonConstant.SYSTEM_ACCOUNT_NAME);
		if("Suggestion".equalsIgnoreCase(feedback.getCategory())){
			msgDto.setMsgType(MsgTemplateEnum.FEEDBACK_REPLY.msgType());
		}else {
			msgDto.setMsgType(MsgTemplateEnum.REPORT_REPLY.msgType());
		}
		msgDto.setReceiver(feedback.getReportedUid());
		msgDto.setScope(MsgScopeTypeEnum.CUSTOM_ASSIGN_USER.code());
		msgDto.setParams(new Object[]{replyDto.getReplyContent()});
		SendMsgStrategyFactory.getInstance(MsgSendTypeEnum.PLATFORM_MSG).send(msgDto);

	}
}
