package com.campgem.modules.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.modules.service.entity.Service;
import com.campgem.modules.service.entity.enums.ServiceStatusEnum;
import com.campgem.modules.service.mapper.ServiceMapper;
import com.campgem.modules.service.service.IServiceService;
import com.campgem.modules.service.vo.ServiceVo;

import java.util.List;

/**
 * @Description: 服务
 * @Author: campgem
 * @Date:   2019-08-20
 * @Version: V1.0
 */
@org.springframework.stereotype.Service
public class ServiceServiceImpl extends ServiceImpl<ServiceMapper, Service> implements IServiceService {
	
	@Override
	public IPage<ServiceVo> queryPageList(Integer pageNo, Integer pageSize, String categoryId, Integer sort) {
		LambdaQueryWrapper<Service> query = new LambdaQueryWrapper<>();
		if (!categoryId.equals("all")) {
			query.eq(Service::getCategoryId, categoryId);
		}
		query.eq(Service::getDelFlag, 0);
		query.eq(Service::getStatus, ServiceStatusEnum.ENABLE.code());
		
		int count = baseMapper.selectCount(query);
		
		Integer start = (pageNo - 1) * pageSize;
		List<ServiceVo> list = baseMapper.queryPageList(categoryId, sort, start, pageSize);
		
		Page<ServiceVo> page = new Page<>(pageNo, pageSize);
		page.setRecords(list);
		page.setTotal(count);
		return page;
	}
}
