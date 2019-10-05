package com.campgem.modules.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.modules.trade.entity.RequirementsReviews;
import com.campgem.modules.trade.entity.RequirementsReviewsShields;
import com.campgem.modules.trade.mapper.RequirementsReviewsMapper;
import com.campgem.modules.trade.service.IRequirementsReviewsService;
import com.campgem.modules.trade.service.IRequirementsReviewsShieldsService;
import com.campgem.modules.trade.vo.RequirementsReviewsVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 需求留言
 * @Author: campgem
 * @Date:   2019-08-18
 * @Version: V1.0
 */
@Service
public class RequirementsReviewsServiceImpl extends ServiceImpl<RequirementsReviewsMapper, RequirementsReviews> implements IRequirementsReviewsService {
	@Resource
	private IRequirementsReviewsShieldsService requirementsReviewsShieldsService;
	
	@Override
	public IPage<RequirementsReviewsVo> queryPageList(Page page, String requirementId) {
		// 过滤被屏蔽的用户
		List<RequirementsReviewsShields> shields = requirementsReviewsShieldsService.list(new LambdaQueryWrapper<RequirementsReviewsShields>()
				.eq(RequirementsReviewsShields::getRequirementId, requirementId));
		return baseMapper.queryPageList(page, requirementId, shields);
	}
}
