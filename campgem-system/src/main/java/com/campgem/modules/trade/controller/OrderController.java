package com.campgem.modules.trade.controller;

import com.campgem.common.api.vo.Result;
import com.campgem.common.constant.CommonConstant;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.modules.trade.dto.OrderInfoDto;
import com.campgem.modules.trade.dto.OrderPayDto;
import com.campgem.modules.trade.service.ICartService;
import com.campgem.modules.trade.service.IGoodsService;
import com.campgem.modules.trade.service.IOrderService;
import com.campgem.modules.trade.vo.OrderInfoVo;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
	@Resource
	private IOrderService orderService;

	@ApiOperation("订单确认")
	@PostMapping("/order/info")
	public Result<List<OrderInfoVo>> queryOrderInfo(OrderInfoDto orderInfoDto) {
		if (StringUtils.isEmpty(orderInfoDto.getGoodsId()) &&
				(orderInfoDto.getCartIds() == null || orderInfoDto.getCartIds().length == 0)) {
			throw new JeecgBootException("商品ID或者购物车ID列表不能同时为空");
		}
		
		List<OrderInfoVo> data;
		if (orderInfoDto.getCartIds() != null) {
			data = cartService.queryOrderInfo(orderInfoDto.getCartIds());
		} else {
			// 直接购买
			 data = goodsService.queryOrderInfo(orderInfoDto);
		}
		
		return new Result<List<OrderInfoVo>>().result(data);
	}
	
	@ApiOperation("订单支付，返回URL，需要跳转到这个地址")
	@PostMapping("/order/pay")
	public Result<String> pay(@RequestBody OrderPayDto payDto) {
		if (!CommonConstant.payments.keySet().contains(payDto.getPaymentMethod())) {
			throw new JeecgBootException("支付方式错误");
		}
		
		// 添加订单
		Payment payment = orderService.createPaymentByOrders(payDto);
		String url = null;
		for(Links links : payment.getLinks()){
			if(links.getRel().equals("approval_url")){
				url = links.getHref();
				break;
			}
		}
		
		if (url == null) {
			throw new JeecgBootException("订单支付失败");
		}
		
		return new Result<String>().result(url);
	}
}
