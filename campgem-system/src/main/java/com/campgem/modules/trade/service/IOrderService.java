package com.campgem.modules.trade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.trade.dto.OrderPayDto;
import com.campgem.modules.trade.entity.Order;
import com.paypal.api.payments.Payment;

/**
 * @Description: 订单
 * @Author: campgem
 * @Date:   2019-08-19
 * @Version: V1.0
 */
public interface IOrderService extends IService<Order> {
	Payment createPaymentByOrders(OrderPayDto payDto);
}
