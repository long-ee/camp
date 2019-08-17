package com.campgem.modules.trade.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.campgem.common.api.vo.Result;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.modules.trade.service.IGoodsEvaluationService;
import com.campgem.modules.trade.vo.GoodsEvaluationVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description: 商品评价
 * @Author: campgem
 * @Date: 2019-08-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "商品评价")
@RestController
@RequestMapping("/api/v1")
public class GoodsEvaluationController {
	@Resource
	private IGoodsEvaluationService goodsEvaluationService;
	
	/**
	 * 分页列表查询
	 */
	@ApiOperation(value = "商品评价分页列表查询", notes = "C13 商品评价")
	@GetMapping(value = "/goods/{goodsId}/evaluation")
	@ApiImplicitParam(name = "goodsId", value = "商品ID", paramType = "path")
	public Result<IPage<GoodsEvaluationVo>> queryGoodsEvaluationPageList(@PathVariable String goodsId,
	                                                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
	                                                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		if (StringUtils.isEmpty(goodsId)) {
			throw new JeecgBootException("商品ID不能为空");
		}
		
		IPage<GoodsEvaluationVo> list = goodsEvaluationService.queryGoodsEvaluationPageList(goodsId, pageNo, pageSize);
		
		return new Result<IPage<GoodsEvaluationVo>>().result(list);
	}
}
