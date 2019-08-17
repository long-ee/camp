package com.campgem.modules.trade.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.trade.dto.RequirementsQueryDto;
import com.campgem.modules.trade.entity.Requirements;
import com.campgem.modules.trade.vo.RequirementsVo;

/**
 * @Description: 需求
 * @Author: campgem
 * @Date:   2019-08-17
 * @Version: V1.0
 */
public interface IRequirementsService extends IService<Requirements> {
	
	IPage<RequirementsVo> queryPageList(RequirementsQueryDto queryDto, Integer pageNo, Integer pageSize);
	
	void incrementReviewCount(String requirementId);
}
