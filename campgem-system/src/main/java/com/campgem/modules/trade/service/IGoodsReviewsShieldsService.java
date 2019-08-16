package com.campgem.modules.trade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.trade.entity.GoodsReviewsShields;

import java.util.List;

/**
 * @Description: 商品留言屏蔽
 * @Author: campgem
 * @Date:   2019-08-17
 * @Version: V1.0
 */
public interface IGoodsReviewsShieldsService extends IService<GoodsReviewsShields> {
	
	Boolean addUserReviewShield(String targetId);
	
	List<String> queryUserShieldList();
}
