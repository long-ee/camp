package com.campgem.modules.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.modules.service.service.IServiceEvaluationService;
import com.campgem.modules.trade.service.IGoodsEvaluationService;
import com.campgem.modules.trade.service.IOrderService;
import com.campgem.modules.user.dto.OrdersEvaluationDto;
import com.campgem.modules.user.vo.OrdersDetailVo;
import com.campgem.modules.user.vo.OrdersListVo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "用户订单管理接口")
@Slf4j
@RequestMapping("/api/v1")
public class UserOrdersController {
	
	private static List<String> ordersStatus = new ArrayList<>();
	
	static {
		ordersStatus.add("all");
		ordersStatus.add("unpaid");
		ordersStatus.add("progress");
		ordersStatus.add("finished");
	}
	
	@Resource
	private IOrderService orderService;
	
	@Resource
	private IServiceEvaluationService serviceEvaluationService;
	@Resource
	private IGoodsEvaluationService goodsEvaluationService;
	
	@ApiOperation(value = "用户订单列表", notes = "G1")
	@GetMapping("user/orders")
	@ApiImplicitParam(name = "status", value = "类型，'all', 'unpaid', 'progress', 'finished'",
			allowableValues = "all, unpaid, progress, finished", paramType = "query")
	public Result<IPage<OrdersListVo>> queryUserOrders(@RequestParam(name = "status", defaultValue = "all") String status,
	                                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
	                                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		if (!ordersStatus.contains(status)) {
			throw new JeecgBootException(StatusEnum.OrdersStatusError);
		}
		
		Page page = new Page(pageNo, pageSize);
		IPage<OrdersListVo> pageList = orderService.queryUserOrders(status, page);
		return new Result<IPage<OrdersListVo>>().result(pageList);
	}
	
	@ApiOperation(value = "订单详情", notes = "G10")
	@GetMapping("user/orders/{orderId}/detail")
	@ApiImplicitParam(name = "orderId", value = "订单ID", paramType = "path")
	public Result<OrdersDetailVo> queryUserOrdersDetail(@PathVariable String orderId) {
		OrdersDetailVo detail = orderService.queryUserOrdersDetail(orderId);
		return new Result<OrdersDetailVo>().result(detail);
	}
	
	@ApiModelProperty(value = "订单评价", notes = "G101")
	@PostMapping("user/orders/{orderId}/evaluation")
	@ApiImplicitParam(name = "orderId", value = "订单ID", paramType = "path")
	public Result userOrdersEvaluation(@Valid @RequestBody OrdersEvaluationDto dto, @PathVariable String orderId) {
		boolean ok = orderService.evaluate(orderId, dto);
		return ok ? Result.succ : Result.fail;
	}
	
	@ApiOperation(value = "订单完成", notes = "")
	@PostMapping("user/orders/{orderId}/finished")
	@ApiImplicitParam(name = "orderId", value = "订单ID", paramType = "path")
	public Result userOrdersFinished(@PathVariable String orderId) {
		boolean ok = orderService.finished(orderId);
		return ok ? Result.succ : Result.fail;
	}
	
	@ApiOperation(value = "订单完成", notes = "")
	@PostMapping("user/orders/{orderId}/trackingNumber")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderId", value = "订单ID", paramType = "path"),
			@ApiImplicitParam(name = "trackingNumber", value = "trackingNumber", paramType = "query")
	})
	public Result userOrdersTrackingNumber(@PathVariable String orderId, String trackingNumber) {
		if (trackingNumber == null) {
			throw new JeecgBootException(StatusEnum.TrackingNumberEmptyError);
		}
		
		boolean ok = orderService.updateTrackingNumber(orderId, trackingNumber);
		return ok ? Result.succ : Result.fail;
	}
}
