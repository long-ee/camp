package com.campgem.modules.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.trade.entity.Wishes;
import com.campgem.modules.trade.mapper.WishesMapper;
import com.campgem.modules.trade.service.IWishesService;
import com.campgem.modules.trade.vo.WishesVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 心愿
 * @Author: campgem
 * @Date:   2019-08-18
 * @Version: V1.0
 */
@Service
public class WishesServiceImpl extends ServiceImpl<WishesMapper, Wishes> implements IWishesService {
	
	@Override
	public IPage<WishesVo> queryWishesPageList(Integer pageNo, Integer pageSize) {
		LambdaQueryWrapper<Wishes> query = new LambdaQueryWrapper<>();
		query.eq(Wishes::getUid, SecurityUtils.getCurrentUserUid());
		query.eq(Wishes::getDelFlag, 0);
		int count = baseMapper.selectCount(query);
		
		Integer start = (pageNo - 1) * pageSize;
		List<WishesVo> list = baseMapper.queryWishesPageList(SecurityUtils.getCurrentUserUid(), start, pageSize);
		Page<WishesVo> page = new Page<>(pageNo, pageSize);
		page.setRecords(list);
		page.setTotal(count);
		
		return page;
	}
}
