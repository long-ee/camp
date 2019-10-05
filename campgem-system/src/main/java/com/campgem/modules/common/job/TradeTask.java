package com.campgem.modules.common.job;

import com.campgem.modules.order.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 定时任务
 */
@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
@Slf4j
public class TradeTask {
	
	@Resource
	private IOrderService orderService;
	
	/**
	 * 每6个小时执行一次，统一检查订单状态
	 */
	@Async
	@Scheduled(cron = "0 0 */6 * * ?")
	public void checkOrderStatus() {
		orderService.checkOrderStatus();
	}
}