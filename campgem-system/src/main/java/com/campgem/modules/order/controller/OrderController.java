package com.campgem.modules.order.controller;

import com.campgem.common.api.vo.Result;
import com.campgem.common.constant.CommonConstant;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.modules.common.utils.CommonUtils;
import com.campgem.modules.order.dto.OrderInfoDto;
import com.campgem.modules.order.dto.OrderPayDto;
import com.campgem.modules.order.entity.Orders;
import com.campgem.modules.order.service.IOrderGoodsService;
import com.campgem.modules.order.service.IOrderService;
import com.campgem.modules.order.service.IPaymentService;
import com.campgem.modules.order.vo.OrderGoodsOrServiceStatusVo;
import com.campgem.modules.order.vo.OrderInfoVo;
import com.campgem.modules.service.dto.ServiceOrderPayDto;
import com.campgem.modules.service.entity.Service;
import com.campgem.modules.service.entity.enums.ServiceStatusEnum;
import com.campgem.modules.service.service.IOrdersServiceService;
import com.campgem.modules.service.service.IServiceService;
import com.campgem.modules.trade.entity.enums.GoodsStatusEnum;
import com.campgem.modules.trade.entity.enums.OrderStatusEnum;
import com.campgem.modules.trade.entity.enums.OrderTypeEnum;
import com.campgem.modules.trade.service.ICartService;
import com.campgem.modules.trade.service.IGoodsService;
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
 *
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
	private IOrdersServiceService ordersServiceService;
	@Resource
	private IPaymentService paymentService;
	@Resource
	private IServiceService serviceService;
	
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
	
	@ApiOperation(value = "订单支付，如果是PayPal支付，则返回URL，需要跳转到这个地址，如果是信用卡，返回的交易码，调用接口查询支付结果", notes = "C20")
	@PostMapping("/order/pay")
	public Result<String> goodsOrderPay(@Valid @RequestBody OrderPayDto payDto) {
		if (!CommonConstant.payments.containsKey(payDto.getPaymentMethod())) {
			throw new JeecgBootException(StatusEnum.UnknownPaymentError);
		}
		
		// 创建订单
		List<Orders> orders = orderService.createGoodsOrders(payDto);
		
		String result = pay(orders, payDto.getPaymentMethod(), payDto.getNonce());
		return new Result<String>().result(result);
	}
	
	@ApiOperation(value = "商圈支付，如果是PayPal支付，则返回URL，需要跳转到这个地址，如果是信用卡，返回的交易码，调用接口查询支付结果", notes = "D16 确认下单")
	@PostMapping("/service/order/pay")
	public Result<String> serviceOrderPay(@Valid @RequestBody ServiceOrderPayDto payDto) {
		if (!CommonConstant.payments.containsKey(payDto.getPaymentMethod())) {
			throw new JeecgBootException(StatusEnum.UnknownPaymentError);
		}
		
		Service service = serviceService.getById(payDto.getServiceId());
		Orders orders = orderService.createServiceOrder(service, payDto);
		
		String result = pay(Collections.singletonList(orders), payDto.getPaymentMethod(), payDto.getNonce());
		return new Result<String>().result(result);
	}
	
	/**
	 * 支付
	 * @param ordersList 订单列表
	 * @param paymentMethod 支付方式
	 * @param nonce Braintree信用卡支付要用到
	 * @return 如果是PayPal，返回一个地址，前端需要重定向到这个地址，如果是Braintree
	 */
	private String pay(List<Orders> ordersList, Integer paymentMethod, String nonce) {
		if (CommonUtils.isPaypal(paymentMethod)) {
			// PayPal支付
			return paymentService.pay(ordersList);
		} else {
			// Visa/Masterd Card 支付
			return paymentService.payWithCreditCard(nonce);
		}
	}
	
	/**
	 * 订单支付，已下单，但是没有立即支付，从个人中心未支付订单中来
	 */
	@ApiOperation(value = "订单支付", notes = "G1 Unpaid Orders")
	@GetMapping("/order/pay/{orderId}")
	public Result<String> goodsOrderPay(@PathVariable String orderId) {
		Orders o = orderService.getById(orderId);
		if (!o.getStatus().equals(OrderStatusEnum.UNPAID.code())) {
			// 订单已经支付或者过期了
			throw new JeecgBootException(StatusEnum.OrderPaidOrExpiredError);
		}
		
		if (o.getOrderType().equals(OrderTypeEnum.PRODUCT.code())) {
			// 商品状态
			List<OrderGoodsOrServiceStatusVo> goodsList = orderGoodsService.getGoodsInfo(orderId);
			for (OrderGoodsOrServiceStatusVo goods : goodsList) {
				if (!goods.getStatus().equals(GoodsStatusEnum.IN_SALE.code())) {
					throw new JeecgBootException(StatusEnum.GoodsStatusError, goods.getName());
				}
			}
		} else {
			// 服务状态
			List<OrderGoodsOrServiceStatusVo> serviceList = ordersServiceService.getServiceInfo(orderId);
			for (OrderGoodsOrServiceStatusVo service : serviceList) {
				if (!service.getStatus().equals(ServiceStatusEnum.ENABLE.code())) {
					throw new JeecgBootException(StatusEnum.ServiceStatusError, service.getName());
				}
			}
		}
		
		String result = pay(Collections.singletonList(o), o.getPaymentMethod(), o.getNonce());
		return new Result<String>().result(result);
	}
	
	@ApiOperation("Braintree 信用卡支付token")
	@GetMapping("/order/pay/token")
	public Result<String> getCreditCardPayToken() {
		String token = paymentService.getToken();
		return new Result<String>().result(token);
	}
	
	/**
	 * paypal支付处理
	 */
	@ApiOperation("PayPal支付处理，用户同意后，回调到前端，获取参数，调用此接口，返回支付结果")
	@PostMapping("/order/paypal/process")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "paymentId", value = "PayPal回调回来的paymentId", required = true, paramType = "form"),
			@ApiImplicitParam(name = "PayerID", value = "PayPal回调回来的PayerID", required = true, paramType = "form")
	})
	public Result paypalProcess(String paymentId, String PayerID) {
		try {
			Payment payment = paymentService.executePayment(paymentId, PayerID);
			if (payment.getState().equals("approved")) {
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
