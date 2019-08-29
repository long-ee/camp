package com.campgem.modules.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.LoginUserVo;
import com.campgem.common.api.vo.Result;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.service.service.IBusinessActivityService;
import com.campgem.modules.service.service.IServiceService;
import com.campgem.modules.service.vo.manage.MBusinessActivityVo;
import com.campgem.modules.service.vo.manage.MServiceDetailVo;
import com.campgem.modules.user.entity.enums.MemberTypeEnum;
import com.campgem.modules.user.vo.UserBusinessActivityListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Api(tags = "用户活动管理接口")
@Slf4j
@RequestMapping("/api/v1")
public class UserBusinessActivityController {
	
	@Resource
	private IServiceService serviceService;
	@Resource
	private IBusinessActivityService businessActivityService;
	
	@ApiOperation(value = "用户服务列表", notes = "G17")
	@GetMapping("/user/business/activity/list")
	public Result<IPage<UserBusinessActivityListVo>> userBusinessActivityPageList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
	                                                                              @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		Page page = new Page(pageNo, pageSize);
		IPage<UserBusinessActivityListVo> pageList = businessActivityService.queryPageList(page);
		return new Result<IPage<UserBusinessActivityListVo>>().result(pageList);
	}
	
	@ApiOperation(value = "活动详情", notes = "G17")
	@GetMapping("user/business/activity/{activityId}/detail")
	@ApiImplicitParam(name = "activityId", value = "活动ID", paramType = "path")
	public Result<MServiceDetailVo> userBusinessActivityDetail(@PathVariable String activityId) {
		MServiceDetailVo service = serviceService.queryManageServiceDetail(activityId);
		return new Result<MServiceDetailVo>().result(service);
	}
	
	@ApiOperation(value = "删除活动", notes = "G17")
	@DeleteMapping("user/business/activity/{activityId}/delete")
	@ApiImplicitParam(name = "activityId", value = "活动ID", paramType = "path")
	public Result deleteUserBusinessActivity(@PathVariable String activityId) {
		boolean ok = businessActivityService.removeById(activityId);
		return ok ? Result.succ : Result.fail;
	}
	
	@ApiOperation(value = "添加活动", notes = "G171")
	@PostMapping("user/business/activity/add")
	public Result addUserBusinessActivity(@Valid @RequestBody MBusinessActivityVo activityVo) {
		activityVo.setUid(checkMemberType());
		boolean ok = businessActivityService.saveOrUpdate(activityVo, false);
		return ok ? Result.succ : Result.fail;
	}
	
	@ApiOperation(value = "修改活动", notes = "G171")
	@PutMapping("user/business/activity/edit")
	public Result editUserBusinessActivity(@Valid @RequestBody MBusinessActivityVo activityVo) {
		activityVo.setUid(checkMemberType());
		boolean ok = businessActivityService.saveOrUpdate(activityVo, true);
		return ok ? Result.succ : Result.fail;
	}
	
	private String checkMemberType() {
		LoginUserVo user = SecurityUtils.getCurrentUser();
		if (!user.getMemberType().equals(MemberTypeEnum.LOCAL_BUSINESS.code())) {
			throw new JeecgBootException(StatusEnum.ActivityNeedLocationBusiness);
		}
		
		return user.getUid();
	}
}
