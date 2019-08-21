package com.campgem.modules.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.service.entity.BusinessActivity;
import com.campgem.modules.service.vo.*;

import java.util.List;

/**
 * @Description: 商家活动
 * @Author: campgem
 * @Date:   2019-08-21
 * @Version: V1.0
 */
public interface IBusinessActivityService extends IService<BusinessActivity> {
	
	IPage<BusinessActivityListVo> queryActivityPageList(String date, String categoryId, Integer pageNo, Integer pageSize);
	
	List<BusinessActivityCalendarVo> getActivityCalendar();
	
	BusinessActivityDetailVo queryActivityDetail(String activityId);
	
	List<BusinessActivityTodayListVo> queryTodayBusinessActivityList();
	
	List<BusinessActivityInProgressVo> queryBusinessActivityInProgress(String businessId);
}
