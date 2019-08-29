package com.campgem.modules.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.service.entity.OrdersService;
import com.campgem.modules.user.vo.OrdersServiceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 订单服务
 * @Author: campgem
 * @Date:   2019-08-28
 * @Version: V1.0
 */
public interface OrdersServiceMapper extends BaseMapper<OrdersService> {
	List<OrdersServiceVo> queryOrdersService(@Param("orderId") String orderId);
}
