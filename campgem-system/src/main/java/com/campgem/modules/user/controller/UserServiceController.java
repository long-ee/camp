package com.campgem.modules.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.service.service.IServiceService;
import com.campgem.modules.service.vo.manage.MServiceDetailVo;
import com.campgem.modules.service.vo.manage.MServiceVo;
import com.campgem.modules.user.vo.UserServiceListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Api(tags = "用户服务管理接口")
@Slf4j
@RequestMapping("/api/v1")
public class UserServiceController {
	
	@Resource
	private IServiceService serviceService;
	
	@ApiOperation(value = "用户服务列表", notes = "G13")
	@GetMapping("/user/service/list")
	public Result<IPage<UserServiceListVo>> userGoodsPageList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
	                                                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		Page page = new Page(pageNo, pageSize);
		IPage<UserServiceListVo> pageList = serviceService.queryPageList(page);
		return new Result<IPage<UserServiceListVo>>().result(pageList);
	}
	
	@ApiOperation(value = "服务详情", notes = "G13")
	@GetMapping("user/service/{serviceId}/detail")
	@ApiImplicitParam(name = "serviceId", value = "服务ID", paramType = "path")
	public Result<MServiceDetailVo> userServiceDetail(@PathVariable String serviceId) {
		MServiceDetailVo service = serviceService.queryManageServiceDetail(serviceId);
		return new Result<MServiceDetailVo>().result(service);
	}
	
	@ApiOperation(value = "需求状态修改", notes = "G14")
	@GetMapping("user/service/{serviceId}/status")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "serviceId", value = "需求ID", paramType = "path"),
			@ApiImplicitParam(name = "status", value = "状态，不区分大小写", allowableValues = "ENABLE, DISABLE", required = true)
	})
	public Result userServiceStatus(@PathVariable String serviceId, String status) {
		boolean ok = serviceService.updateStatusById(serviceId, status);
		return ok ? Result.succ : Result.fail;
	}
	
	@ApiOperation(value = "添加服务", notes = "G131")
	@PostMapping("user/service/add")
	public Result addUserService(@Valid @RequestBody MServiceVo serviceVo) {
		serviceVo.setUid(SecurityUtils.getCurrentUserUid());
		boolean ok = serviceService.saveOrUpdate(serviceVo, false);
		return ok ? Result.succ : Result.fail;
	}
	
	@ApiOperation(value = "修改服务", notes = "G131")
	@PutMapping("user/service/edit")
	public Result editUserService(@Valid @RequestBody MServiceVo serviceVo) {
		serviceVo.setUid(SecurityUtils.getCurrentUserUid());
		boolean ok = serviceService.saveOrUpdate(serviceVo, true);
		return ok ? Result.succ : Result.fail;
	}
}
