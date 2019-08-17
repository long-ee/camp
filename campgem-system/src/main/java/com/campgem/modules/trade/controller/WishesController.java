package com.campgem.modules.trade.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.campgem.common.api.vo.Result;
import com.campgem.modules.trade.service.IWishesService;
import com.campgem.modules.trade.vo.WishesVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description: 心愿
 * @Author: ling
 * @Date: 2019-08-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "心愿单")
@RestController
@RequestMapping("/api/v1")
public class WishesController {
	@Resource
	private IWishesService wishesService;
	
	@ApiOperation(value = "心愿单-分页列表查询", notes = "C19 心愿单")
	@GetMapping(value = "/wishes")
	public Result<IPage<WishesVo>> queryWishesPageList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
	                                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		IPage<WishesVo> page = wishesService.queryWishesPageList(pageNo, pageSize);
		return new Result<IPage<WishesVo>>().result(page);
	}
	
	@ApiOperation(value = "删除心愿单", notes = "C19 心愿单")
	@DeleteMapping(value = "/wishes")
	@ApiImplicitParam(name = "wishId", value = "心愿单ID", required = true, paramType = "form")
	public Result deleteWishes(String wishId) {
		boolean ok = wishesService.removeById(wishId);
		return ok ? Result.succ : Result.fail;
	}
	
}
