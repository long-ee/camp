package com.campgem.modules.trade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.modules.trade.dto.RequirementsQueryDto;
import com.campgem.modules.trade.dto.manage.MRequirementsQueryDto;
import com.campgem.modules.trade.entity.Requirements;
import com.campgem.modules.trade.vo.RequirementsDetailVo;
import com.campgem.modules.trade.vo.RequirementsVo;
import com.campgem.modules.trade.vo.manage.MRequirementsDetailVo;
import com.campgem.modules.trade.vo.manage.MRequirementsListVo;
import com.campgem.modules.user.vo.UserRequirementListVo;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 需求
 * @Author: ling
 * @Date: 2019-08-17
 * @Version: V1.0
 */
public interface RequirementsMapper extends BaseMapper<Requirements> {
	
	IPage<RequirementsVo> queryPageList(Page page,
	                                    @Param("query") RequirementsQueryDto queryDto);
	
	void incrementReviewCount(@Param("requirementId") String requirementId);
	
	/**
	 * 后台查询列表
	 */
	IPage<MRequirementsListVo> queryManagePageList(Page page, @Param("queryDto") MRequirementsQueryDto queryDto);
	
	/**
	 * 需求详情
	 */
	RequirementsDetailVo queryRequirementDetail(@Param("requirementId") String requirementId);
	
	MRequirementsDetailVo queryManageRequirementDetail(@Param("requirementId") String requirementId);
	
	IPage<UserRequirementListVo> queryUserPageList(Page page);
}
