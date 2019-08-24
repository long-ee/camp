package com.campgem.modules.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.util.BeanConvertUtils;
import com.campgem.modules.service.dto.manage.MServiceQueryDto;
import com.campgem.modules.service.entity.BusinessActivity;
import com.campgem.modules.service.entity.BusinessActivityImages;
import com.campgem.modules.service.mapper.BusinessActivityMapper;
import com.campgem.modules.service.service.IBusinessActivityImagesService;
import com.campgem.modules.service.service.IBusinessActivityService;
import com.campgem.modules.service.vo.*;
import com.campgem.modules.service.vo.manage.MBusinessActivityDetailVo;
import com.campgem.modules.service.vo.manage.MBusinessActivityListVo;
import com.campgem.modules.service.vo.manage.MBusinessActivityVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: 商家活动
 * @Author: campgem
 * @Date:   2019-08-21
 * @Version: V1.0
 */
@Service
public class BusinessActivityServiceImpl extends ServiceImpl<BusinessActivityMapper, BusinessActivity> implements IBusinessActivityService {
	@Resource
	private IBusinessActivityImagesService businessActivityImagesService;
	
	/**
	 * 本月第一天
	 */
	private static String firstDayOfThisMonth;
	
	/**
	 * 下月最后一天
	 */
	private static String lastDayOfNextMonth;
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	
	static {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH,1);//1:本月第一天
		firstDayOfThisMonth = dateFormat.format(c.getTime());
		
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		lastDayOfNextMonth = dateFormat.format(c.getTime());
	}
	
	@Override
	public IPage<BusinessActivityListVo> queryActivityPageList(String date, String categoryId, Page page) {
		return baseMapper.queryActivityPageList(page, date, categoryId);
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
		String today = dateFormat.format(new Date());
		return baseMapper.queryTodayBusinessActivityList(today);
	}
	
	@Override
	public List<BusinessActivityInProgressVo> queryBusinessActivityInProgress(String businessId) {
		String today = dateFormat.format(new Date());
		return baseMapper.queryBusinessActivityInProgress(today, businessId);
	}
	
	@Override
	public IPage<MBusinessActivityListVo> queryManagePageList(Page<MServiceQueryDto> page, MServiceQueryDto queryDto) {
		return baseMapper.queryManagePageList(page, queryDto);
	}
	
	@Override
	@Transactional
	public boolean saveOrUpdate(MBusinessActivityVo activityVo, boolean isUpdate) {
		if (activityVo.getImages().length == 0) {
			throw new JeecgBootException("活动图片不能为空");
		}
		
		BusinessActivity businessActivity = BeanConvertUtils.copy(activityVo, BusinessActivity.class);
		
		// 设置时间属性
		String[] dates = activityVo.getDateRange().split("~");
		String[] times = activityVo.getTimeRange().split("~");
		try {
			businessActivity.setStartDate(dateFormat.parse(dates[0]));
			businessActivity.setEndDate(dateFormat.parse(dates[1]));
			
			businessActivity.setStartTime(timeFormat.parse(times[0]));
			businessActivity.setEndTime(timeFormat.parse(times[1]));
		} catch (ParseException e) {
			throw new JeecgBootException("时间格式错误");
		}
		
		String uuid;
		if (!isUpdate) {
			// 新增
			uuid = UUID.randomUUID().toString().replaceAll("-", "");
			businessActivity.setId(uuid);
			businessActivity.setDelFlag(0);
			businessActivity.setCreateTime(new Date());
			save(businessActivity);
		} else {
			uuid = businessActivity.getId();
			updateById(businessActivity);
			
			// 删除图片列表
			businessActivityImagesService.remove(new LambdaQueryWrapper<BusinessActivityImages>()
					.eq(BusinessActivityImages::getBusinessActivityId, uuid));
		}
		
		for (BusinessActivityImages image : activityVo.getImages()) {
			image.setBusinessActivityId(uuid);
		}
		
		// 添加
		businessActivityImagesService.saveBatch(Arrays.asList(activityVo.getImages()));
		
		return true;
	}
	
	@Override
	public MBusinessActivityDetailVo queryManageBusinessActivityDetail(String id) {
		return baseMapper.queryManageBusinessActivityDetail(id);
	}
}
