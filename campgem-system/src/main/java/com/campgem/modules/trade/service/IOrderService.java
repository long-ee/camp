package com.campgem.modules.trade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.trade.dto.OrderPayDto;
import com.campgem.modules.trade.entity.Orders;

import java.util.List;

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
	List<Orders> createOrders(OrderPayDto payDto);
	
	/**
	 * 更新支付ID
	 */
	void updatePayId(String payId, List<String> orderIds);
	
	/**
	 * 支付成功，修改订单数据
	 */
	void paypalSuccess(String paymentId);
}
