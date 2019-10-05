package com.campgem.modules.service.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.modules.service.dto.manage.MServiceQueryDto;
import com.campgem.modules.service.entity.Service;
import com.campgem.modules.service.service.IServiceService;
import com.campgem.modules.service.vo.manage.MServiceDetailVo;
import com.campgem.modules.service.vo.manage.MServiceListVo;
import com.campgem.modules.service.vo.manage.MServiceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;

/**
 * @Description: 服务
 * @Author: campgem
 * @Date: 2019-08-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "【管理端】服务信息管理接口")
@RestController
@RequestMapping("/api/manage/v1/")
public class ManageServiceController {
	@Resource
	private IServiceService serviceService;
	
	@ApiOperation(value = "服务-分页列表查询", notes = "服务-分页列表查询")
	@GetMapping(value = "/service/list")
	public Result<IPage<MServiceListVo>> queryPageList(MServiceQueryDto queryDto,
	                                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
	                                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		Result<IPage<MServiceListVo>> result = new Result<>();
		
		Page<MServiceQueryDto> page = new Page<>(pageNo, pageSize);
		IPage<MServiceListVo> pageList = serviceService.queryManagePageList(page, queryDto);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	@ApiOperation("修改服务状态")
	@PutMapping("/service/status")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "需求ID", required = true),
			@ApiImplicitParam(name = "status", value = "状态，不区分大小写", allowableValues = "ENABLE, DISABLE", required = true)
	})
	public Result updateServiceStatus(String id, String status) {
		boolean ok = serviceService.updateStatusById(id, status);
		return ok ? Result.succ : Result.fail;
	}
	
	/**
	 * 添加
	 */
	@ApiOperation(value = "服务-添加", notes = "服务-添加")
	@PostMapping(value = "/service/add")
	public Result add(@Valid @RequestBody MServiceVo service) {
		boolean ok = serviceService.saveOrUpdate(service, false);
		return ok ? Result.succ : Result.fail;
	}
	
	/**
	 * 编辑
	 */
	@ApiOperation(value = "服务-编辑", notes = "服务-编辑")
	@PutMapping(value = "/service/edit")
	public Result edit(@Valid @RequestBody MServiceVo service) {
		boolean ok = serviceService.saveOrUpdate(service, true);
		return ok ? Result.succ : Result.fail;
	}
	
	/**
	 * 通过id删除
	 */
	@ApiOperation(value = "服务-通过id删除", notes = "服务-通过id删除")
	@DeleteMapping(value = "/service/delete")
	public Result<?> delete(@RequestParam(name = "id") String id) {
		try {
			serviceService.removeById(id);
		} catch (Exception e) {
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 */
	@ApiOperation(value = "服务-批量删除", notes = "服务-批量删除")
	@DeleteMapping(value = "/service/deleteBatch")
	public Result<Service> deleteBatch(@RequestParam(name = "ids") String ids) {
		Result<Service> result = new Result<>();
		if (ids == null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		} else {
			this.serviceService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	 * 通过id查询
	 */
	@ApiOperation(value = "服务-通过id查询", notes = "服务-通过id查询")
	@GetMapping(value = "/service/queryById")
	public Result<MServiceDetailVo> queryById(@RequestParam(name = "id") String id) {
		Result<MServiceDetailVo> result = new Result<>();
		MServiceDetailVo service = serviceService.queryManageServiceDetail(id);
		if (service == null) {
			result.error500("未找到对应实体");
		} else {
			result.setResult(service);
			result.setSuccess(true);
		}
		return result;
	}
}
