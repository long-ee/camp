package com.campgem.modules.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.trade.entity.Cart;
import com.campgem.modules.trade.entity.Goods;
import com.campgem.modules.trade.entity.GoodsSpecifications;
import com.campgem.modules.trade.entity.enums.IdentityEnum;
import com.campgem.modules.trade.mapper.CartMapper;
import com.campgem.modules.trade.service.ICartService;
import com.campgem.modules.trade.service.IGoodsService;
import com.campgem.modules.trade.service.IGoodsSpecificationsService;
import com.campgem.modules.trade.vo.GoodsCartVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Description: 购物车
 * @Author: ling
 * @Date: 2019-08-16
 * @Version: V1.0
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {
	
	@Resource
	private IGoodsService goodsService;
	@Resource
	private IGoodsSpecificationsService goodsSpecificationsService;
	
	@Override
	public Boolean removeCart(String cartId) {
		String uid = SecurityUtils.getCurrentUserUid();
		return baseMapper.delete(new LambdaQueryWrapper<Cart>().eq(Cart::getMid, uid).eq(Cart::getId, cartId)) > 0;
	}
	
	@Override
	public Boolean addGoods(String goodsId, String specId, Integer quantity) {
		String uid = SecurityUtils.getCurrentUserUid();
		// 查询是否已添加了同样的商品
		LambdaQueryWrapper<Cart> query = new LambdaQueryWrapper<Cart>().eq(Cart::getMid, uid).eq(Cart::getGoodsId, goodsId);
		Integer count = baseMapper.selectCount(query);
		
		Goods goods = goodsService.getById(goodsId);
		if (goods == null) {
			throw new JeecgBootException("商品不存在");
		}
		
		if (goods.getIdentity().equals(IdentityEnum.BUSINESS.code())) {
			// 商家，必须要有规格
			if (StringUtils.isEmpty(specId)) {
				throw new JeecgBootException("规格不能为空");
			}
			
			// 检查规格是否是对应商品的
			GoodsSpecifications specifications = goodsSpecificationsService.getById(specId);
			if (specifications == null) {
				throw new JeecgBootException("规格不存在");
			}
			if (!specifications.getGoodsId().equals(goodsId)) {
				throw new JeecgBootException("规格与商品不匹配");
			}
		}
		
		if (count > 0) {
			if (goods.getIdentity().equals(IdentityEnum.STUDENT_INDIVIDUAL.code())) {
				// 对于Identity=“Student”/"Individual"的商品，购买数量最多为1，重复添加同一商品不增加购买数量；此处直接返回
				return true;
			} else {
				// 对于Identity=“Business"的商品，展示选择规格时录入的数量，重复添加则叠加购买数量
				Cart cart = new Cart();
				cart.setQuantity(count + 1);
				return baseMapper.update(cart, query) > 0;
			}
		} else {
			// 购物车没有此商品，添加
			Cart cart = new Cart();
			cart.setMid(uid);
			cart.setGoodsId(goodsId);
			cart.setSellerId(goods.getMid());
			cart.setSpecificationsId(specId);
			cart.setQuantity(quantity);
			cart.setSellerName(goods.getSellerName());
			cart.setCreateTime(new Date());
			return baseMapper.insert(cart) > 0;
		}
	}
	
	@Override
	public Map<String, List<GoodsCartVo>> queryCartList() {
		String uid = SecurityUtils.getCurrentUserUid();
		List<GoodsCartVo> carts = baseMapper.queryCartList(uid);
		
		Map<String, List<GoodsCartVo>> result = new HashMap<>();
		for (GoodsCartVo cart : carts) {
			if (result.containsKey(cart.getSellerName())) {
				result.get(cart.getSellerName()).add(cart);
			} else {
				List<GoodsCartVo> list = new ArrayList<>();
				list.add(cart);
				result.put(cart.getSellerName(), list);
			}
		}
		
		return result;
	}
}