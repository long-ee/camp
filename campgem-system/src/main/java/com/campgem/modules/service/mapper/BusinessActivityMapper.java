package com.campgem.modules.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.modules.service.dto.manage.MServiceQueryDto;
import com.campgem.modules.service.entity.BusinessActivity;
import com.campgem.modules.service.vo.*;
import com.campgem.modules.service.vo.manage.MBusinessActivityDetailVo;
import com.campgem.modules.service.vo.manage.MBusinessActivityListVo;
import com.campgem.modules.user.vo.UserBusinessActivityListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 商家活动
 * @Author: campgem
 * @Date: 2019-08-21
 * @Version: V1.0
 */
public interface BusinessActivityMapper extends BaseMapper<BusinessActivity> {
	
	IPage<BusinessActivityListVo> queryActivityPageList(Page page,
	                                                    @Param("date") String date,
	                                                    @Param("categoryId") String categoryId);
	
	List<BusinessActivityCalendarVo> getActivityCalendar(@Param("first") String firstDayOfThisMonth,
	                                                     @Param("last") String lastDayOfNextMonth);
	
	List<BusinessActivityTodayListVo> queryTodayBusinessActivityList(@Param("today") String today);
	
	List<BusinessActivityInProgressVo> queryBusinessActivityInProgress(@Param("today") String today,
	                                                                   @Param("businessId") String businessId);
	
	BusinessActivityDetailVo queryActivityDetail(@Param("activityId") String activityId);
	
	List<BusinessActivityMoreOrSimilarListVo> querySimilarActivity(@Param("activityId") String activityId,
	                                                               @Param("categoryId") String categoryId);
	
	List<BusinessActivityMoreOrSimilarListVo> queryMoreActivity(@Param("activityId") String activityId,
	                                                            @Param("businessId") String businessId);
	
	IPage<MBusinessActivityListVo> queryManagePageList(Page page, @Param("queryDto") MServiceQueryDto queryDto);
	
	MBusinessActivityDetailVo queryManageBusinessActivityDetail(@Param("activityId") String activityId);
	
	IPage<UserBusinessActivityListVo> queryPageList(Page page);
}
