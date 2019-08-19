package com.campgem.modules.trade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.modules.trade.entity.OrdersGoods;
import com.campgem.modules.trade.mapper.OrderGoodsMapper;
import com.campgem.modules.trade.service.IOrderGoodsService;
import org.springframework.stereotype.Service;

/**
 * @Description: 订单商品
 * @Author: campgem
 * @Date:   2019-08-19
 * @Version: V1.0
 */
@Service
public class OrderGoodsServiceImpl extends ServiceImpl<OrderGoodsMapper, OrdersGoods> implements IOrderGoodsService {

}
