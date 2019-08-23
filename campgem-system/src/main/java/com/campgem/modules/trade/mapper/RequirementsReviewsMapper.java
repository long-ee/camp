package com.campgem.modules.trade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.modules.trade.entity.RequirementsReviews;
import com.campgem.modules.trade.entity.RequirementsReviewsShields;
import com.campgem.modules.trade.vo.RequirementsReviewsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 需求留言
 * @Author: campgem
 * @Date: 2019-08-18
 * @Version: V1.0
 */
public interface RequirementsReviewsMapper extends BaseMapper<RequirementsReviews> {
	
	IPage<RequirementsReviewsVo> queryPageList(Page page,
	                                           @Param("requirementId") String requirementId,
	                                           @Param("shields") List<RequirementsReviewsShields> shields);
}
