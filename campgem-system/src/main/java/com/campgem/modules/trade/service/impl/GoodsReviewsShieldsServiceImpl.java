package com.campgem.modules.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.trade.entity.GoodsReviewsShields;
import com.campgem.modules.trade.mapper.GoodsReviewsShieldsMapper;
import com.campgem.modules.trade.service.IGoodsReviewsShieldsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 商品留言屏蔽
 * @Author: campgem
 * @Date:   2019-08-17
 * @Version: V1.0
 */
@Service
public class GoodsReviewsShieldsServiceImpl extends ServiceImpl<GoodsReviewsShieldsMapper, GoodsReviewsShields> implements IGoodsReviewsShieldsService {
	
	@Override
	public Boolean addUserReviewShield(String targetId) {
		String uid = SecurityUtils.getCurrentUserUid();
		if (uid.equals(targetId)) {
			throw new JeecgBootException("不能屏蔽自己");
		}
		Integer count = baseMapper.selectCount(new LambdaQueryWrapper<GoodsReviewsShields>().eq(GoodsReviewsShields::getUid, uid).eq(GoodsReviewsShields::getShieldUid, targetId));
		if (count > 0) {
			return true;
		}
		GoodsReviewsShields shields = new GoodsReviewsShields();
		shields.setCreateTime(new Date());
		shields.setUid(uid);
		shields.setShieldUid(targetId);
		return baseMapper.insert(shields) > 0;
	}
	
	@Override
	public List<String> queryUserShieldList() {
		String uid = SecurityUtils.getCurrentUserUid();
		List<GoodsReviewsShields> list = baseMapper.selectList(new LambdaQueryWrapper<GoodsReviewsShields>().eq(GoodsReviewsShields::getUid, uid));
		List<String> result = new ArrayList<>();
		for (GoodsReviewsShields shields : list) {
			result.add(shields.getUid());
		}
		return result;
	}
}
