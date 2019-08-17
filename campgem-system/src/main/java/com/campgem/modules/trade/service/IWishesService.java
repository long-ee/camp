package com.campgem.modules.trade.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.trade.entity.Wishes;
import com.campgem.modules.trade.vo.WishesVo;

/**
 * @Description: 心愿
 * @Author: campgem
 * @Date:   2019-08-18
 * @Version: V1.0
 */
public interface IWishesService extends IService<Wishes> {
	
	IPage<WishesVo> queryWishesPageList(Integer pageNo, Integer pageSize);
}
