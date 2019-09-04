package com.campgem.modules.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.modules.common.vo.OrdersTaskVo;
import com.campgem.modules.order.entity.Orders;
import com.campgem.modules.user.vo.OrdersDetailVo;
import com.campgem.modules.user.vo.OrdersListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 订单
 * @Author: campgem
 * @Date:   2019-08-19
 * @Version: V1.0
 */
public interface OrderMapper extends BaseMapper<Orders> {
	
	void updatePayId(@Param("payId") String payId, @Param("ids") List<String> orderIds);
	
	void paypalSuccessByPayId(@Param("payId") String payId, @Param("status") Integer status);
	
	List<OrdersTaskVo> queryExpiredOrderList(@Param("status") Integer status);
	
	/**
	 * 设置订单状态
	 */
	void updateOrderStatusByIds(@Param("ids") List<String> orderIds, @Param("status") Integer status);
	
	IPage<OrdersListVo> queryUserOrders(Page page, @Param("uid") String uid, @Param("status") String status);
	
	OrdersDetailVo queryUserOrdersDetail(@Param("uid") String uid, @Param("orderId") String orderId);
	
	boolean updateOrderStatusById(@Param("orderId") String orderId, @Param("status") Integer status);
	
	boolean updateTrackingNumber(@Param("orderId") String orderId, @Param("trackingNumber") String trackingNumber);
	
	OrdersTaskVo queryExpiredOrderById(@Param("orderId") String orderId);
}
