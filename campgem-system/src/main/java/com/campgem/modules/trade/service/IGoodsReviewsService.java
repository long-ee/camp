package com.campgem.modules.trade.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.trade.entity.GoodsReviews;
import com.campgem.modules.trade.vo.GoodsReviewsVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 商品留言
 * @Author: campgem
 * @Date:   2019-08-17
 * @Version: V1.0
 */
public interface IGoodsReviewsService extends IService<GoodsReviews> {
	
	IPage<GoodsReviewsVo> queryGoodsReviewsPageList(HttpServletRequest request, String goodsId, Integer pageNo, Integer pageSize);
}
