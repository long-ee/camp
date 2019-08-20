package com.campgem.modules.trade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.trade.entity.Orders;
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
}
