package com.campgem.modules.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.service.entity.Service;
import com.campgem.modules.service.vo.BusinessServiceVo;
import com.campgem.modules.service.vo.ServiceDetailVo;
import com.campgem.modules.service.vo.ServiceOrderVo;
import com.campgem.modules.service.vo.ServiceVo;

import java.util.List;

/**
 * @Description: 服务
 * @Author: campgem
 * @Date:   2019-08-20
 * @Version: V1.0
 */
public interface IServiceService extends IService<Service> {
	
	List<ServiceVo> queryServiceListByCategory(String categoryId);
	
	IPage<ServiceVo> queryServicePageList(Page page, String categoryId, Integer sort);
	
	ServiceDetailVo queryServiceDetail(String serviceId);
	
	IPage<BusinessServiceVo> queryBusinessServicePageList(String businessId, Integer pageNo, Integer pageSize);
	
	ServiceOrderVo queryServiceOrder(String serviceId);
}
