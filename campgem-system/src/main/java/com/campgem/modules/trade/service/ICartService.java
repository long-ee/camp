package com.campgem.modules.trade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.trade.entity.Cart;
import com.campgem.modules.trade.vo.CartGoodsVo;
import com.campgem.modules.trade.vo.OrderInfoVo;

import java.util.List;

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
	 */
	List<CartGoodsVo> queryCartList();
	
	/**
	 * 订单信息
	 */
	List<OrderInfoVo> queryOrderInfo(String[] cartIds);
}
