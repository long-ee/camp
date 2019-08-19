package com.campgem.modules.trade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.trade.dto.OrderPayDto;
import com.campgem.modules.trade.entity.Orders;
import com.paypal.api.payments.Payment;

/**
 * @Description: 订单
 * @Author: campgem
 * @Date:   2019-08-19
 * @Version: V1.0
 */
public interface IOrderService extends IService<Orders> {
	/**
	 * 创建订单
	 */
	Payment createPaymentByOrders(OrderPayDto payDto);
	
	void executePayment(String paymentId, String token, String payerID);
}
