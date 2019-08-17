package com.campgem.modules.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.api.vo.LoginUserVo;
import com.campgem.modules.shiro.authc.ShiroRealm;
import com.campgem.modules.shiro.vo.DefContants;
import com.campgem.modules.trade.entity.GoodsReviews;
import com.campgem.modules.trade.mapper.GoodsReviewsMapper;
import com.campgem.modules.trade.service.IGoodsReviewsService;
import com.campgem.modules.trade.service.IGoodsReviewsShieldsService;
import com.campgem.modules.trade.vo.GoodsReviewsVo;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
	public IPage<GoodsReviewsVo> queryGoodsReviewsPageList(HttpServletRequest request, String goodsId, Integer pageNo, Integer pageSize) {
		// 查询屏蔽列表
		List<String> shields;
		try {
			// 判断是否登录
			String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
			LoginUserVo loginUserVo = shiroRealm.checkUserTokenIsEffect(token);
			shields = goodsReviewsShieldsService.queryUserShieldList(loginUserVo.getUid());
		} catch (AuthenticationException ex) {
			shields = new ArrayList<>();
		}
		
		
		LambdaQueryWrapper<GoodsReviews> query = new LambdaQueryWrapper<>();
		query.eq(GoodsReviews::getDelFlag, 0);
		query.eq(GoodsReviews::getGoodsId, goodsId);
		query.eq(GoodsReviews::getIsOpen, 1);
		if (shields.size() > 0) {
			query.notIn(GoodsReviews::getUid, shields);
		}
		Integer count = baseMapper.selectCount(query);
		
		int start = (pageNo - 1) * pageSize;
		List<GoodsReviewsVo> list = baseMapper.queryPageList(start, pageSize, goodsId, shields);
		
		Page<GoodsReviewsVo> page = new Page<>(pageNo, pageSize);
		page.setRecords(list);
		page.setTotal(count);
		return page;
	}
}
