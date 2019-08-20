package com.campgem.modules.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.service.entity.Service;
import com.campgem.modules.service.vo.ServiceVo;

/**
 * @Description: 服务
 * @Author: campgem
 * @Date:   2019-08-20
 * @Version: V1.0
 */
public interface IServiceService extends IService<Service> {
	
	IPage<ServiceVo> queryPageList(Integer pageNo, Integer pageSize, String categoryId, Integer sort);
}
