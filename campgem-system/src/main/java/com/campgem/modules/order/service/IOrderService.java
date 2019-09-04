package com.campgem.modules.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.order.dto.OrderPayDto;
import com.campgem.modules.order.entity.Orders;
import com.campgem.modules.service.dto.ServiceOrderPayDto;
import com.campgem.modules.service.entity.Service;
import com.campgem.modules.user.dto.OrdersEvaluationDto;
import com.campgem.modules.user.vo.OrdersDetailVo;
import com.campgem.modules.user.vo.OrdersListVo;

import java.util.List;

/**
 * @Description: 订单
 * @Author: campgem
 * @Date:   2019-08-19
 * @Version: V1.0
 */
public interface IOrderService extends IService<Orders> {
	/**
	 * 创建商品订单
	 */
	List<Orders> createGoodsOrders(OrderPayDto payDto);
	
	/**
	 * 更新支付ID
	 */
	void updatePayId(String payId, List<String> orderIds);
	
	/**
	 * 支付成功，修改订单数据
	 * PayPal支付，通过pay-id修改订单状态
	 */
	void paypalSuccess(String paymentId);
	
	/**
	 * 创建服务订单
	 */
	Orders createServiceOrder(Service service, ServiceOrderPayDto payDto);
	
	/**
	 * 处理过期未支付的订单
	 */
	void checkOrderStatus();
	
	/**
	 * 处理过期未支付的订单
	 */
	void checkOrderStatusById(String orderId);
	
	/**
	 * 查询用户订单
	 */
	IPage<OrdersListVo> queryUserOrders(String s, Page page);
	
	/**
	 * 用户订单详情
	 */
	OrdersDetailVo queryUserOrdersDetail(String orderId);
	
	boolean evaluate(String orderId, OrdersEvaluationDto dto);
	
	boolean updateOrderStatusById(String orderId, Integer status);
	
	boolean finished(String orderId);
	
	boolean updateTrackingNumber(String orderId, String trackingNumber);
	
	/**
	 * 信用卡支付成功
	 */
	void creditCardPaySuccess(List<Orders> ordersList);
}
