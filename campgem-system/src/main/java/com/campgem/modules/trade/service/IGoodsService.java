package com.campgem.modules.trade.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.trade.dto.GoodsQueryDto;
import com.campgem.modules.trade.entity.Goods;
import com.campgem.modules.trade.vo.GoodsDetailVo;
import com.campgem.modules.trade.vo.GoodsListVo;

/**
 * @Description: 分类信息
 * @Author: ling
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface IGoodsService extends IService<Goods> {
	/**
	 * 根据条件分页查询分类分页列表
	 * @param queryDto
	 * @return
	 */
	IPage<GoodsListVo> queryPageList(Integer page, Integer pageSize, GoodsQueryDto queryDto);
	
	GoodsDetailVo queryGoodsDetail(String goodsId);
}