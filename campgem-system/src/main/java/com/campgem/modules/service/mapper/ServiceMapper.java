package com.campgem.modules.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.service.entity.Service;
import com.campgem.modules.service.vo.ServiceDetailVo;
import com.campgem.modules.service.vo.ServiceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 服务
 * @Author: campgem
 * @Date:   2019-08-20
 * @Version: V1.0
 */
public interface ServiceMapper extends BaseMapper<Service> {
	
	List<ServiceVo> queryPageList(@Param("categoryId") String categoryId,
	                              @Param("sort") Integer sort,
	                              @Param("start") Integer start,
	                              @Param("pageSize") Integer pageSize);
	
	ServiceDetailVo queryServiceDetail(@Param("serviceId") String serviceId);
}
