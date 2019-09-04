package com.campgem.modules.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.common.utils.CommonUtils;
import com.campgem.modules.common.vo.OrdersGoodsTaskVo;
import com.campgem.modules.common.vo.OrdersTaskVo;
import com.campgem.modules.order.dto.OrderPayDto;
import com.campgem.modules.order.entity.Orders;
import com.campgem.modules.order.entity.OrdersGoods;
import com.campgem.modules.order.mapper.OrderMapper;
import com.campgem.modules.order.service.IOrderGoodsService;
import com.campgem.modules.order.service.IOrderService;
import com.campgem.modules.order.vo.OrderInfoVo;
import com.campgem.modules.service.dto.ServiceOrderPayDto;
import com.campgem.modules.service.entity.OrdersService;
import com.campgem.modules.service.entity.ServiceImages;
import com.campgem.modules.service.service.IOrdersServiceService;
import com.campgem.modules.service.service.IServiceEvaluationService;
import com.campgem.modules.service.service.IServiceImagesService;
import com.campgem.modules.trade.entity.enums.OrderStatusEnum;
import com.campgem.modules.trade.entity.enums.OrderTypeEnum;
import com.campgem.modules.trade.service.ICartService;
import com.campgem.modules.trade.service.IGoodsEvaluationService;
import com.campgem.modules.trade.service.IGoodsService;
import com.campgem.modules.trade.service.IGoodsSpecificationsService;
import com.campgem.modules.user.dto.OrdersEvaluationDto;
import com.campgem.modules.user.service.IMemberService;
import com.campgem.modules.user.vo.MemberVo;
import com.campgem.modules.user.vo.OrdersDetailVo;
import com.campgem.modules.user.vo.OrdersListVo;
import com.campgem.modules.user.vo.ShippingMethodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
	@Resource
	private IGoodsSpecificationsService goodsSpecificationsService;
	@Resource
	private IOrdersServiceService ordersServiceService;
	@Resource
	private IServiceImagesService serviceImagesService;
	@Resource
	private IMemberService memberService;
	
	@Resource
	private IGoodsService goodsService;
	@Resource
	private IGoodsEvaluationService goodsEvaluationService;
	@Resource
	private IServiceEvaluationService serviceEvaluationService;
	
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static SimpleDateFormat orderSdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@Value("${jeecg.order.expired-interval}")
	private Integer expiredInterval;
	
	private static TimeUnit expiredTimeUnit = TimeUnit.HOURS;
	
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
				orders.setOrderNo(getOrderNo(createTime));
				orders.setAddressId(payDto.getAddressId());
				orders.setPaymentMethod(payDto.getPaymentMethod());
				orders.setOrderType(OrderTypeEnum.PRODUCT.code());
				orders.setStatus(OrderStatusEnum.UNPAID.code());
				orders.setCreateTime(createTime);
				orders.setExpiredTime(getExpiredTime(createTime));
				
				// 设置配送方式
				orders.setShipping(orderInfo.getShipping());
				
				List<OrderInfoVo> orderInfoVos = cartService.queryOrderInfo(cartIds);
				if (orderInfoVos.size() > 1) {
					// 数据有错误
					throw new JeecgBootException(StatusEnum.CartDataError);
				}
				OrderInfoVo orderInfoVo = orderInfoVos.get(0);
				// 设置卖家名称，这种情况下，返回的一定会有个key，并且是卖家名称
				orders.setSellerName(orderInfoVo.getMemberName());
				orders.setSellerId(orderInfoVo.getSellerId());
				
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
				for (OrderInfoVo.SellerGoods sellerGoods : orderInfoVo.getGoods()) {
					double taxes = 0;
					double amount = 0;
					if (CommonUtils.isBusiness(orderInfoVo.getMemberType())) {
						// 商家库存是否足够
						if (sellerGoods.getQuantity() > sellerGoods.getSpecificationStock()) {
							throw new JeecgBootException(StatusEnum.GoodsStockNotEnoughError, sellerGoods.getGoodsName());
						}
						
						// 减少商家用户库存
						goodsSpecificationsService.updateStock(2, sellerGoods.getSpecificationId(), sellerGoods.getQuantity());
						
						taxes = sellerGoods.getTaxes().doubleValue() / 100.0 * sellerGoods.getSalePrice().doubleValue();
						amount = sellerGoods.getSalePrice().doubleValue() * sellerGoods.getQuantity();
					} else {
						// 学生/一般用户库存是否足够
						if (sellerGoods.getQuantity() > sellerGoods.getStock()) {
							throw new JeecgBootException(StatusEnum.GoodsStockNotEnoughError, sellerGoods.getGoodsName());
						}
						// 减少学生/一般用户库存
						goodsService.updateStock(2, sellerGoods.getGoodsId(), sellerGoods.getQuantity());
						
						// 学生/一般用户商品没有税率
						taxes = 0;
						amount = sellerGoods.getSalePrice().doubleValue() * sellerGoods.getQuantity();
					}
					
					totalTaxes += taxes * sellerGoods.getQuantity();
					totalAmount += amount;
					
					OrdersGoods ordersGoods = new OrdersGoods();
					ordersGoods.setGoodsIcon(sellerGoods.getGoodsIcon());
					ordersGoods.setGoodsName(sellerGoods.getGoodsName());
					ordersGoods.setGoodsId(sellerGoods.getGoodsId());
					ordersGoods.setTaxes(new BigDecimal(taxes));
					ordersGoods.setSpecificationId(sellerGoods.getSpecificationId());
					ordersGoods.setPrice(sellerGoods.getSalePrice());
					ordersGoods.setSpecificationName(sellerGoods.getSpecificationName());
					ordersGoods.setQuantity(sellerGoods.getQuantity());
					ordersGoods.setOrderId(orderId);
					
					listOrdersGoods.add(ordersGoods);
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
		
		// 添加Redis缓存，过期时间就是超时时间
		for (Orders o : ordersList) {
			redisTemplate.opsForValue().set(o.getId(), "", expiredInterval, expiredTimeUnit);
		}

		return ordersList;
	}
	
	@Override
	public void updatePayId(String payId, List<String> orderIds) {
		baseMapper.updatePayId(payId, orderIds);
	}
	
	@Override
	public void paypalSuccess(String paymentId) {
		baseMapper.paypalSuccessByPayId(paymentId, OrderStatusEnum.PAID.code());
	}
	
	@Override
	public void creditCardPaySuccess(List<Orders> ordersList) {
		List<String> ids = new ArrayList<>();
		for (Orders o : ordersList) {
			ids.add(o.getId());
		}
		
		// 更新为已支付
		baseMapper.updateOrderStatusByIds(ids, OrderStatusEnum.PAID.code());
	}
	
	private Date getExpiredTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		// 过期时间
		calendar.add(Calendar.HOUR, expiredInterval);
		return calendar.getTime();
	}
	
	private String getOrderNo(Date date) {
		return String.format("%s%04d", orderSdf.format(date), new Random().nextInt(9999));
	}
	
	@Override
	@Transactional
	public Orders createServiceOrder(com.campgem.modules.service.entity.Service service, ServiceOrderPayDto payDto) {
		String uid = SecurityUtils.getCurrentUserUid();
		Date createTime = new Date();
		String orderId = UUID.randomUUID().toString().replaceAll("-", "");
		
		Orders orders = new Orders();
		// 设置公共属性
		orders.setId(orderId);
		orders.setUid(uid);
		orders.setOrderNo(getOrderNo(createTime));
		orders.setPaymentMethod(payDto.getPaymentMethod());
		orders.setOrderType(OrderTypeEnum.SERVICE.code());
		orders.setStatus(OrderStatusEnum.UNPAID.code());
		orders.setCreateTime(createTime);
		orders.setNonce(payDto.getNonce());
		orders.setExpiredTime(getExpiredTime(createTime));
		
		Date appointment;
		try {
			appointment = sdf.parse(payDto.getDate() + " " + payDto.getTime());
		} catch (ParseException ignore) {
			throw new JeecgBootException(StatusEnum.AppointmentTimeError);
		}
		orders.setAppointment(appointment);
		
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
		
		// 查询服务列表图片
		LambdaQueryWrapper<ServiceImages> query = new LambdaQueryWrapper<>();
		query.eq(ServiceImages::getServiceId, service.getId());
		query.eq(ServiceImages::getIsListImage, 1);
		ServiceImages serviceImages = serviceImagesService.getOne(query);
		
		// 添加订单服务表
		OrdersService ordersService = new OrdersService();
		ordersService.setOrderId(orderId);
		ordersService.setServiceId(service.getId());
		ordersService.setAppointmentTime(appointment);
		ordersService.setPrice(service.getSalePrice());
		ordersService.setServiceName(service.getServiceName());
		ordersService.setTaxes(service.getTaxes());
		if (serviceImages != null) {
			ordersService.setServiceIcon(serviceImages.getServiceImage());
		}
		
		ordersServiceService.save(ordersService);
		
		// 添加Redis缓存，过期时间就是超时时间
		redisTemplate.opsForValue().set(ordersService.getId(), "", expiredInterval, expiredTimeUnit);
		
		return orders;
	}
	
	@Override
	@Transactional
	public void checkOrderStatus() {
		List<OrdersTaskVo> ordersList = baseMapper.queryExpiredOrderList(OrderStatusEnum.UNPAID.code());
		log.info("过期订单数量：" + ordersList.size());
		
		expiredOrders(ordersList);
	}
	
	@Override
	public void checkOrderStatusById(String orderId) {
		// 按照ID查找，如果订单已支付，则查询为空，这种情况下，添加了Redis缓存就不同管了
		OrdersTaskVo ordersTaskVo = baseMapper.queryExpiredOrderById(orderId, OrderStatusEnum.UNPAID.code());
		
		if (ordersTaskVo != null) {
			expiredOrders(Collections.singletonList(ordersTaskVo));
		}
	}
	
	private void expiredOrders(List<OrdersTaskVo> ordersList) {
		
		// 修改订单状态
		List<String> orderIds = new ArrayList<>();
		
		// 商家库存数据
		Map<String, Integer> businessMap = new HashMap<>();
		// 学生/一般用户库存
		Map<String, Integer> generalMap = new HashMap<>();
		for (OrdersTaskVo orders : ordersList) {
			orderIds.add(orders.getId());
			
			if (orders.getOrderType().equals(OrderTypeEnum.SERVICE.code())) {
				// 如果是服务订单，则跳过，因为服务订单没有库存，但是还是要设置此订单为过期状态
				continue;
			}
			
			if (CommonUtils.isBusiness(orders.getMemberType())) {
				for (OrdersGoodsTaskVo ordersGoods : orders.getGoods()) {
					if (businessMap.containsKey(ordersGoods.getSpecificationId())) {
						businessMap.replace(ordersGoods.getSpecificationId(), businessMap.get(ordersGoods.getSpecificationId()) + ordersGoods.getQuantity());
					} else {
						businessMap.put(ordersGoods.getSpecificationId(), ordersGoods.getQuantity());
					}
				}
			} else {
				for (OrdersGoodsTaskVo ordersGoods : orders.getGoods()) {
					if (generalMap.containsKey(ordersGoods.getGoodsId())) {
						generalMap.replace(ordersGoods.getGoodsId(), generalMap.get(ordersGoods.getGoodsId()) + ordersGoods.getQuantity());
					} else {
						generalMap.put(ordersGoods.getGoodsId(), ordersGoods.getQuantity());
					}
				}
			}
		}
		
		// 恢复商家商品库存
		for (String key : businessMap.keySet()) {
			goodsSpecificationsService.updateStock(1, key, businessMap.get(key));
		}
		
		// 回复学生/一般用户商品库存
		for (String key : generalMap.keySet()) {
			goodsService.updateStock(1, key, generalMap.get(key));
		}
		
		if (orderIds.size() > 0) {
			// 更新订单过期状态
			baseMapper.updateOrderStatusByIds(orderIds, OrderStatusEnum.EXPIRED.code());
		}
	}
	
	@Override
	public IPage<OrdersListVo> queryUserOrders(String status, Page page) {
		String uid = SecurityUtils.getCurrentUserUid();
		return baseMapper.queryUserOrders(page, uid, status);
	}
	
	@Override
	public OrdersDetailVo queryUserOrdersDetail(String orderId) {
		String uid = SecurityUtils.getCurrentUserUid();
		OrdersDetailVo detail = baseMapper.queryUserOrdersDetail(uid, orderId);
		if (detail == null) {
			throw new JeecgBootException(StatusEnum.OrdersNotExistError);
		}
		
		if (detail.getRole().equals("buyer")) {
			// 设置卖家信息
			MemberVo member = memberService.getMemberByUserBaseId(detail.getSellerId());
			detail.setSellerName(member.getMemberName());
		} else {
			// 设置买家信息
			MemberVo member = memberService.getMemberByUserBaseId(detail.getUid());
			detail.setMemberName(member.getMemberName());
			detail.setMobile(member.getMobile());
		}
		
		return detail;
	}
	
	@Override
	@Transactional
	public boolean evaluate(String orderId, OrdersEvaluationDto dto) {
		String uid = SecurityUtils.getCurrentUserUid();
		Orders orders = getById(orderId);
		if (!orders.getUid().equals(uid) && !orders.getSellerId().equals(uid)) {
			// 不是买家也不是卖家
			throw new JeecgBootException(StatusEnum.OrdersOwnerError);
		}
		
		Integer status = orders.getStatus();
		if (status.equals(OrderStatusEnum.UNPAID.code()) || status.equals(OrderStatusEnum.EVALUATED.code()) ||
				status.equals(OrderStatusEnum.OFFLINE_TRADING.code()) || status.equals(OrderStatusEnum.EXPIRED.code())) {
			throw new JeecgBootException(StatusEnum.OrdersStatusError);
		}
		
		boolean ok;
		if (orders.getOrderType().equals(OrderTypeEnum.SERVICE.code())) {
			// 服务订单
			ok = serviceEvaluationService.evaluation(uid, dto);
		} else {
			// 商品订单
			ok = goodsEvaluationService.evaluation(uid, dto);
		}
		if (ok) {
			// 更新订单已评价
			return updateOrderStatusById(orderId, OrderStatusEnum.EVALUATED.code());
		}
		return false;
	}
	
	@Override
	public boolean updateOrderStatusById(String orderId, Integer status) {
		return baseMapper.updateOrderStatusById(orderId, status);
	}
	
	@Override
	public boolean finished(String orderId) {
		String uid = SecurityUtils.getCurrentUserUid();
		Orders orders = getById(orderId);
		
		if (!orders.getUid().equals(uid) && !orders.getSellerId().equals(uid)) {
			// 不是买家也不是卖家
			throw new JeecgBootException(StatusEnum.OrdersOwnerError);
		}
		return updateOrderStatusById(orderId, OrderStatusEnum.OFFLINE_TRADING.code());
	}
	
	@Override
	public boolean updateTrackingNumber(String orderId, String trackingNumber) {
		return baseMapper.updateTrackingNumber(orderId, trackingNumber);
	}
}
