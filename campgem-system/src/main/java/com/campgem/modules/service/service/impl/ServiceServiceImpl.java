package com.campgem.modules.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.modules.service.entity.Service;
import com.campgem.modules.service.entity.enums.ServiceStatusEnum;
import com.campgem.modules.service.mapper.ServiceMapper;
import com.campgem.modules.service.service.IServiceService;
import com.campgem.modules.service.vo.*;

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
	public List<ServiceVo> queryServiceListByCategory(String categoryId) {
		return baseMapper.queryServiceListByCategory(categoryId);
	}
	
	@Override
	public IPage<ServiceVo> queryServicePageList(Page page, String categoryId, Integer sort) {
		return baseMapper.queryServicePageList(page, categoryId, sort);
	}
	
	@Override
	public ServiceDetailVo queryServiceDetail(String serviceId) {
		ServiceDetailVo detail = baseMapper.queryServiceDetail(serviceId);
		if (detail == null) {
			throw new JeecgBootException("服务不存在");
		}
		
		// 关联服务
		List<ServiceRelatedVo> list = baseMapper.queryServiceRelated(detail.getCategoryId(), serviceId);
		detail.setRelatedServices(list);
		
		return detail;
	}
	
	@Override
	public IPage<BusinessServiceVo> queryBusinessServicePageList(String businessId, Integer pageNo, Integer pageSize) {
		LambdaQueryWrapper<Service> query = new LambdaQueryWrapper<>();
		query.eq(Service::getDelFlag, 0);
		query.eq(Service::getStatus, ServiceStatusEnum.ENABLE.code());
		query.eq(Service::getUid, businessId);
		
		int count = baseMapper.selectCount(query);
		
		Integer start = (pageNo - 1) * pageSize;
		List<BusinessServiceVo> list = baseMapper.queryBusinessServicePageList(businessId, start, pageSize);
		
		Page<BusinessServiceVo> page = new Page<>(pageNo, pageSize);
		page.setRecords(list);
		page.setTotal(count);
		return page;
	}
	
	@Override
	public ServiceOrderVo queryServiceOrder(String serviceId) {
		return baseMapper.queryServiceOrder(serviceId);
	}
}
