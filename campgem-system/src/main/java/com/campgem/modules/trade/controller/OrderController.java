package com.campgem.modules.trade.controller;

import com.campgem.common.api.vo.Result;
import com.campgem.common.constant.CommonConstant;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.modules.common.service.IPaymentService;
import com.campgem.modules.trade.dto.OrderInfoDto;
import com.campgem.modules.trade.dto.OrderPayDto;
import com.campgem.modules.trade.entity.Goods;
import com.campgem.modules.trade.entity.Orders;
import com.campgem.modules.trade.entity.enums.GoodsStatusEnum;
import com.campgem.modules.trade.entity.enums.OrderStatusEnum;
import com.campgem.modules.trade.service.ICartService;
import com.campgem.modules.trade.service.IGoodsService;
import com.campgem.modules.trade.service.IOrderGoodsService;
import com.campgem.modules.trade.service.IOrderService;
import com.campgem.modules.trade.vo.OrderInfoVo;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

/**
 * TODO 配置后台定时任务，指定时间内未支付的订单将设置为EXPIRED
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
	@Resource
	private IOrderGoodsService orderGoodsService;
	@Resource
	private IPaymentService paypalService;

	@ApiOperation("订单确认")
	@PostMapping("/order/info")
	public Result<List<OrderInfoVo>> queryOrderInfo(OrderInfoDto orderInfoDto) {
		if (StringUtils.isEmpty(orderInfoDto.getGoodsId()) &&
				(orderInfoDto.getCartIds() == null || orderInfoDto.getCartIds().length == 0)) {
			throw new JeecgBootException(StatusEnum.GoodsIdAndCartIdBlankError);
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
	
	@ApiOperation("订单支付，如果是PayPal支付，则返回URL，需要跳转到这个地址，如果是信用卡，返回的交易码，调用接口查询支付结果")
	@PostMapping("/order/pay")
	public Result<String> pay(@Valid @RequestBody OrderPayDto payDto) {
		if (!CommonConstant.payments.containsKey(payDto.getPaymentMethod())) {
			throw new JeecgBootException(StatusEnum.UnknownPaymentError);
		}
		
		// 创建订单
		List<Orders> orders = orderService.createGoodsOrders(payDto);
		if (CommonConstant.payments.get(payDto.getPaymentMethod()).equals("PayPal")) {
			// PayPal支付
			String url = paypalService.pay(orders);
			return new Result<String>().result(url);
		} else {
			// Visa/Masterd Card 支付
			String transId = paypalService.payWithCreditCard("");
			return new Result<String>().result(transId);
		}
	}
	
	/**
	 * 订单支付，已下单，但是没有立即支付，从个人中心未支付订单中来
	 */
	@ApiOperation(value = "订单支付", notes = "G1 Unpaid Orders")
	@GetMapping("/order/pay/{orderId}")
	public Result<String> pay(@PathVariable String orderId) {
		Orders o = orderService.getById(orderId);
		if (!o.getStatus().equals(OrderStatusEnum.UNPAID.code())) {
			// 订单已经支付或者过期了
			throw new JeecgBootException(StatusEnum.OrderPaidOrExpiredError);
		}
		
		// 商品状态
		List<Goods> goodsList = orderGoodsService.getGoodsInfo(orderId);
		for (Goods goods : goodsList) {
			if (!goods.getStatus().equals(GoodsStatusEnum.IN_SALE.code())) {
				// TODO
				throw new JeecgBootException("商品'" + goods.getGoodsName() + "'不在销售状态");
			}
		}
		
		if (CommonConstant.payments.get(o.getPaymentMethod()).equals("PayPal")) {
			// PayPal
			String url = paypalService.pay(Collections.singletonList(o));
			return new Result<String>().result(url);
		} else {
			// Visa/Masterd Card 支付
			String transId = paypalService.payWithCreditCard("");
			return new Result<String>().result(transId);
		}
	}
	
	/**
	 * paypal支付处理
	 */
	@ApiOperation("PayPal支付处理，用户同意后，回调到前端，返回支付结果")
	@PostMapping("/order/paypal/process")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "paymentId", value = "PayPal回调回来的paymentId", required = true, paramType = "form"),
			@ApiImplicitParam(name = "PayerID", value = "PayPal回调回来的PayerID", required = true, paramType = "form")
	})
	public Result paypalProcess(String paymentId, String PayerID) {
		try {
			Payment payment = paypalService.executePayment(paymentId, PayerID);
			if(payment.getState().equals("approved")){
				// 支付成功，修改订单数据
				orderService.paypalSuccess(paymentId);
				return new Result<String>().result("支付成功");
			}
			return Result.error("支付失败");
		} catch (PayPalRESTException e) {
			System.out.println(e.getMessage());
		}
		return Result.error("支付失败");
	}
}
