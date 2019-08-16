package com.campgem.modules.trade.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.campgem.common.api.vo.Result;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.system.base.controller.JeecgController;
import com.campgem.modules.message.entity.SysMessage;
import com.campgem.modules.message.service.ISysMessageService;
import com.campgem.modules.trade.service.IGoodsReviewsService;
import com.campgem.modules.trade.service.IGoodsReviewsShieldsService;
import com.campgem.modules.trade.vo.GoodsReviewsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description: 留言板块
 * @Author: ling
 * @Date: 2019-08-17
 * @Version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(tags = "交易信息管理接口")
public class ReviewController extends JeecgController<SysMessage, ISysMessageService> {
	@Resource
	private IGoodsReviewsService goodsReviewsService;
	@Resource
	private IGoodsReviewsShieldsService goodsReviewsShieldsService;
	
	/**
	 * 商品留言分页
	 */
	@ApiOperation(value = "商品留言分页查询接口", notes = "C11 商品详情")
	@GetMapping(value = "/goods/{goodsId}/review")
	@ApiImplicitParam(name = "goodsId", value = "商品ID", required = true, paramType = "path")
	public Result<IPage<GoodsReviewsVo>> queryGoodsReviewsPageList(@PathVariable String goodsId,
	                                                               @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
	                                                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		if (StringUtils.isEmpty(goodsId)) {
			throw new JeecgBootException("商品ID不能为空");
		}
		IPage<GoodsReviewsVo> pageList = goodsReviewsService.queryGoodsReviewsPageList(goodsId, pageNo, pageSize);
		Result<IPage<GoodsReviewsVo>> result = new Result<>();
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	@ApiOperation(value = "商品留言屏蔽接口", notes = "C12 商品详情")
	@PostMapping(value = "/review/shield")
	@ApiImplicitParam(name = "targetId", value = "屏蔽的用户ID", required = true, paramType = "form")
	public Result addUserReviewShield(String targetId) {
		if (StringUtils.isEmpty(targetId)) {
			throw new JeecgBootException("用户ID不能为空");
		}
		Boolean result = goodsReviewsShieldsService.addUserReviewShield(targetId);
		return result ? Result.succ : Result.fail;
	}
}
