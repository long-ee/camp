package com.campgem.modules.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.modules.trade.dto.GoodsQueryDto;
import com.campgem.modules.trade.entity.Goods;
import com.campgem.modules.trade.mapper.GoodsMapper;
import com.campgem.modules.trade.service.IGoodsService;
import com.campgem.modules.trade.vo.GoodsDetailVo;
import com.campgem.modules.trade.vo.GoodsListVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
		return goods;
	}
}
