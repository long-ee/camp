package com.campgem.modules.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.util.BeanConvertUtils;
import com.campgem.modules.trade.dto.GoodsQueryDto;
import com.campgem.modules.trade.dto.OrderInfoDto;
import com.campgem.modules.trade.entity.Goods;
import com.campgem.modules.trade.entity.enums.IdentityEnum;
import com.campgem.modules.trade.mapper.GoodsMapper;
import com.campgem.modules.trade.service.IGoodsService;
import com.campgem.modules.trade.vo.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 分类信息
 * @Author: ling
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
	
	@Override
	public IPage<GoodsListVo> queryPageList(Integer pageNo, Integer pageSize, GoodsQueryDto queryDto) {
		LambdaQueryWrapper<Goods> query = new LambdaQueryWrapper<>();
		query.eq(Goods::getDelFlag, 0);
		if (!"all".equals(queryDto.getCategoryId())) {
			query.eq(Goods::getCategoryId, queryDto.getCategoryId());
		}
		if (queryDto.getQuality() != 0) {
			query.eq(Goods::getQuality, queryDto.getQuality());
		}
		Integer count = baseMapper.selectCount(query);
		
		Integer start = (pageNo - 1) * pageSize;
		List<GoodsListVo> list = baseMapper.queryPageList(start, pageSize, queryDto);
		
		Page<GoodsListVo> page = new Page<>(pageNo, pageSize);
		page.setRecords(list);
		page.setTotal(count);
		return page;
	}
	
	@Override
	public GoodsDetailVo queryGoodsDetail(String goodsId) {
		GoodsDetailVo goods = baseMapper.queryGoodsDetail(goodsId);
		if (goods == null) {
			throw new JeecgBootException("商品不存在");
		}
		
		// 关联商品查询
		List<GoodsRelativeVo> relatives = baseMapper.queryGoodsRelative(goods.getCategoryId(), goodsId);
		goods.setRelatives(relatives);
		
		return goods;
	}
	
	@Override
	public void increment(String goodsId) {
		baseMapper.incrementReviewCount(goodsId);
	}
	
	@Override
	public List<OrderInfoVo> queryOrderInfo(OrderInfoDto orderInfoDto) {
		OrderInfoTempVo goodsVo = baseMapper.queryOrderInfo(orderInfoDto.getGoodsId(), orderInfoDto.getSpecificationId());
		if (goodsVo == null) {
			throw new JeecgBootException("商品不存在");
		}
		if (goodsVo.getIdentity().equals(IdentityEnum.BUSINESS.code())) {
			// 商家，必须要有规格
			if (orderInfoDto.getSpecificationId() == null) {
				throw new JeecgBootException("缺少规格");
			} else {
				// 设置规格数据
				if (StringUtils.isEmpty(goodsVo.getSpecification())) {
					throw new JeecgBootException("规格错误");
				}
				String[] spec = goodsVo.getSpecification().split(",");
				goodsVo.setSalePrice(new BigDecimal(spec[0]));
				goodsVo.setSpecificationName(spec[1]);
				goodsVo.setSpecification(null);
			}
		}
		
		goodsVo.setQuantity(orderInfoDto.getQuantity());
		
		List<OrderInfoVo> list = new ArrayList<>();
		OrderInfoVo infoVo = new OrderInfoVo();
		infoVo.setSellerId(goodsVo.getSellerId());
		infoVo.setSellerName(goodsVo.getSellerName());
		infoVo.setSellerIdentity(goodsVo.getIdentity());
		infoVo.setShippingMethods(goodsVo.getShippingMethods());
		
		infoVo.setGoods(new ArrayList<OrderInfoVo.SellerGoods> () {{
			add(BeanConvertUtils.copy(goodsVo, OrderInfoVo.SellerGoods.class));
		}});
		
		return list;
	}
}
