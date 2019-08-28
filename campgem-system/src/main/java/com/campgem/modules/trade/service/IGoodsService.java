package com.campgem.modules.trade.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.trade.dto.GoodsQueryDto;
import com.campgem.modules.trade.dto.OrderInfoDto;
import com.campgem.modules.trade.dto.manage.MGoodsQueryDto;
import com.campgem.modules.trade.entity.Goods;
import com.campgem.modules.trade.vo.GoodsDetailVo;
import com.campgem.modules.trade.vo.GoodsListVo;
import com.campgem.modules.trade.vo.OrderInfoVo;
import com.campgem.modules.trade.vo.manage.MGoodsListVo;
import com.campgem.modules.trade.vo.manage.MGoodsVo;
import com.campgem.modules.user.vo.UserGoodsDetailVo;
import com.campgem.modules.user.vo.UserGoodsListVo;
import com.campgem.modules.user.vo.UserGoodsVo;

import java.util.List;

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
	IPage<GoodsListVo> queryPageList(Page<GoodsQueryDto> page, GoodsQueryDto queryDto);
	
	GoodsDetailVo queryGoodsDetail(String goodsId);
	
	/**
	 * 商品详情，是否要关联商品
	 */
	GoodsDetailVo queryGoodsDetail(String goodsId, boolean isRelated);
	
	void increment(String goodsId);
	
	List<OrderInfoVo> queryOrderInfo(OrderInfoDto orderInfoDto);
	
	IPage<MGoodsListVo> queryPageList(Page<MGoodsQueryDto> page, MGoodsQueryDto queryDto);
	
	boolean save(MGoodsVo goods);
	
	boolean update(MGoodsVo updateGoods);
	
	IPage<UserGoodsListVo> queryPageList(Page page);
	
	/**
	 * 新增/编辑用户商品
	 * @param isUpdate true更新，false新增
	 */
	boolean addOrUpdateUserGoods(UserGoodsVo userGoodsVo, boolean isUpdate);
	
	/**
	 * 用户商品详情
	 */
	UserGoodsDetailVo queryUserGoodsDetail(String goodsId);
}
