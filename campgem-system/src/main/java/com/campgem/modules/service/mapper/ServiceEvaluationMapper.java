package com.campgem.modules.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.service.entity.ServiceEvaluation;
import com.campgem.modules.service.vo.ServiceEvaluationVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 服务评价
 * @Author: campgem
 * @Date:   2019-08-20
 * @Version: V1.0
 */
public interface ServiceEvaluationMapper extends BaseMapper<ServiceEvaluation> {
	
	List<ServiceEvaluationVo> queryServiceEvaluationPageList(@Param("serviceId") String serviceId,
	                                                         @Param("start") Integer start,
	                                                         @Param("pageSize") Integer pageSize);
}
