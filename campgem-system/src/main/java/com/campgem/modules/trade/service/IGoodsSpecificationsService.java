package com.campgem.modules.trade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.trade.entity.GoodsSpecifications;

/**
 * @Description: 商品规格
 * @Author: ling
 * @Date:   2019-08-17
 * @Version: V1.0
 */
public interface IGoodsSpecificationsService extends IService<GoodsSpecifications> {
	
	/**
	 * 修改库存值
	 * @param type 1添加2减少
	 */
	void updateStock(Integer type, String id, Integer integer);
}
