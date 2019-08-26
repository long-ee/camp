package com.campgem.modules.trade.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.system.base.controller.JeecgController;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.common.entity.enums.AdvertisementLocationEnum;
import com.campgem.modules.common.entity.enums.CategoryTypeEnum;
import com.campgem.modules.common.service.IAdvertisementService;
import com.campgem.modules.common.service.ICategoryService;
import com.campgem.modules.common.vo.AdvertisementVo;
import com.campgem.modules.common.vo.CategoryVo;
import com.campgem.modules.message.entity.SysMessage;
import com.campgem.modules.message.service.ISysMessageService;
import com.campgem.modules.trade.dto.GoodsQueryDto;
import com.campgem.modules.trade.dto.GoodsReviewDto;
import com.campgem.modules.trade.entity.Goods;
import com.campgem.modules.trade.entity.GoodsReviews;
import com.campgem.modules.trade.entity.GoodsReviewsShields;
import com.campgem.modules.trade.service.IGoodsEvaluationService;
import com.campgem.modules.trade.service.IGoodsReviewsService;
import com.campgem.modules.trade.service.IGoodsReviewsShieldsService;
import com.campgem.modules.trade.service.IGoodsService;
import com.campgem.modules.trade.vo.GoodsDetailVo;
import com.campgem.modules.trade.vo.GoodsEvaluationVo;
import com.campgem.modules.trade.vo.GoodsListVo;
import com.campgem.modules.trade.vo.GoodsReviewsVo;
import com.campgem.modules.user.service.IUserBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @Description: 交易板块
 * @Author: ling
 * @Date: 2019-08-15
 * @Version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(tags = "商品信息管理接口")
public class GoodsController extends JeecgController<SysMessage, ISysMessageService> {
	@Resource
	private ICategoryService categoryService;
	@Resource
	private IGoodsService goodsService;
	@Resource
	private IAdvertisementService advertisementService;
	@Resource
	private IGoodsEvaluationService goodsEvaluationService;
	@Resource
	private IGoodsReviewsService goodsReviewsService;
	@Resource
	private IGoodsReviewsShieldsService goodsReviewsShieldsService;
	
	@Resource
	private IUserBaseService userBaseService;
	
	/**
	 * 交易分类列表
	 */
	@ApiOperation(value = "交易分类查询", notes = "C1 分类")
	@GetMapping(value = "/goods/category")
	public Result<List<CategoryVo>> queryCategoryList() {
		Result<List<CategoryVo>> result = new Result<>();
		List<CategoryVo> data = categoryService.queryByType(CategoryTypeEnum.PRODUCT.code());
		result.setResult(data);
		return result;
	}
	
