package com.campgem.modules.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.service.entity.ServiceImages;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 服务图片
 * @Author: ling
 * @Date: 2019-08-20
 * @Version: V1.0
 */
public interface ServiceImagesMapper extends BaseMapper<ServiceImages> {
	List<ServiceImages> queryServiceImages(@Param("serviceId") String serviceId);
}
