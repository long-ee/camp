package com.campgem.modules.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.util.BeanConvertUtils;
import com.campgem.modules.service.entity.ServiceEvaluation;
import com.campgem.modules.service.mapper.ServiceEvaluationMapper;
import com.campgem.modules.service.service.IServiceEvaluationService;
import com.campgem.modules.service.vo.ServiceEvaluationVo;
import com.campgem.modules.user.dto.OrdersEvaluationDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description: 服务评价
 * @Author: campgem
 * @Date:   2019-08-20
 * @Version: V1.0
 */
@Service
public class ServiceEvaluationServiceImpl extends ServiceImpl<ServiceEvaluationMapper, ServiceEvaluation> implements IServiceEvaluationService {
	
	@Override
	public IPage<ServiceEvaluationVo> queryServiceEvaluationPageList(String serviceId, Integer pageNo, Integer pageSize) {
		LambdaQueryWrapper<ServiceEvaluation> query = new LambdaQueryWrapper<>();
		query.eq(ServiceEvaluation::getServiceId, serviceId);
		int count = baseMapper.selectCount(query);
		
		int start = (pageNo - 1) * pageSize;
		List<ServiceEvaluationVo> list = baseMapper.queryServiceEvaluationPageList(serviceId, start, pageSize);
		
		Page<ServiceEvaluationVo> page = new Page<>(pageNo, pageSize);
		page.setRecords(list);
		page.setTotal(count);
		
		return page;
	}
	
	@Override
	public boolean evaluation(String uid, OrdersEvaluationDto dto) {
		ServiceEvaluation serviceEvaluation = BeanConvertUtils.copy(dto, ServiceEvaluation.class);
		
		serviceEvaluation.setUid(uid);
		serviceEvaluation.setCreateTime(new Date());
		serviceEvaluation.setDelFlag(0);
		
		return save(serviceEvaluation);
	}
}
