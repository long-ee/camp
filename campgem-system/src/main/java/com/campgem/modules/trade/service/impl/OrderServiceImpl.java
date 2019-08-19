package com.campgem.modules.trade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.util.SecurityUtils;
import com.campgem.config.paypal.PaypalPaymentIntent;
import com.campgem.config.paypal.PaypalPaymentMethod;
import com.campgem.modules.common.service.IPaypalService;
import com.campgem.modules.trade.dto.OrderPayDto;
import com.campgem.modules.trade.entity.Orders;
import com.campgem.modules.trade.entity.OrdersGoods;
import com.campgem.modules.trade.entity.enums.OrderStatusEnum;
import com.campgem.modules.trade.entity.enums.OrderTypeEnum;
import com.campgem.modules.trade.mapper.OrderMapper;
import com.campgem.modules.trade.service.ICartService;
import com.campgem.modules.trade.service.IOrderGoodsService;
import com.campgem.modules.trade.service.IOrderService;
import com.campgem.modules.trade.vo.OrderInfoVo;
import com.campgem.modules.user.vo.ShippingMethodsVo;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Description: 订单
 * @Author: campgem
 * @Date: 2019-08-19
 * @Version: V1.0
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements IOrderService {
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
		List<Orders> ordersList = new ArrayList<>();
		// 创建订单
		for (OrderPayDto.OrderInfo orderInfo : payDto.getOrders()) {
			String[] cartIds = orderInfo.getCartIds();
			if (cartIds != null && cartIds.length > 0) {
				// 通过购物车创建订单，创建完成后需要删除对应的购物车
				Orders orders = new Orders();
				String orderId = UUID.randomUUID().toString().replaceAll("-", "");
				// 设置公共属性
				orders.setId(orderId);
				orders.setUid(uid);
				orders.setAddressId(payDto.getAddressId());
				orders.setPaymentMethods(payDto.getPaymentMethod());
				orders.setOrderType(OrderTypeEnum.PRODUCT.code());
				orders.setStatus(OrderStatusEnum.UNPAID.code());
				orders.setCreateTime(createTime);
				
				// 设置配送方式
				orders.setShipping(orderInfo.getShipping());
				
				List<OrderInfoVo> orderInfoVos = cartService.queryOrderInfo(cartIds);
				if (orderInfoVos.size() > 1) {
					// 数据有错误
					throw new JeecgBootException("购物车ID数据有误");
				}
				// 设置卖家名称，这种情况下，返回的一定会有个key，并且是卖家名称
				orders.setSellerName(orderInfoVos.get(0).getSellerName());
				orders.setSellerId(orderInfoVos.get(0).getSellerId());
				
				// 计算金额，包括运费和税金
				List<ShippingMethodsVo> methods = orderInfoVos.get(0).getShippingMethods();
				for (ShippingMethodsVo method : methods) {
					if (method.getName().equals(orderInfo.getShipping())) {
						orders.setFreightAmount(new BigDecimal(method.getPrice()));
						break;
					}
				}
				
				double totalTaxes = 0;
				double totalAmount = 0;
				List<OrdersGoods> listOrdersGoods = new ArrayList<>();
				for (OrderInfoVo.SellerGoods sellerGoods : orderInfoVos.get(0).getGoods()) {
					double taxes = sellerGoods.getTaxes().doubleValue() / 100.0 * sellerGoods.getSalePrice().doubleValue();
					double amount = sellerGoods.getSalePrice().doubleValue() * sellerGoods.getQuantity();
					totalTaxes += taxes * sellerGoods.getQuantity();
					totalAmount += amount;
					
					OrdersGoods ordersGoods = new OrdersGoods();
					ordersGoods.setGoodsIcon(sellerGoods.getGoodsIcon());
					ordersGoods.setGoodsName(sellerGoods.getGoodsName());
					ordersGoods.setTaxes(new BigDecimal(taxes));
					ordersGoods.setPrice(sellerGoods.getSalePrice());
					ordersGoods.setSpecificationName(sellerGoods.getSpecificationName());
					ordersGoods.setQuantity(sellerGoods.getQuantity());
					ordersGoods.setOrderId(orderId);
					
					listOrdersGoods.add(ordersGoods);
				}
				
				orders.setTaxesAmount(new BigDecimal(totalTaxes));
				orders.setAmount(new BigDecimal(totalAmount));
				
				orders.setPayAmount(new BigDecimal(totalTaxes + totalAmount + orders.getFreightAmount().doubleValue()));
				
				baseMapper.insert(orders);
				cartService.removeByIds(Arrays.asList(cartIds));
				
				// 添加订单商品表
				orderGoodsService.saveBatch(listOrdersGoods);
				
				ordersList.add(orders);
			}
		}


		Payment payment;
		try {
			payment = paypalService.createPayment(
					12.33,
					"USD",
					PaypalPaymentMethod.paypal,
					PaypalPaymentIntent.sale,
					"",
					"http://192.168.0.173:8080/order/paypal/cancel",
					"http://192.168.0.173:8080/order/paypal/notify"
					);
		} catch (PayPalRESTException e) {
			System.out.println(e.getMessage());
			throw new JeecgBootException("订单支付失败");
		}
		return payment;
	}
	
	@Override
	public void executePayment(String paymentId, String token, String payerID) {
		try {
			Payment payment = paypalService.executePayment(paymentId, payerID);
			if(payment.getState().equals("approved")){
				System.out.println("支付成功");
			}
			System.out.println(payment);
		} catch (PayPalRESTException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
