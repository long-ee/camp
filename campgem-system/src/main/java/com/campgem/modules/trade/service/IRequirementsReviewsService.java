package com.campgem.modules.trade.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.trade.entity.RequirementsReviews;
import com.campgem.modules.trade.vo.RequirementsReviewsVo;

/**
 * @Description: 需求留言
 * @Author: campgem
 * @Date:   2019-08-18
 * @Version: V1.0
 */
public interface IRequirementsReviewsService extends IService<RequirementsReviews> {
	
	IPage<RequirementsReviewsVo> queryPageList(Page page, String requirementId);
}
