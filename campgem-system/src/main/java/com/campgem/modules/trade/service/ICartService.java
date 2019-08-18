package com.campgem.modules.trade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.trade.entity.Cart;
import com.campgem.modules.trade.vo.GoodsCartVo;
import com.campgem.modules.trade.vo.GoodsOrderInfoVo;

import java.util.List;
import java.util.Map;

/**
 * @Description: 购物车
 * @Author: ling
 * @Date: 2019-08-16
 * @Version: V1.0
 */
public interface ICartService extends IService<Cart> {
	/**
	 * 移除购物车商品
	 */
	Boolean removeCart(String goodsId);
	
	/**
	 * 添加商品到购物车
	 */
	Boolean addGoods(String goodsId, String specId, Integer quantity);
	
	/**
	 * 购物车列表
	 * [返回商家名 -> 商家对应的商品列表]
	 */
	Map<String, List<GoodsCartVo>> queryCartList();
	
	Map<String, List<GoodsOrderInfoVo>> queryOrderInfo(String[] cartIds);
}
