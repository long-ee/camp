package com.campgem.modules.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.service.entity.Service;
import com.campgem.modules.service.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 服务
 * @Author: campgem
 * @Date:   2019-08-20
 * @Version: V1.0
 */
public interface ServiceMapper extends BaseMapper<Service> {
	List<ServiceVo> queryServiceListByCategory(@Param("categoryId") String categoryId);
	
	List<ServiceVo> queryServicePageList(@Param("categoryId") String categoryId,
	                                     @Param("sort") Integer sort,
	                                     @Param("start") Integer start,
	                                     @Param("pageSize") Integer pageSize);
	
	ServiceDetailVo queryServiceDetail(@Param("serviceId") String serviceId);
	
	/**
	 * 关联服务，跟服务类型一样，排除当前服务，并且只有5条
	 */
	List<ServiceRelatedVo> queryServiceRelated(@Param("categoryId") String categoryId,
	                                           @Param("serviceId") String serviceId);
	
	List<BusinessServiceVo> queryBusinessServicePageList(@Param("uid") String uid,
	                                                     @Param("start") Integer start,
	                                                     @Param("pageSize") Integer pageSize);
	
	ServiceOrderVo queryServiceOrder(@Param("serviceId") String serviceId);
}
