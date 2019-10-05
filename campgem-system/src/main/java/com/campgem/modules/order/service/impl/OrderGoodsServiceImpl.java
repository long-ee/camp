package com.campgem.modules.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.modules.order.entity.OrdersGoods;
import com.campgem.modules.order.mapper.OrderGoodsMapper;
import com.campgem.modules.order.service.IOrderGoodsService;
import com.campgem.modules.order.vo.OrderGoodsOrServiceStatusVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 订单商品
 * @Author: campgem
 * @Date:   2019-08-19
 * @Version: V1.0
 */
@Service
public class OrderGoodsServiceImpl extends ServiceImpl<OrderGoodsMapper, OrdersGoods> implements IOrderGoodsService {
	
	@Override
	public List<OrderGoodsOrServiceStatusVo> getGoodsInfo(String orderId) {
		return baseMapper.getGoodsInfo(orderId);
	}
}
