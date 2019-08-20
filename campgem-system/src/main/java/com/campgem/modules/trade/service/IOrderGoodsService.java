package com.campgem.modules.trade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.trade.entity.Goods;
import com.campgem.modules.trade.entity.OrdersGoods;

import java.util.List;

/**
 * @Description: 订单商品
 * @Author: campgem
 * @Date:   2019-08-19
 * @Version: V1.0
 */
public interface IOrderGoodsService extends IService<OrdersGoods> {
	
	List<Goods> getGoodsInfo(String orderId);
}
