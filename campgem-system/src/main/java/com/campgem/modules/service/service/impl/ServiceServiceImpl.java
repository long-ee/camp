package com.campgem.modules.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.util.BeanConvertUtils;
import com.campgem.modules.service.dto.manage.MServiceQueryDto;
import com.campgem.modules.service.entity.Service;
import com.campgem.modules.service.entity.ServiceImages;
import com.campgem.modules.service.entity.enums.ServiceStatusEnum;
import com.campgem.modules.service.mapper.ServiceMapper;
import com.campgem.modules.service.service.IServiceImagesService;
import com.campgem.modules.service.service.IServiceService;
import com.campgem.modules.service.vo.*;
import com.campgem.modules.service.vo.manage.MServiceDetailVo;
import com.campgem.modules.service.vo.manage.MServiceListVo;
import com.campgem.modules.service.vo.manage.MServiceVo;
import com.campgem.modules.user.service.IMemberService;
import com.campgem.modules.user.vo.MemberVo;
import com.campgem.modules.user.vo.UserServiceListVo;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Description: 服务
 * @Author: campgem
 * @Date:   2019-08-20
 * @Version: V1.0
 */
@org.springframework.stereotype.Service
public class ServiceServiceImpl extends ServiceImpl<ServiceMapper, Service> implements IServiceService {
	@Resource
	private IServiceImagesService serviceImagesService;
	
	@Resource
	private IMemberService memberService;
	
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
			throw new JeecgBootException(StatusEnum.ServiceNotExistError);
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
	
	@Override
	public IPage<MServiceListVo> queryManagePageList(Page<MServiceQueryDto> page, MServiceQueryDto queryDto) {
		return baseMapper.queryManagePageList(page, queryDto);
	}
	
	@Override
	@Transactional
	public boolean saveOrUpdate(MServiceVo saveService, boolean isUpdate) {
		if (saveService.getServiceImages().length == 0) {
			throw new JeecgBootException(StatusEnum.ServiceImagesBlankError);
		}
		
		if (saveService.getServiceImages().length > 10) {
			throw new JeecgBootException(StatusEnum.ServiceImagesMaxError);
		}
		
		Service service = BeanConvertUtils.copy(saveService, Service.class);
		
		MemberVo member = memberService.getMemberByUserBaseId(service.getUid());
		service.setMemberName(member.getMemberName());
		service.setMemberType(member.getMemberType());
		
		String uuid;
		if (!isUpdate) {
			// 新增
			uuid = UUID.randomUUID().toString().replaceAll("-", "");
			service.setId(uuid);
			service.setDelFlag(0);
			service.setCreateTime(new Date());
			save(service);
		} else {
			uuid = service.getId();
			updateById(service);
			
			// 删除图片列表
			serviceImagesService.remove(new LambdaQueryWrapper<ServiceImages>().eq(ServiceImages::getServiceId, uuid));
		}
		
		for (ServiceImages image : saveService.getServiceImages()) {
			image.setServiceId(uuid);
		}
		
		// 添加
		serviceImagesService.saveBatch(Arrays.asList(saveService.getServiceImages()));
		
		return true;
	}
	
	@Override
	public MServiceDetailVo queryManageServiceDetail(String serviceId) {
		MServiceDetailVo detail = baseMapper.queryManageServiceDetail(serviceId);
		if (detail == null) {
			throw new JeecgBootException(StatusEnum.UnknownPaymentError);
		}
		return detail;
	}
	
	@Override
	public IPage<UserServiceListVo> queryPageList(Page page) {
		return baseMapper.queryPageList(page);
	}
	
	@Override
	public boolean updateStatusById(String serviceId, String status) {
		Service service = new Service();
		service.setId(serviceId);
		if ("ENABLE".equals(status.toUpperCase())) {
			service.setStatus(0);
		} else if ("DISABLE".equals(status.toUpperCase())) {
			service.setStatus(-1);
		} else {
			return false;
		}
		
		return updateById(service);
	}
}
