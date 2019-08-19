package com.campgem.modules.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.util.BeanConvertUtils;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.trade.entity.Cart;
import com.campgem.modules.trade.entity.Goods;
import com.campgem.modules.trade.entity.GoodsSpecifications;
import com.campgem.modules.trade.entity.enums.IdentityEnum;
import com.campgem.modules.trade.mapper.CartMapper;
import com.campgem.modules.trade.service.ICartService;
import com.campgem.modules.trade.service.IGoodsService;
import com.campgem.modules.trade.service.IGoodsSpecificationsService;
import com.campgem.modules.trade.vo.CartGoodsTempVo;
import com.campgem.modules.trade.vo.CartGoodsVo;
import com.campgem.modules.trade.vo.OrderInfoTempVo;
import com.campgem.modules.trade.vo.OrderInfoVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
		return baseMapper.delete(new LambdaQueryWrapper<Cart>().eq(Cart::getUid, uid).eq(Cart::getId, cartId)) > 0;
	}
	
	@Override
	public Boolean addGoods(String goodsId, String specId, Integer quantity) {
		String uid = SecurityUtils.getCurrentUserUid();
		// 查询是否已添加了同样的商品
		LambdaQueryWrapper<Cart> query = new LambdaQueryWrapper<Cart>().eq(Cart::getUid, uid).eq(Cart::getGoodsId, goodsId);
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
			cart.setUid(uid);
			cart.setGoodsId(goodsId);
			cart.setSellerId(goods.getUid());
			cart.setSpecificationsId(specId);
			cart.setQuantity(quantity);
			cart.setSellerName(goods.getSellerName());
			cart.setCreateTime(new Date());
			return baseMapper.insert(cart) > 0;
		}
	}
	
	@Override
	public List<CartGoodsVo> queryCartList() {
		String uid = SecurityUtils.getCurrentUserUid();
		List<CartGoodsTempVo> carts = baseMapper.queryCartList(uid);
		
		Map<String, List<CartGoodsTempVo>> map = new HashMap<>();
		for (CartGoodsTempVo cart : carts) {
			if (map.containsKey(cart.getSellerName())) {
				map.get(cart.getSellerName()).add(cart);
			} else {
				List<CartGoodsTempVo> list = new ArrayList<>();
				list.add(cart);
				map.put(cart.getSellerName(), list);
			}
		}
		
		List<CartGoodsVo> list = new ArrayList<>();
		for (String key : map.keySet()) {
			List<CartGoodsTempVo> tempVos = map.get(key);
			
			CartGoodsVo cartGoodsVo = new CartGoodsVo();
			cartGoodsVo.setSellerName(tempVos.get(0).getSellerName());
			cartGoodsVo.setGoods(BeanConvertUtils.copyList(tempVos, CartGoodsVo.Goods.class));
			
			list.add(cartGoodsVo);
		}
		
		return list;
	}
	
	@Override
	public List<OrderInfoVo> queryOrderInfo(String[] cartIds) {
		List<OrderInfoTempVo> infos = baseMapper.queryOrderInfo(SecurityUtils.getCurrentUserUid(), cartIds);
		Map<String, List<OrderInfoTempVo>> map = new HashMap<>();
		for (OrderInfoTempVo info : infos) {
			// 处理规格和价格
			if (info.getSpecification().contains(",")) {
				// 有规格数据
				String[] spec = info.getSpecification().split(",");
				info.setSalePrice(new BigDecimal(spec[0]));
				info.setSpecificationName(spec[1]);
			} else {
				// 没有，直接设置价格
				info.setSalePrice(new BigDecimal(info.getSpecification()));
			}
			info.setSpecification(null);
			if (!map.containsKey(info.getSellerName())) {
				List<OrderInfoTempVo> list = new ArrayList<>();
				list.add(info);
				map.put(info.getSellerName(), list);
			} else {
				map.get(info.getSellerName()).add(info);
			}
		}
		
		List<OrderInfoVo> v2s = new ArrayList<>();
		for (String key : map.keySet()) {
			List<OrderInfoTempVo> tempVos = map.get(key);
			OrderInfoVo v2 = new OrderInfoVo();
			v2.setSellerId(tempVos.get(0).getSellerId());
			v2.setSellerName(tempVos.get(0).getSellerName());
			v2.setSellerIdentity(tempVos.get(0).getIdentity());
			v2.setShippingMethods(tempVos.get(0).getShippingMethods());
			v2.setGoods(BeanConvertUtils.copyList(tempVos, OrderInfoVo.SellerGoods.class));
			
			v2s.add(v2);
		}
		
		return v2s;
	}
	
	public OrderInfoVo queryOrderInfoV2(String[] cartIds) {
		return null;
	}
}
