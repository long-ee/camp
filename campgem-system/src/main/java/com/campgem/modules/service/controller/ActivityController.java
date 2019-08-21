package com.campgem.modules.service.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.campgem.common.api.vo.Result;
import com.campgem.common.system.base.controller.JeecgController;
import com.campgem.modules.message.entity.SysMessage;
import com.campgem.modules.message.service.ISysMessageService;
import com.campgem.modules.service.service.IBusinessActivityService;
import com.campgem.modules.service.vo.BusinessActivityCalendarVo;
import com.campgem.modules.service.vo.BusinessActivityDetailVo;
import com.campgem.modules.service.vo.BusinessActivityListVo;
import com.campgem.modules.service.vo.BusinessActivityTodayListVo;
import com.campgem.modules.university.entity.enums.CategoryTypeEnum;
import com.campgem.modules.university.service.ICategoryService;
import com.campgem.modules.university.vo.CategoryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 商家活动板块
 * @Author: ling
 * @Date: 2019-08-21
 * @Version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(tags = "活动信息管理接口")
public class ActivityController extends JeecgController<SysMessage, ISysMessageService> {
	@Resource
	private ICategoryService categoryService;
	
	@Resource
	private IBusinessActivityService businessActivityService;
	
	@ApiOperation(value = "今日活动列表", notes = "D1 生活圈")
	@GetMapping(value = "/activity/today")
	public Result<List<BusinessActivityTodayListVo>> queryTodayBusinessActivityList() {
		List<BusinessActivityTodayListVo> list = businessActivityService.queryTodayBusinessActivityList();
		return new Result<List<BusinessActivityTodayListVo>>().result(list);
	}
	
	@ApiOperation(value = "活动分类查询", notes = "D15 活动列表")
	@GetMapping(value = "/activity/category")
	public Result<List<CategoryVo>> queryActivityList() {
		Result<List<CategoryVo>> result = new Result<>();
		List<CategoryVo> data = categoryService.queryByType(CategoryTypeEnum.ACTIVITY.code());
		result.setResult(data);
		return result;
	}
	
	@ApiOperation(value = "活动分页", notes = "D15 活动列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "date", value = "活动日期", paramType = "query"),
			@ApiImplicitParam(name = "categoryId", value = "分类ID，默认all", defaultValue = "all", paramType = "query")
	})
	@GetMapping("/activity")
	public Result<IPage<BusinessActivityListVo>> queryActivityPageList(String date,
	                                                                   @RequestParam(defaultValue = "all") String categoryId,
	                                                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
	                                                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		Result<IPage<BusinessActivityListVo>> result = new Result<>();
		IPage<BusinessActivityListVo> pageList = businessActivityService.queryActivityPageList(date, categoryId, pageNo, pageSize);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	@ApiOperation(value = "活动日历", notes = "D15 活动列表")
	@GetMapping("/activity/calendar")
	public Result<List<BusinessActivityCalendarVo>> queryActivityCalendar() {
		List<BusinessActivityCalendarVo> list = businessActivityService.getActivityCalendar();
		return new Result<List<BusinessActivityCalendarVo>>().result(list);
	}
	
	@ApiOperation(value = "活动详情", notes = "D151 活动详情")
	@GetMapping("/activity/{activityId}/detail")
	@ApiImplicitParam(name = "activityId", value = "活动ID", required = true, paramType = "path")
	public Result<BusinessActivityDetailVo> queryActivityDetail(@PathVariable String activityId) {
		BusinessActivityDetailVo detail = businessActivityService.queryActivityDetail(activityId);
		return new Result<BusinessActivityDetailVo>().result(detail);
	}
}
