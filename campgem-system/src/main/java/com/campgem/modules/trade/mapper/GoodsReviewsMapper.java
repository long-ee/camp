package com.campgem.modules.trade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.trade.entity.GoodsReviews;
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
	
	List<GoodsReviewsVo> queryPageList(@Param("start") Integer start, @Param("pageSize") Integer pageSize,
	                                   @Param("goodsId") String goodsId, @Param("shields") List<String> shields);
}
