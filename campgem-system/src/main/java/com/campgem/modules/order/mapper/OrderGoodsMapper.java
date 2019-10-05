package com.campgem.modules.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.common.vo.OrdersGoodsTaskVo;
import com.campgem.modules.order.entity.OrdersGoods;
import com.campgem.modules.order.vo.OrderGoodsOrServiceStatusVo;
import com.campgem.modules.user.vo.OrdersGoodsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 订单商品
 * @Author: campgem
 * @Date:   2019-08-19
 * @Version: V1.0
 */
public interface OrderGoodsMapper extends BaseMapper<OrdersGoods> {
	
	List<OrderGoodsOrServiceStatusVo> getGoodsInfo(@Param("orderId") String orderId);
	
	List<OrdersGoodsTaskVo> queryOrderGoodsTaskList(@Param("orderId") String orderId);
	
	List<OrdersGoodsVo> queryOrderGoods(@Param("orderId") String orderId);
}
