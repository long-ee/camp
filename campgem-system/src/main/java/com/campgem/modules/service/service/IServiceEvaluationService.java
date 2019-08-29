package com.campgem.modules.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.service.entity.ServiceEvaluation;
import com.campgem.modules.service.vo.ServiceEvaluationVo;
import com.campgem.modules.user.dto.OrdersEvaluationDto;

/**
 * @Description: 服务评价
 * @Author: campgem
 * @Date:   2019-08-20
 * @Version: V1.0
 */
public interface IServiceEvaluationService extends IService<ServiceEvaluation> {
	
	IPage<ServiceEvaluationVo> queryServiceEvaluationPageList(String serviceId, Integer pageNo, Integer pageSize);
	
	boolean evaluation(String uid, OrdersEvaluationDto dto);
}
