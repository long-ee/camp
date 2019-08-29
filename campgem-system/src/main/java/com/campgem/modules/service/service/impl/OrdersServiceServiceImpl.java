package com.campgem.modules.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.modules.service.entity.OrdersService;
import com.campgem.modules.service.mapper.OrdersServiceMapper;
import com.campgem.modules.service.service.IOrdersServiceService;
import org.springframework.stereotype.Service;

/**
 * @Description: 订单服务
 * @Author: campgem
 * @Date:   2019-08-28
 * @Version: V1.0
 */
@Service
public class OrdersServiceServiceImpl extends ServiceImpl<OrdersServiceMapper, OrdersService> implements IOrdersServiceService {

}
