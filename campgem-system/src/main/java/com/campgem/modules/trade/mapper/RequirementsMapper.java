package com.campgem.modules.trade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.modules.trade.dto.RequirementsQueryDto;
import com.campgem.modules.trade.dto.manage.MRequirementsQueryDto;
import com.campgem.modules.trade.entity.Requirements;
import com.campgem.modules.trade.vo.RequirementsDetailVo;
import com.campgem.modules.trade.vo.RequirementsVo;
import com.campgem.modules.trade.vo.manage.MRequirementsListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 需求
 * @Author: campgem
 * @Date:   2019-08-17
 * @Version: V1.0
 */
public interface RequirementsMapper extends BaseMapper<Requirements> {
	
	List<RequirementsVo> queryPageList(@Param("query") RequirementsQueryDto queryDto,
	                                   @Param("start") Integer start,
	                                   @Param("pageSize") Integer pageSize);
	
	void incrementReviewCount(@Param("requirementId") String requirementId);
	
	/**
	 * 后台查询列表
	 */
	IPage<MRequirementsListVo> queryManagePageList(Page page, @Param("queryDto") MRequirementsQueryDto queryDto);
	
	/**
	 * 需求详情
	 */
	RequirementsDetailVo queryRequirementDetail(@Param("requirementId") String requirementId);
}
