package com.campgem.modules.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.user.dto.FeedbackDto;
import com.campgem.modules.user.entity.Feedback;

/**
 * @Description: 举报/反馈
 * @Author: campgem
 * @Date:   2019-08-30
 * @Version: V1.0
 */
public interface IFeedbackService extends IService<Feedback> {
	
	boolean userFeedbackReport(FeedbackDto feedbackDto);
}
