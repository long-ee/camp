package com.campgem.modules.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.modules.trade.service.IGoodsService;
import com.campgem.modules.user.vo.UserGoodsDetailVo;
import com.campgem.modules.user.vo.UserGoodsListVo;
import com.campgem.modules.user.vo.UserGoodsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Api(tags = "用户商品管理接口")
@Slf4j
@RequestMapping("/api/v1")
public class UserGoodsController {
	
	@Resource
	private IGoodsService goodsService;
	
	@ApiOperation(value = "用户商品列表", notes = "G12")
	@GetMapping("/user/goods/list")
	public Result<IPage<UserGoodsListVo>> userGoodsPageList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
	                                                        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		Page page = new Page(pageNo, pageSize);
		IPage<UserGoodsListVo> pageList = goodsService.queryPageList(page);
		return new Result<IPage<UserGoodsListVo>>().result(pageList);
	}
	
	@ApiOperation(value = "商品详情", notes = "G10")
	@GetMapping("user/goods/{goodsId}/detail")
	@ApiImplicitParam(name = "goodsId", value = "商品ID", paramType = "path")
	public Result<UserGoodsDetailVo> userGoodsDetail(@PathVariable String goodsId) {
		UserGoodsDetailVo goodsVo = goodsService.queryUserGoodsDetail(goodsId);
		return new Result<UserGoodsDetailVo>().result(goodsVo);
	}
	
	@ApiOperation(value = "商品状态修改", notes = "G14")
	@GetMapping("user/goods/{goodsId}/status")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodsId", value = "商品ID", paramType = "path"),
			@ApiImplicitParam(name = "status", value = "状态，不区分大小写", allowableValues = "ENABLE, DISABLE", required = true)
	})
	public Result userServiceStatus(@PathVariable String goodsId, String status) {
		boolean ok = goodsService.updateStatusById(goodsId, status);
		return ok ? Result.succ : Result.fail;
	}
	
	@ApiOperation(value = "添加商品", notes = "G121 G122")
	@PostMapping("user/goods/add")
	public Result addUserGoods(@Valid @RequestBody UserGoodsVo userGoodsVo) {
		boolean ok = goodsService.addOrUpdateUserGoods(userGoodsVo, false);
		return ok ? Result.succ : Result.fail;
	}
	
	@ApiOperation(value = "修改商品", notes = "G121 G122")
	@PutMapping("user/goods/edit")
	public Result editUserGoods(@Valid @RequestBody UserGoodsVo userGoodsVo) {
		boolean ok = goodsService.addOrUpdateUserGoods(userGoodsVo, true);
		return ok ? Result.succ : Result.fail;
	}
}
