package com.campgem.modules.university.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.util.DateUtils;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.university.dto.ClubActivityDto;
import com.campgem.modules.university.dto.ClubActivityQueryDto;
import com.campgem.modules.university.entity.ClubActivity;
import com.campgem.modules.university.service.IClubActivityService;
import com.campgem.modules.university.service.IClubService;
import com.campgem.modules.university.vo.ClubActivityCalendarVo;
import com.campgem.modules.university.vo.ClubActivityVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


@Slf4j
@Api(tags="社团活动信息接口")
@RestController
@RequestMapping("/api/v1")
public class ClubActivityController {
	@Resource
	private IClubService clubService;
	@Resource
	private IClubActivityService clubActivityService;

	@ApiOperation(value="社团活动信息接口-活动日历查询", notes="E14 参数：当前登录人员所属学校ID")
	@GetMapping(value = "/university/club/activity/calendar")
	public Result<?> activityCalendar(@RequestParam(name="universityId",required=true) String universityId) {
//		String universityId = SecurityUtils.getCurrentUser().getUniversityId();
		if(StringUtils.isBlank(universityId)){
			return Result.error(StatusEnum.BadRequest.code(), "校园ID不能为空");
		}
		List<ClubActivityCalendarVo> activityCalendarVos = clubActivityService.queryActivityCalendar(universityId);
		return new Result<List<ClubActivityCalendarVo>>().result(activityCalendarVos);
	}

	@ApiOperation(value="社团活动信息接口-今天活动列表查询", notes="E1 今天活动列表查询（默认查出条数小于等于10条）")
	@GetMapping(value = "/university/club/activity/list/today")
	public Result<IPage<ClubActivityVo>> queryTodayActivities(ClubActivityQueryDto queryDto) {
		Page<ClubActivityQueryDto> page = new Page<ClubActivityQueryDto>(1, 10);
		queryDto.setStartDate(DateUtils.formatDate());
		queryDto.setEndDate(DateUtils.formatDate());
		IPage<ClubActivityVo> activityVos = clubActivityService.queryPageList(page, queryDto);
		return new Result<IPage<ClubActivityVo>>().result(activityVos);
	}
	

	@ApiOperation(value="社团活动信息-社团活动列表分页查询", notes="E14 G152 社团活动列表查询（universityId 查询校园活动，clubId 查询俱乐部）")
	@GetMapping(value = "/university/club/activity/list")
	public Result<IPage<ClubActivityVo>> queryActivities(ClubActivityQueryDto queryDto,
												@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
												@RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
		Page<ClubActivityQueryDto> page = new Page<ClubActivityQueryDto>(pageNo, pageSize);
		IPage<ClubActivityVo> activityVos = clubActivityService.queryPageList(page, queryDto);
		return new Result<IPage<ClubActivityVo>>().result(activityVos);
	}
	

	@ApiOperation(value="社团活动信息-活动详情查询", notes="E13 社团活动详情查询")
	@GetMapping(value = "/university/club/activity/details")
	public Result<ClubActivityVo> details(String activityId) {
		ClubActivityVo clubActivity = clubActivityService.queryDetails(activityId);
		String memberId = SecurityUtils.getCurrentUserMemberId();
		boolean isClubMember = clubService.isClubMember(clubActivity.getClubId(), memberId);
		clubActivity.setClubMember(isClubMember);
		return new Result<ClubActivityVo>().result(clubActivity);
	}


	@ApiOperation(value="社团活动信息-创建活动", notes="G153 创建/编辑社团活动-创建活动")
	@PostMapping(value = "/university/club/activity/create")
	public Result create(@Valid ClubActivityDto clubActivityDto) {
		String memberId = SecurityUtils.getCurrentUserMemberId();
		clubActivityDto.setPublisherId(memberId);
		clubActivityService.create(clubActivityDto);
		return Result.ok();
	}

	@ApiOperation(value="社团活动信息-编辑活动", notes="G153 创建/编辑社团活动-编辑活动")
	@PostMapping(value = "/university/club/activity/edit")
	public Result edit(@Valid ClubActivityDto clubActivityDto) {
		clubActivityService.edit(clubActivityDto);
		return Result.ok();
	}

}
