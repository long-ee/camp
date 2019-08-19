package com.campgem.modules.trade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.util.SecurityUtils;
import com.campgem.config.paypal.PaypalPaymentIntent;
import com.campgem.config.paypal.PaypalPaymentMethod;
import com.campgem.modules.common.service.IPaypalService;
import com.campgem.modules.trade.dto.OrderPayDto;
import com.campgem.modules.trade.entity.Order;
import com.campgem.modules.trade.entity.enums.OrderStatusEnum;
import com.campgem.modules.trade.entity.enums.OrderTypeEnum;
import com.campgem.modules.trade.mapper.OrderMapper;
import com.campgem.modules.trade.service.ICartService;
import com.campgem.modules.trade.service.IOrderGoodsService;
import com.campgem.modules.trade.service.IOrderService;
import com.campgem.modules.trade.vo.OrderInfoVo;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description: 订单
 * @Author: campgem
 * @Date: 2019-08-19
 * @Version: V1.0
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
	@Resource
	private IPaypalService paypalService;
	@Resource
	private ICartService cartService;
	@Resource
	private IOrderGoodsService orderGoodsService;
	
	@Override
	@Transactional
	public Payment createPaymentByOrders(OrderPayDto payDto) {
		String uid = SecurityUtils.getCurrentUserUid();
		Date createTime = new Date();
		// 创建订单
		for (OrderPayDto.OrderInfo orderInfo : payDto.getOrders()) {
			String[] cartIds = orderInfo.getCartIds();
			if (cartIds != null && cartIds.length > 0) {
				// 通过购物车创建订单，创建完成后需要删除对应的购物车
				Order order = new Order();
				// 设置公共属性
				order.setUid(uid);
				order.setAddressId(payDto.getAddressId());
				order.setPaymentMethods(payDto.getPaymentMethod());
				order.setOrderType(OrderTypeEnum.PRODUCT.code());
				order.setStatus(OrderStatusEnum.UNPAID.code());
				order.setCreateTime(createTime);
				
				// 设置配送方式
				order.setShipping(orderInfo.getShipping());
				
				OrderInfoVo voV2 = cartService.queryOrderInfoV2(cartIds);
				// 设置卖家名称，这种情况下，返回的一定会有个key，并且是卖家名称
				order.setSellerName(voV2.getSellerName());
				order.setSellerId(voV2.getSellerId());
				// 计算金额，运费，税金
				
			}
		}
		
		
		Payment payment;
		try {
			payment = paypalService.createPayment(
					12.333,
					"USB",
					PaypalPaymentMethod.paypal,
					PaypalPaymentIntent.order,
					"",
					"cancelUrl",
					"successUrl"
					);
		} catch (PayPalRESTException e) {
			throw new JeecgBootException("订单支付失败");
		}
		return payment;
	}
}
