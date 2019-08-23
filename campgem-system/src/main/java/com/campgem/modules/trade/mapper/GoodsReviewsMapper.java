package com.campgem.modules.trade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.modules.trade.entity.GoodsReviews;
import com.campgem.modules.trade.entity.GoodsReviewsShields;
import com.campgem.modules.trade.vo.GoodsReviewsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 商品留言
 * @Author: campgem
 * @Date:   2019-08-17
 * @Version: V1.0
 */
public interface GoodsReviewsMapper extends BaseMapper<GoodsReviews> {
	
	IPage<GoodsReviewsVo> queryPageList(Page page,
	                                    @Param("goodsId") String goodsId,
	                                    @Param("shields") List<GoodsReviewsShields> shields);
}
