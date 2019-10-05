package com.campgem.modules.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.modules.order.vo.OrderGoodsOrServiceStatusVo;
import com.campgem.modules.service.entity.OrdersService;
import com.campgem.modules.service.mapper.OrdersServiceMapper;
import com.campgem.modules.service.service.IOrdersServiceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 订单服务
 * @Author: ling
 * @Date: 2019-08-28
 * @Version: V1.0
 */
@Service
public class OrdersServiceServiceImpl extends ServiceImpl<OrdersServiceMapper, OrdersService> implements IOrdersServiceService {
	
	@Override
	public List<OrderGoodsOrServiceStatusVo> getServiceInfo(String orderId) {
		return baseMapper.getServiceInfo(orderId);
	}
}
