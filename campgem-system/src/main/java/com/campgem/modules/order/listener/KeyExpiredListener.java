package com.campgem.modules.order.listener;

import com.campgem.modules.order.service.IOrderService;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class KeyExpiredListener extends KeyExpirationEventMessageListener {
	
	@Resource
	private IOrderService orderService;
	
	public KeyExpiredListener(RedisMessageListenerContainer listenerContainer) {
		super(listenerContainer);
	}
	
	@Override
	public void onMessage(Message message, byte[] pattern) {
		// 有过期的订单，body就是订单号
		String orderId = new String(message.getBody());
		orderService.checkOrderStatusById(orderId);
	}
}
