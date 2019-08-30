package com.campgem.modules.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.order.vo.OrderGoodsOrServiceStatusVo;
import com.campgem.modules.service.entity.OrdersService;

import java.util.List;

/**
 * @Description: 订单服务
 * @Author: campgem
 * @Date:   2019-08-28
 * @Version: V1.0
 */
public interface IOrdersServiceService extends IService<OrdersService> {
	
	List<OrderGoodsOrServiceStatusVo> getServiceInfo(String orderId);
}
