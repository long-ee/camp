package com.campgem.modules.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.modules.service.entity.BusinessActivity;
import com.campgem.modules.service.mapper.BusinessActivityMapper;
import com.campgem.modules.service.service.IBusinessActivityService;
import com.campgem.modules.service.vo.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description: 商家活动
 * @Author: campgem
 * @Date:   2019-08-21
 * @Version: V1.0
 */
@Service
public class BusinessActivityServiceImpl extends ServiceImpl<BusinessActivityMapper, BusinessActivity> implements IBusinessActivityService {
	/**
	 * 本月第一天
	 */
	private static String firstDayOfThisMonth;
	
	/**
	 * 下月最后一天
	 */
	private static String lastDayOfNextMonth;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	static {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH,1);//1:本月第一天
		firstDayOfThisMonth = sdf.format(c.getTime());
		
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		lastDayOfNextMonth = sdf.format(c.getTime());
	}
	
	@Override
	public IPage<BusinessActivityListVo> queryActivityPageList(String date, String categoryId, Integer pageNo, Integer pageSize) {
		LambdaQueryWrapper<BusinessActivity> query = new LambdaQueryWrapper<>();
		query.eq(BusinessActivity::getDelFlag, 0);
		if (!categoryId.equals("all")) {
			query.eq(BusinessActivity::getCategoryId, categoryId);
		}
		int count = baseMapper.selectCount(query);
		
		Integer start = (pageNo - 1) * pageSize;
		List<BusinessActivityListVo> list = baseMapper.queryActivityPageList(date, categoryId, start, pageSize);
		Page<BusinessActivityListVo> page = new Page<>(pageNo, pageSize);
		page.setRecords(list);
		
		return page;
	}
	
	@Override
	public List<BusinessActivityCalendarVo> getActivityCalendar() {
		return baseMapper.getActivityCalendar(firstDayOfThisMonth, lastDayOfNextMonth);
	}
	
	@Override
	public BusinessActivityDetailVo queryActivityDetail(String activityId) {
		BusinessActivityDetailVo detail = baseMapper.queryActivityDetail(activityId);
		if (detail == null) {
			throw new JeecgBootException("活动不存");
		}
		
		// 相似活动
		List<BusinessActivityMoreOrSimilarListVo> similarActivity = baseMapper.querySimilarActivity(activityId, detail.getCategoryId());
		detail.setSimilarActivity(similarActivity);
		
		// 更多活动
		List<BusinessActivityMoreOrSimilarListVo> moreActivity = baseMapper.queryMoreActivity(activityId, detail.getUid());
		detail.setMoreActivity(moreActivity);
		
		return detail;
	}
	
	@Override
	public List<BusinessActivityTodayListVo> queryTodayBusinessActivityList() {
		String today = sdf.format(new Date());
		return baseMapper.queryTodayBusinessActivityList(today);
	}
	
	@Override
	public List<BusinessActivityInProgressVo> queryBusinessActivityInProgress(String businessId) {
		String today = sdf.format(new Date());
		return baseMapper.queryBusinessActivityInProgress(today, businessId);
	}
}
