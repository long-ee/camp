package com.campgem.modules.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.service.service.IServiceService;
import com.campgem.modules.trade.service.IRequirementsService;
import com.campgem.modules.trade.vo.manage.MRequirementsDetailVo;
import com.campgem.modules.trade.vo.manage.MRequirementsVo;
import com.campgem.modules.user.vo.UserRequirementListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Api(tags = "用户需求管理接口")
@Slf4j
@RequestMapping("/api/v1")
public class UserRequirementController {
	
	@Resource
	private IServiceService serviceService;
	@Resource
	private IRequirementsService requirementsService;
	
	@ApiOperation(value = "用户需求列表", notes = "G14")
	@GetMapping("/user/requirements/list")
	public Result<IPage<UserRequirementListVo>> userGoodsPageList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
	                                                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		Page page = new Page(pageNo, pageSize);
		IPage<UserRequirementListVo> pageList = requirementsService.queryPageList(page);
		return new Result<IPage<UserRequirementListVo>>().result(pageList);
	}
	
	@ApiOperation(value = "需求详情", notes = "G14")
	@GetMapping("user/requirements/{requirementId}/detail")
	@ApiImplicitParam(name = "serviceId", value = "需求ID", paramType = "path")
	public Result<MRequirementsDetailVo> userServiceDetail(@PathVariable String requirementId) {
		MRequirementsDetailVo detail = requirementsService.queryManageRequirementDetail(requirementId);
		return new Result<MRequirementsDetailVo>().result(detail);
	}
	
	@ApiOperation(value = "需求状态修改", notes = "G14")
	@GetMapping("user/requirements/{requirementId}/status")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "serviceId", value = "需求ID", paramType = "path"),
			@ApiImplicitParam(name = "status", value = "状态，不区分大小写", allowableValues = "ENABLE, DISABLE", required = true)
	})
	public Result userServiceStatus(@PathVariable String requirementId, String status) {
		boolean ok = requirementsService.updateStatusById(requirementId, status);
		return ok ? Result.succ : Result.fail;
	}
	
	@ApiOperation(value = "添加需求", notes = "G141")
	@PostMapping("user/requirements/add")
	public Result addUserService(@Valid @RequestBody MRequirementsVo requirementsVo) {
		requirementsVo.setUid(SecurityUtils.getCurrentUserUid());
		boolean ok = requirementsService.save(requirementsVo);
		return ok ? Result.succ : Result.fail;
	}
	
	@ApiOperation(value = "修改需求", notes = "G141")
	@PutMapping("user/requirements/edit")
	public Result editUserService(@Valid @RequestBody MRequirementsVo requirementsVo) {
		requirementsVo.setUid(SecurityUtils.getCurrentUserUid());
		boolean ok = requirementsService.update(requirementsVo);
		return ok ? Result.succ : Result.fail;
	}
}
