package com.campgem.modules.university.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.common.util.DateUtils;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.university.dto.ClubActivityQueryDto;
import com.campgem.modules.university.service.IClubActivityService;
import com.campgem.modules.university.service.IClubService;
import com.campgem.modules.university.vo.ClubActivityVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Slf4j
@Api(tags="社团活动信息接口")
@RestController
@RequestMapping("/api/v1")
public class ClubActivityController {
	@Resource
	private IClubService clubService;
	@Resource
	private IClubActivityService clubActivityService;
	

	@ApiOperation(value="社团活动信息接口-今天活动列表查询", notes="E1 今天活动列表查询（默认查出条数小于等于10条）")
	@GetMapping(value = "/university/club/activity/list/today")
	public Result<IPage<ClubActivityVo>> queryTodayActivities(ClubActivityQueryDto queryDto) {
		Page<ClubActivityQueryDto> page = new Page<ClubActivityQueryDto>(1, 10);
		queryDto.setStartDate(DateUtils.formatDate());
		queryDto.setEndDate(DateUtils.formatDate());
		IPage<ClubActivityVo> activityVos = clubActivityService.queryPageList(page, queryDto);
		return new Result<IPage<ClubActivityVo>>().result(activityVos);
	}
	

	@ApiOperation(value="社团活动信息-社团活动列表分页查询", notes="E14 社团活动列表查询（universityId 查询校园活动，clubId 查询俱乐部）")
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
}