	/**
	 * 商品分页查询
	 */
	@ApiOperation(value = "商品分页查询", notes = "C1 商品列表")
	@GetMapping(value = "/goods")
	public Result<IPage<GoodsListVo>> queryGoodsPageList(GoodsQueryDto queryDto,
	                                                     @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
	                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		Result<IPage<GoodsListVo>> result = new Result<>();
		Page<GoodsQueryDto> page = new Page<>(pageNo, pageSize);
		IPage<GoodsListVo> pageList = goodsService.queryPageList(page, queryDto);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	 * 商品详情
	 */
	@ApiOperation(value = "商品详情接口", notes = "C11 商品详情")
	@GetMapping(value = "/goods/{goodsId}/detail")
	@ApiImplicitParam(name = "goodsId", value = "商品ID", required = true, paramType = "path")
	public Result<GoodsDetailVo> queryGoodsDetail(@PathVariable String goodsId) {
		if (StringUtils.isEmpty(goodsId)) {
			throw new JeecgBootException(StatusEnum.GoodsIdBlankError);
		}
		GoodsDetailVo detail = goodsService.queryGoodsDetail(goodsId);
		return new Result<GoodsDetailVo>().result(detail);
	}
	
	/**
	 * 广告
	 */
	@ApiOperation(value = "广告列表", notes = "C1 右侧广告")
	@GetMapping(value = "/goods/advertisement")
	public Result<List<AdvertisementVo>> queryAdvertisementList() {
		List<AdvertisementVo> list = advertisementService.getAdvertisementByLocation(AdvertisementLocationEnum.TRADING_CENTER_HOMEPAGE.code());
		Result<List<AdvertisementVo>> result = new Result<>();
		result.setResult(list);
		return result;
	}
	
	/**
	 * 分页列表查询
	 */
	@ApiOperation(value = "商品评价分页列表查询", notes = "C13 商品评价")
	@GetMapping(value = "/goods/{goodsId}/evaluation")
	@ApiImplicitParam(name = "goodsId", value = "商品ID", paramType = "path")
	public Result<IPage<GoodsEvaluationVo>> queryGoodsEvaluationPageList(@PathVariable String goodsId,
	                                                                     @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
	                                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		if (org.apache.commons.lang.StringUtils.isEmpty(goodsId)) {
			throw new JeecgBootException(StatusEnum.GoodsIdBlankError);
		}
		
		IPage<GoodsEvaluationVo> list = goodsEvaluationService.queryGoodsEvaluationPageList(goodsId, pageNo, pageSize);
		
		return new Result<IPage<GoodsEvaluationVo>>().result(list);
	}
	
	/**
	 * 商品留言分页
	 */
	@ApiOperation(value = "商品留言分页查询接口", notes = "C11 商品详情")
	@GetMapping(value = "/goods/{goodsId}/review")
	@ApiImplicitParam(name = "goodsId", value = "商品ID", required = true, paramType = "path")
	public Result<IPage<GoodsReviewsVo>> queryGoodsReviewsPageList(@PathVariable String goodsId,
	                                                               @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
	                                                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
	                                                               HttpServletRequest request) {
		if (StringUtils.isEmpty(goodsId)) {
			throw new JeecgBootException(StatusEnum.GoodsIdBlankError);
		}
		
		Page<String> page = new Page<>(pageNo, pageSize);
		IPage<GoodsReviewsVo> pageList = goodsReviewsService.queryGoodsReviewsPageList(page, goodsId);
		
		Result<IPage<GoodsReviewsVo>> result = new Result<>();
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	@ApiOperation(value = "商品留言屏蔽接口", notes = "C12 商品详情")
	@PostMapping(value = "/goods/{goodsId}/shield")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodsId", value = "商品ID", required = true, paramType = "path"),
			@ApiImplicitParam(name = "targetId", value = "屏蔽的用户ID", required = true, paramType = "form")
	})
	public Result addUserReviewShield(@PathVariable String goodsId, String targetId) {
		if (StringUtils.isEmpty(targetId)) {
			throw new JeecgBootException(StatusEnum.GoodsIdBlankError);
		}
		
		Goods goods = goodsService.getById(goodsId);
		if (goods == null) {
			throw new JeecgBootException(StatusEnum.GoodsNotExistError);
		}
		
		// 是否是发布者
		if (!goods.getUid().equals(SecurityUtils.getCurrentUserUid())) {
			throw new JeecgBootException(StatusEnum.GoodsShieldUsersError);
		}
		
		if (userBaseService.getById(targetId) == null) {
			throw new JeecgBootException(StatusEnum.ShieldUserNotExistError);
		}
		
		GoodsReviewsShields shields = new GoodsReviewsShields();
		shields.setGoodsId(goodsId);
		shields.setShieldUid(targetId);
		shields.setCreateTime(new Date());
		
		boolean result = goodsReviewsShieldsService.save(shields);
		return result ? Result.succ : Result.fail;
	}
	
	@ApiOperation(value = "新增商品留言接口", notes = "C12 商品详情")
	@PostMapping(value = "/goods/review")
	public Result addGoodsReview(@Valid GoodsReviewDto reviewDto) {
		GoodsReviews goodsReviews = new GoodsReviews();
		goodsReviews.setCreateTime(new Date());
		goodsReviews.setGoodsId(reviewDto.getGoodsId());
		goodsReviews.setUid(SecurityUtils.getCurrentUserUid());
		goodsReviews.setIsOpen(reviewDto.getIsOpen());
		goodsReviews.setContent(reviewDto.getContent());
		boolean ok = goodsReviewsService.save(goodsReviews);
		if (ok) {
			goodsService.increment(reviewDto.getGoodsId());
		}
		return ok ? Result.succ : Result.fail;
	}
}
