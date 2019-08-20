package com.campgem.modules.trade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.trade.entity.Goods;
import com.campgem.modules.trade.entity.OrdersGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 订单商品
 * @Author: campgem
 * @Date:   2019-08-19
 * @Version: V1.0
 */
public interface OrderGoodsMapper extends BaseMapper<OrdersGoods> {
	
	List<Goods> getGoodsInfo(@Param("orderId") String orderId);
}
