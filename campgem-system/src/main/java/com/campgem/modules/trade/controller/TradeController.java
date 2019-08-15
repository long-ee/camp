package com.campgem.modules.trade.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.campgem.common.api.vo.Result;
import com.campgem.common.system.base.controller.JeecgController;
import com.campgem.modules.common.entity.enums.AdvertisementLocationEnum;
import com.campgem.modules.common.service.IAdvertisementService;
import com.campgem.modules.common.vo.AdvertisementVo;
import com.campgem.modules.message.entity.SysMessage;
import com.campgem.modules.message.service.ISysMessageService;
import com.campgem.modules.trade.dto.GoodsQueryDto;
import com.campgem.modules.trade.service.IGoodsService;
import com.campgem.modules.trade.vo.GoodsListVo;
import com.campgem.modules.university.entity.enums.CategoryTypeEnum;
import com.campgem.modules.university.service.ICategoryService;
import com.campgem.modules.university.vo.CategoryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 交易板块
 * @Author: ling
 * @Date: 2019-08-15
 * @Version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/trade")
@Api(tags = "交易信息管理接口")
public class TradeController extends JeecgController<SysMessage, ISysMessageService> {
	@Resource
	private ICategoryService categoryService;
	@Resource
	private IGoodsService goodsService;
	@Resource
	private IAdvertisementService advertisementService;
	
	/**
	 * 交易分类列表
	 */
	@ApiOperation(value = "交易分类查询")
	@GetMapping(value = "/category")
	public Result<List<CategoryVo>> queryCategoryList() {
		Result<List<CategoryVo>> result = new Result<>();
		List<CategoryVo> data = categoryService.queryByType(CategoryTypeEnum.PRODUCT.code());
		result.setResult(data);
		return result;
	}
	
	/**
	 * 商品分页查询
	 */
	@ApiOperation(value = "商品分页查询")
	@GetMapping(value = "/goods")
	public Result<IPage<GoodsListVo>> queryGoodsPageList(GoodsQueryDto queryDto,
	                                                     @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
	                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		Result<IPage<GoodsListVo>> result = new Result<>();
		IPage<GoodsListVo> pageList = goodsService.queryPageList(pageNo, pageSize, queryDto);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	 * 广告
	 */
	@ApiOperation(value = "交易平台广告列表")
	@GetMapping(value = "/advertisement")
	public Result<List<AdvertisementVo>> queryAdvertisementList() {
		List<AdvertisementVo> list = advertisementService.getAdvertisementByLocation(AdvertisementLocationEnum.TRADING_CENTER_HOMEPAGE.code());
		Result<List<AdvertisementVo>> result = new Result<>();
		result.setResult(list);
		return result;
	}
}
