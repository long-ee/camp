package com.campgem.modules.trade.controller;

import com.campgem.common.api.vo.Result;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.modules.trade.dto.OrderInfoDto;
import com.campgem.modules.trade.service.ICartService;
import com.campgem.modules.trade.service.IGoodsService;
import com.campgem.modules.trade.vo.GoodsOrderInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description: 订单
 * @Author: ling
 * @Date: 2019-08-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "订单信息管理接口")
@RestController
@RequestMapping("/api/v1")
public class OrderController {
	@Resource
	private ICartService cartService;
	@Resource
	private IGoodsService goodsService;

	@ApiOperation("订单确认")
	@PostMapping("/order/info")
	public Result<Map<String, List<GoodsOrderInfoVo>>> queryOrderInfo(OrderInfoDto orderInfoDto) {
		if (StringUtils.isEmpty(orderInfoDto.getGoodsId()) &&
				(orderInfoDto.getCartIds() == null || orderInfoDto.getCartIds().length == 0)) {
			throw new JeecgBootException("商品ID或者购物车ID列表不能同时为空");
		}
		
		Map<String, List<GoodsOrderInfoVo>> data;
		if (orderInfoDto.getCartIds() != null) {
			data = cartService.queryOrderInfo(orderInfoDto.getCartIds());
		} else {
			// 直接购买
			data = goodsService.queryOrderInfo(orderInfoDto);
		}
		
		return new Result<Map<String, List<GoodsOrderInfoVo>>>().result(data);
	}
}
