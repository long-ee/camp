package com.campgem.modules.trade.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.trade.dto.RequirementsQueryDto;
import com.campgem.modules.trade.dto.manage.MRequirementsQueryDto;
import com.campgem.modules.trade.entity.Requirements;
import com.campgem.modules.trade.vo.RequirementsDetailVo;
import com.campgem.modules.trade.vo.RequirementsVo;
import com.campgem.modules.trade.vo.manage.MRequirementsDetailVo;
import com.campgem.modules.trade.vo.manage.MRequirementsListVo;
import com.campgem.modules.trade.vo.manage.MRequirementsVo;
import com.campgem.modules.user.vo.UserRequirementListVo;

/**
 * @Description: 需求
 * @Author: campgem
 * @Date:   2019-08-17
 * @Version: V1.0
 */
public interface IRequirementsService extends IService<Requirements> {
	
	IPage<RequirementsVo> queryPageList(Page<RequirementsQueryDto> page, RequirementsQueryDto queryDto);
	
	void incrementReviewCount(String requirementId);
	
	IPage<MRequirementsListVo> queryManagePageList(Page<MRequirementsListVo> page, MRequirementsQueryDto queryDto);
	
	boolean save(MRequirementsVo requirements);
	
	boolean update(MRequirementsVo updateRequirements);
	
	RequirementsDetailVo queryRequirementDetail(String requirementId);
	
	MRequirementsDetailVo queryManageRequirementDetail(String id);
	
	IPage<UserRequirementListVo> queryPageList(Page page);
	
	boolean updateStatusById(String requirementId, String status);
}
