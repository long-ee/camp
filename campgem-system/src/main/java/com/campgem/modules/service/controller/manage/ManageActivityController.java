package com.campgem.modules.service.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.modules.service.dto.manage.MServiceQueryDto;
import com.campgem.modules.service.entity.Service;
import com.campgem.modules.service.service.IBusinessActivityService;
import com.campgem.modules.service.vo.manage.MBusinessActivityDetailVo;
import com.campgem.modules.service.vo.manage.MBusinessActivityListVo;
import com.campgem.modules.service.vo.manage.MBusinessActivityVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;

/**
 * @Description: 活动
 * @Author: ling
 * @Date: 2019-08-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "活动管理")
@RestController
@RequestMapping("/api/manage/v1/")
public class ManageActivityController {
	@Resource
	private IBusinessActivityService businessActivityService;
	
	@ApiOperation(value = "活动-分页列表查询", notes = "F2")
	@GetMapping(value = "/activity/list")
	public Result<IPage<MBusinessActivityListVo>> queryPageList(MServiceQueryDto queryDto,
	                                                            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
	                                                            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		Result<IPage<MBusinessActivityListVo>> result = new Result<>();
		
		Page<MServiceQueryDto> page = new Page<>(pageNo, pageSize);
		IPage<MBusinessActivityListVo> pageList = businessActivityService.queryManagePageList(page, queryDto);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	 * 添加
	 */
	@ApiOperation(value = "活动-添加", notes = "活动-添加")
	@PostMapping(value = "/activity/add")
	public Result add(@Valid @RequestBody MBusinessActivityVo activity) {
		boolean ok = businessActivityService.saveOrUpdate(activity, false);
		return ok ? Result.succ : Result.fail;
	}
	
	/**
	 * 编辑
	 */
	@ApiOperation(value = "活动-编辑", notes = "活动-编辑")
	@PutMapping(value = "/activity/edit")
	public Result edit(@Valid @RequestBody MBusinessActivityVo activity) {
		boolean ok = businessActivityService.saveOrUpdate(activity, true);
		return ok ? Result.succ : Result.fail;
	}
	
	/**
	 * 通过id删除
	 */
	@ApiOperation(value = "活动-通过id删除", notes = "活动-通过id删除")
	@DeleteMapping(value = "/activity/delete")
	public Result<?> delete(@RequestParam(name = "id") String id) {
		try {
			businessActivityService.removeById(id);
		} catch (Exception e) {
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 */
	@ApiOperation(value = "活动-批量删除", notes = "活动-批量删除")
	@DeleteMapping(value = "/activity/deleteBatch")
	public Result<Service> deleteBatch(@RequestParam(name = "ids") String ids) {
		Result<Service> result = new Result<>();
		if (ids == null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		} else {
			businessActivityService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	 * 通过id查询
	 */
	@ApiOperation(value = "活动-通过id查询", notes = "活动-通过id查询")
	@GetMapping(value = "/activity/queryById")
	public Result<MBusinessActivityDetailVo> queryById(@RequestParam(name = "id") String id) {
		Result<MBusinessActivityDetailVo> result = new Result<>();
		MBusinessActivityDetailVo detail = businessActivityService.queryManageBusinessActivityDetail(id);
		if (detail == null) {
			result.error500("未找到对应实体");
		} else {
			result.setResult(detail);
			result.setSuccess(true);
		}
		return new Result<MBusinessActivityDetailVo>().result(detail);
	}
}
