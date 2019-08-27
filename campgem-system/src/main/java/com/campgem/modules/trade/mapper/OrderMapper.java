package com.campgem.modules.trade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.modules.common.vo.OrdersTaskVo;
import com.campgem.modules.trade.entity.Orders;
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
	
	void paypalSuccess(@Param("payId") String payId);
	
	List<OrdersTaskVo> queryExpiredOrderList();
	
	/**
	 * 设置订单状态为已过期
	 */
	void updateOrderStatusExpiredByIds(@Param("ids") List<String> orderIds);
	
	IPage<OrdersListVo> queryUserOrders(Page page, @Param("uid") String uid, @Param("status") String status);
	
	OrdersDetailVo queryUserOrdersDetail(@Param("uid") String uid, @Param("orderId") String orderId);
}
