package com.campgem.modules.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.modules.trade.dto.RequirementsQueryDto;
import com.campgem.modules.trade.entity.Requirements;
import com.campgem.modules.trade.mapper.RequirementsMapper;
import com.campgem.modules.trade.service.IRequirementsService;
import com.campgem.modules.trade.vo.RequirementsVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 需求
 * @Author: campgem
 * @Date:   2019-08-17
 * @Version: V1.0
 */
@Service
public class RequirementsServiceImpl extends ServiceImpl<RequirementsMapper, Requirements> implements IRequirementsService {
	
	@Override
	public IPage<RequirementsVo> queryPageList(RequirementsQueryDto queryDto, Integer pageNo, Integer pageSize) {
		LambdaQueryWrapper<Requirements> query = new LambdaQueryWrapper<>();
		if (!queryDto.getCategoryId().equals("all")) {
			query.eq(Requirements::getCategoryId, queryDto.getCategoryId());
		}
		query.eq(Requirements::getDelFlag, 0);
		int count = baseMapper.selectCount(query);
		
		Integer start = (pageNo - 1) * pageSize;
		List<RequirementsVo> list = baseMapper.queryPageList(queryDto, start, pageSize);
		
		Page<RequirementsVo> page = new Page<>(pageNo, pageSize);
		page.setRecords(list);
		page.setTotal(count);
		return page;
	}
}
