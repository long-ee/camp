package com.campgem.modules.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.order.entity.OrdersGoods;
import com.campgem.modules.order.vo.OrderGoodsOrServiceStatusVo;

import java.util.List;

/**
 * @Description: 订单商品
 * @Author: campgem
 * @Date:   2019-08-19
 * @Version: V1.0
 */
public interface IOrderGoodsService extends IService<OrdersGoods> {
	
	List<OrderGoodsOrServiceStatusVo> getGoodsInfo(String orderId);
}
