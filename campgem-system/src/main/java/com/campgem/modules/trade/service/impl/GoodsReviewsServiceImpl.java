package com.campgem.modules.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.modules.shiro.authc.ShiroRealm;
import com.campgem.modules.trade.entity.GoodsReviews;
import com.campgem.modules.trade.entity.GoodsReviewsShields;
import com.campgem.modules.trade.mapper.GoodsReviewsMapper;
import com.campgem.modules.trade.service.IGoodsReviewsService;
import com.campgem.modules.trade.service.IGoodsReviewsShieldsService;
import com.campgem.modules.trade.vo.GoodsReviewsVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 商品留言
 * @Author: campgem
 * @Date: 2019-08-17
 * @Version: V1.0
 */
@Service
public class GoodsReviewsServiceImpl extends ServiceImpl<GoodsReviewsMapper, GoodsReviews> implements IGoodsReviewsService {
	
	@Resource
	private IGoodsReviewsShieldsService goodsReviewsShieldsService;
	
	@Resource
	private ShiroRealm shiroRealm;
	
	@Override
	public IPage<GoodsReviewsVo> queryGoodsReviewsPageList(Page page, String goodsId) {
		// 查询屏蔽列表
		List<GoodsReviewsShields> shields = goodsReviewsShieldsService.list(new LambdaQueryWrapper<GoodsReviewsShields>()
				.eq(GoodsReviewsShields::getGoodsId, goodsId));
		
		return baseMapper.queryPageList(page, goodsId, shields);
	}
}
