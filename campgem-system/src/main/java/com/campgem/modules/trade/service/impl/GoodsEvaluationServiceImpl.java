package com.campgem.modules.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.modules.trade.entity.GoodsEvaluation;
import com.campgem.modules.trade.mapper.GoodsEvaluationMapper;
import com.campgem.modules.trade.service.IGoodsEvaluationService;
import com.campgem.modules.trade.vo.GoodsEvaluationVo;
import com.campgem.modules.user.dto.OrdersEvaluationDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 商品评价
 * @Author: campgem
 * @Date:   2019-08-17
 * @Version: V1.0
 */
@Service
public class GoodsEvaluationServiceImpl extends ServiceImpl<GoodsEvaluationMapper, GoodsEvaluation> implements IGoodsEvaluationService {
	
	@Override
	public IPage<GoodsEvaluationVo> queryGoodsEvaluationPageList(String goodsId, Integer pageNo, Integer pageSize) {
		LambdaQueryWrapper<GoodsEvaluation> query = new LambdaQueryWrapper<>();
		query.eq(GoodsEvaluation::getGoodsId, goodsId);
		int count = baseMapper.selectCount(query);
		
		int start = (pageNo - 1) * pageSize;
		List<GoodsEvaluationVo> list = baseMapper.queryGoodsEvaluationPageList(goodsId, start, pageSize);
		
		Page<GoodsEvaluationVo> page = new Page<>(pageNo, pageSize);
		page.setRecords(list);
		page.setTotal(count);
		
		return page;
	}
	
	@Override
	@Transactional
	public boolean evaluation(String uid, OrdersEvaluationDto dto) {
		if (dto.getGoodsEvaluations() == null || dto.getGoodsEvaluations().length == 0) {
			throw new JeecgBootException(StatusEnum.GoodsEvaluationNotEmptyError);
		}
		
		Date createTime = new Date();
		List<GoodsEvaluation> saves = new ArrayList<>();
		for (OrdersEvaluationDto.GoodsEvaluationDto goodsDto : dto.getGoodsEvaluations()) {
			GoodsEvaluation goodsEvaluation = new GoodsEvaluation();
			goodsEvaluation.setUid(uid);
			goodsEvaluation.setGoodsId(goodsDto.getGoodsId());
			goodsEvaluation.setContent(goodsDto.getContent());
			goodsEvaluation.setDelFlag(0);
			goodsEvaluation.setCreateTime(createTime);
			goodsEvaluation.setRating(goodsDto.getRating());
			
			saves.add(goodsEvaluation);
		}
		
		return saveBatch(saves);
	}
}
