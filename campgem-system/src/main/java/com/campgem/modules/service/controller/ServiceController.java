package com.campgem.modules.service.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.modules.common.entity.enums.AdvertisementLocationEnum;
import com.campgem.modules.common.entity.enums.CategoryTypeEnum;
import com.campgem.modules.common.service.IAdvertisementService;
import com.campgem.modules.common.service.ICategoryService;
import com.campgem.modules.common.vo.AdvertisementVo;
import com.campgem.modules.common.vo.CategoryVo;
import com.campgem.modules.service.service.IServiceEvaluationService;
import com.campgem.modules.service.service.IServiceService;
import com.campgem.modules.service.vo.*;
import com.campgem.modules.user.service.IMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 服务板块
 * @Author: ling
 * @Date: 2019-08-20
 * @Version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(tags = "服务信息管理接口")
public class ServiceController {
	@Resource
	private ICategoryService categoryService;
	@Resource
	private IAdvertisementService advertisementService;
	@Resource
	private IServiceService serviceService;
	@Resource
	private IServiceEvaluationService serviceEvaluationService;
	@Resource
	private IMemberService memberService;
	
	@ApiOperation(value = "服务分类查询", notes = "D1 生活圈")
	@GetMapping(value = "/service/category")
	public Result<List<CategoryVo>> queryCategoryList() {
		Result<List<CategoryVo>> result = new Result<>();
		List<CategoryVo> data = categoryService.queryByType(CategoryTypeEnum.SERVICE.code());
		result.setResult(data);
		return result;
	}
	
	@ApiOperation(value = "按照分类查询服务列表，各个分类最多8个", notes = "D1 生活圈")
	@GetMapping(value = "/service/category/list")
	public Result<List<ServiceVo>> queryServiceListByCategory(String categoryId) {
		List<ServiceVo> list = serviceService.queryServiceListByCategory(categoryId);
		
		return new Result<List<ServiceVo>>().result(list);
	}
	
	@ApiOperation(value = "广告列表", notes = "D1 右侧广告")
	@GetMapping(value = "/service/advertisement")
	public Result<List<AdvertisementVo>> queryServiceAdvertisement() {
		List<AdvertisementVo> list = advertisementService.getAdvertisementByLocation(AdvertisementLocationEnum.SOCIAL_HOMEPAGE.code());
		Result<List<AdvertisementVo>> result = new Result<>();
		result.setResult(list);
		return result;
	}
	
	@ApiOperation(value = "服务列表广告", notes = "D11 右侧广告")
	@GetMapping(value = "/service/list/advertisement")
	public Result<List<AdvertisementVo>> queryServiceListAdvertisement() {
		List<AdvertisementVo> list = advertisementService.getAdvertisementByLocation(AdvertisementLocationEnum.SERVICE_LIST.code());
		Result<List<AdvertisementVo>> result = new Result<>();
		result.setResult(list);
		return result;
	}
	
	@ApiOperation(value = "服务分页", notes = "D11 服务列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "categoryId", value = "分类ID，默认all", defaultValue = "all", paramType = "query"),
			@ApiImplicitParam(name = "sort", value = "排序，默认0，0default，1popular，2low to high，3high to low", defaultValue = "0", paramType = "query")
	})
	@GetMapping("/service")
	public Result<IPage<ServiceVo>> queryServicePageList(@RequestParam(defaultValue = "all") String categoryId,
	                                                     @RequestParam(defaultValue = "0") Integer sort,
	                                                     @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
	                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		Result<IPage<ServiceVo>> result = new Result<>();
		Page<String> page = new Page<>(pageNo, pageSize);
		IPage<ServiceVo> pageList = serviceService.queryServicePageList(page, categoryId, sort);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	@ApiOperation(value = "服务详情", notes = "D12")
	@GetMapping(value = "/service/{serviceId}/detail")
	@ApiImplicitParam(name = "serviceId", value = "服务ID", required = true, paramType = "path")
	public Result<ServiceDetailVo> queryServiceDetail(@PathVariable String serviceId) {
		ServiceDetailVo detail = serviceService.queryServiceDetail(serviceId);
		return new Result<ServiceDetailVo>().result(detail);
	}
	
	@ApiOperation(value = "服务评价分页查询", notes = "D12")
	@GetMapping(value = "/service/{serviceId}/evaluation")
	@ApiImplicitParam(name = "serviceId", value = "服务ID", required = true, paramType = "path")
	public Result<IPage<ServiceEvaluationVo>> queryServiceEvaluationPageList(@PathVariable String serviceId,
	                                                                         @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
	                                                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		IPage<ServiceEvaluationVo> list = serviceEvaluationService.queryServiceEvaluationPageList(serviceId, pageNo, pageSize);
		
		return new Result<IPage<ServiceEvaluationVo>>().result(list);
	}
	
	@ApiOperation(value = "商家主页", notes = "D14")
	@GetMapping(value = "/business/{businessId}/index")
	@ApiImplicitParam(name = "businessId", value = "商家ID，服务详情接口里面的uid", required = true, paramType = "path")
	public Result<BusinessDetailVo> queryBusinessDetail(@PathVariable String businessId) {
		BusinessDetailVo detail = memberService.queryBusinessDetail(businessId);
		return new Result<BusinessDetailVo>().result(detail);
	}
	
	
	@ApiOperation(value = "商家服务分页", notes = "D14 商家主页")
	@ApiImplicitParam(name = "businessId", value = "商家ID，服务详情接口里面的uid", required = true, paramType = "path")
	@GetMapping("/business/{businessId}/service")
	public Result<IPage<BusinessServiceVo>> queryBusinessServicePageList(@PathVariable String businessId,
	                                                                     @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
	                                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		Result<IPage<BusinessServiceVo>> result = new Result<>();
		IPage<BusinessServiceVo> pageList = serviceService.queryBusinessServicePageList(businessId, pageNo, pageSize);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	@ApiOperation(value = "下单", notes = "D16 确认下单")
	@GetMapping("/service/{serviceId}/order")
	@ApiImplicitParam(name = "serviceId", value = "服务ID", required = true, paramType = "path")
	public Result<ServiceOrderVo> queryServiceOrder(@PathVariable String serviceId) {
		ServiceOrderVo orderVo = serviceService.queryServiceOrder(serviceId);
		return new Result<ServiceOrderVo>().result(orderVo);
	}
}
