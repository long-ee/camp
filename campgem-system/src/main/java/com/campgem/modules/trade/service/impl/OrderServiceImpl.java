package com.campgem.modules.trade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.service.dto.ServiceOrderPayDto;
import com.campgem.modules.trade.dto.OrderPayDto;
import com.campgem.modules.trade.entity.Orders;
import com.campgem.modules.trade.entity.OrdersGoods;
import com.campgem.modules.trade.entity.enums.OrderStatusEnum;
import com.campgem.modules.trade.entity.enums.OrderTypeEnum;
import com.campgem.modules.trade.mapper.OrderMapper;
import com.campgem.modules.trade.service.ICartService;
import com.campgem.modules.trade.service.IOrderGoodsService;
import com.campgem.modules.trade.service.IOrderService;
import com.campgem.modules.trade.vo.OrderInfoVo;
import com.campgem.modules.user.vo.ShippingMethodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: 订单
 * @Author: campgem
 * @Date: 2019-08-19
 * @Version: V1.0
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements IOrderService {
	@Resource
	private ICartService cartService;
	@Resource
	private IOrderGoodsService orderGoodsService;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	@Override
	@Transactional
	public List<Orders> createGoodsOrders(OrderPayDto payDto) {
		String uid = SecurityUtils.getCurrentUserUid();
		Date createTime = new Date();
		List<Orders> ordersList = new ArrayList<>();
		// 创建订单
		for (OrderPayDto.OrderInfo orderInfo : payDto.getOrders()) {
			String[] cartIds = orderInfo.getCartIds();
			if (cartIds != null && cartIds.length > 0) {
				// 通过购物车创建订单，创建完成后需要删除对应的购物车
				Orders orders = new Orders();
				String orderId = UUID.randomUUID().toString().replaceAll("-", "");
				// 设置公共属性
				orders.setId(orderId);
				orders.setUid(uid);
				orders.setAddressId(payDto.getAddressId());
				orders.setPaymentMethod(payDto.getPaymentMethod());
				orders.setOrderType(OrderTypeEnum.PRODUCT.code());
				orders.setStatus(OrderStatusEnum.UNPAID.code());
				orders.setCreateTime(createTime);
				
				// 设置配送方式
				orders.setShipping(orderInfo.getShipping());
				
				List<OrderInfoVo> orderInfoVos = cartService.queryOrderInfo(cartIds);
				if (orderInfoVos.size() > 1) {
					// 数据有错误
					throw new JeecgBootException(StatusEnum.CartDataError);
				}
				// 设置卖家名称，这种情况下，返回的一定会有个key，并且是卖家名称
				orders.setSellerName(orderInfoVos.get(0).getMemberName());
				orders.setSellerId(orderInfoVos.get(0).getSellerId());
				
				// 计算金额，包括运费和税金
				List<ShippingMethodsVo> methods = orderInfoVos.get(0).getShippingMethods();
				for (ShippingMethodsVo method : methods) {
					if (method.getName().equals(orderInfo.getShipping())) {
						orders.setFreightAmount(new BigDecimal(method.getPrice()));
						break;
					}
				}
				
				double totalTaxes = 0;
				double totalAmount = 0;
				List<OrdersGoods> listOrdersGoods = new ArrayList<>();
				for (OrderInfoVo.SellerGoods sellerGoods : orderInfoVos.get(0).getGoods()) {
					double taxes = sellerGoods.getTaxes().doubleValue() / 100.0 * sellerGoods.getSalePrice().doubleValue();
					double amount = sellerGoods.getSalePrice().doubleValue() * sellerGoods.getQuantity();
					totalTaxes += taxes * sellerGoods.getQuantity();
					totalAmount += amount;
					
					OrdersGoods ordersGoods = new OrdersGoods();
					ordersGoods.setGoodsIcon(sellerGoods.getGoodsIcon());
					ordersGoods.setGoodsName(sellerGoods.getGoodsName());
					ordersGoods.setGoodsId(sellerGoods.getGoodsId());
					ordersGoods.setTaxes(new BigDecimal(taxes));
					ordersGoods.setPrice(sellerGoods.getSalePrice());
					ordersGoods.setSpecificationName(sellerGoods.getSpecificationName());
					ordersGoods.setQuantity(sellerGoods.getQuantity());
					ordersGoods.setOrderId(orderId);
					
					listOrdersGoods.add(ordersGoods);
					
					// TODO 减少库存
				}
				
				orders.setTaxesAmount(new BigDecimal(totalTaxes));
				orders.setAmount(new BigDecimal(totalAmount));
				
				orders.setPayAmount(new BigDecimal(totalTaxes + totalAmount + orders.getFreightAmount().doubleValue()));
				
				baseMapper.insert(orders);
				cartService.removeByIds(Arrays.asList(cartIds));
				
				// 添加订单商品表
				orderGoodsService.saveBatch(listOrdersGoods);
				
				ordersList.add(orders);
			}
		}

		return ordersList;
	}
	
	@Override
	public void updatePayId(String payId, List<String> orderIds) {
		baseMapper.updatePayId(payId, orderIds);
	}
	
	@Override
	public void paypalSuccess(String paymentId) {
		baseMapper.paypalSuccess(paymentId);
	}
	
	@Override
	public Orders createServiceOrder(com.campgem.modules.service.entity.Service service, ServiceOrderPayDto payDto) {
		String uid = SecurityUtils.getCurrentUserUid();
		Date createTime = new Date();
		String orderId = UUID.randomUUID().toString().replaceAll("-", "");
		
		Orders orders = new Orders();
		// 设置公共属性
		orders.setId(orderId);
		orders.setUid(uid);
		orders.setPaymentMethod(payDto.getPaymentMethod());
		orders.setOrderType(OrderTypeEnum.SERVICE.code());
		orders.setStatus(OrderStatusEnum.UNPAID.code());
		orders.setCreateTime(createTime);
		
		try {
			Date appointment = sdf.parse(payDto.getDate() + " " + payDto.getTime());
			orders.setAppointment(appointment);
		} catch (ParseException ignore) {
			throw new JeecgBootException(StatusEnum.AppointmentTimeError);
		}
		
		// 设置价格
		orders.setAmount(service.getSalePrice());
		orders.setTaxesAmount(new BigDecimal(service.getSalePrice().doubleValue() * service.getTaxes().doubleValue() / 100.00));
		orders.setPayAmount(service.getSalePrice().add(orders.getTaxesAmount()));
		
		// 设置商家属性
		orders.setSellerName(service.getMemberName());
		orders.setSellerId(service.getUid());
		
		if (!save(orders)) {
			// 添加失败
			throw new JeecgBootException(StatusEnum.OrderCreatedError);
		}
		
		return orders;
	}
}
