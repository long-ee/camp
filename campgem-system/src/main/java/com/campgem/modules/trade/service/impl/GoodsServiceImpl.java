package com.campgem.modules.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.util.BeanConvertUtils;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.common.utils.CommonUtils;
import com.campgem.modules.trade.dto.GoodsQueryDto;
import com.campgem.modules.trade.dto.OrderInfoDto;
import com.campgem.modules.trade.dto.manage.MGoodsQueryDto;
import com.campgem.modules.trade.entity.Goods;
import com.campgem.modules.trade.entity.GoodsImages;
import com.campgem.modules.trade.entity.GoodsSpecifications;
import com.campgem.modules.trade.entity.enums.GoodsStatusEnum;
import com.campgem.modules.trade.mapper.GoodsMapper;
import com.campgem.modules.trade.service.IGoodsImagesService;
import com.campgem.modules.trade.service.IGoodsService;
import com.campgem.modules.trade.service.IGoodsSpecificationsService;
import com.campgem.modules.trade.vo.*;
import com.campgem.modules.trade.vo.manage.MGoodsListVo;
import com.campgem.modules.trade.vo.manage.MGoodsVo;
import com.campgem.modules.user.service.IMemberService;
import com.campgem.modules.user.vo.MemberVo;
import com.campgem.modules.user.vo.UserGoodsDetailVo;
import com.campgem.modules.user.vo.UserGoodsListVo;
import com.campgem.modules.user.vo.UserGoodsVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Description: 分类信息
 * @Author: ling
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
	
	@Resource
	private IMemberService memberService;
	
	@Resource
	private IGoodsSpecificationsService goodsSpecificationsService;
	@Resource
	private IGoodsImagesService goodsImagesService;
	
	@Override
	public IPage<GoodsListVo> queryPageList(Page<GoodsQueryDto> page, GoodsQueryDto queryDto) {
		return baseMapper.queryPageList(page, queryDto);
	}
	
	@Override
	public GoodsDetailVo queryGoodsDetail(String goodsId) {
		return queryGoodsDetail(goodsId, true);
	}
	
	@Override
	public GoodsDetailVo queryGoodsDetail(String goodsId, boolean isRelated) {
		GoodsDetailVo goods = baseMapper.queryGoodsDetail(goodsId);
		if (goods == null) {
			throw new JeecgBootException(StatusEnum.GoodsNotExistError);
		}
		
		if (isRelated) {
			// 关联商品查询
			List<GoodsRelatedVo> relatives = baseMapper.queryGoodsRelated(goods.getCategoryId(), goodsId);
			goods.setRelatives(relatives);
		}
		
		return goods;
	}
	
	@Override
	public void increment(String goodsId) {
		baseMapper.incrementReviewCount(goodsId);
	}
	
	@Override
	public List<OrderInfoVo> queryOrderInfo(OrderInfoDto orderInfoDto) {
		OrderInfoTempVo goodsVo = baseMapper.queryOrderInfo(orderInfoDto.getGoodsId(), orderInfoDto.getSpecificationId());
		if (goodsVo == null) {
			throw new JeecgBootException(StatusEnum.GoodsNotExistError);
		}
		if (CommonUtils.isBusiness(goodsVo.getMemberType())) {
			// 商家，必须要有规格
			if (orderInfoDto.getSpecificationId() == null) {
				throw new JeecgBootException(StatusEnum.SpecificationBlankError);
			} else {
				// 设置规格数据
				if (StringUtils.isEmpty(goodsVo.getSpecification())) {
					throw new JeecgBootException(StatusEnum.SpecificationBlankError);
				}
				String[] spec = goodsVo.getSpecification().split(",");
				goodsVo.setSpecificationId(spec[0]);
				goodsVo.setSalePrice(new BigDecimal(spec[1]));
				goodsVo.setSpecificationName(spec[2]);
				goodsVo.setSpecificationStock(Integer.parseInt(spec[3]));
				goodsVo.setSpecification(null);
			}
		}
		
		goodsVo.setQuantity(orderInfoDto.getQuantity());
		
		List<OrderInfoVo> list = new ArrayList<>();
		OrderInfoVo infoVo = new OrderInfoVo();
		infoVo.setSellerId(goodsVo.getSellerId());
		infoVo.setMemberName(goodsVo.getMemberName());
		infoVo.setMemberType(goodsVo.getMemberType());
		infoVo.setShippingMethods(goodsVo.getShippingMethods());
		
		infoVo.setGoods(new ArrayList<OrderInfoVo.SellerGoods> () {{
			add(BeanConvertUtils.copy(goodsVo, OrderInfoVo.SellerGoods.class));
		}});
		
		return list;
	}
	
	@Override
	public IPage<MGoodsListVo> queryPageList(Page<MGoodsQueryDto> page, MGoodsQueryDto queryDto) {
		return baseMapper.queryManagePageList(page, queryDto);
	}
	
	@Override
	@Transactional
	public boolean save(MGoodsVo saveGoods) {
		String goodsId = UUID.randomUUID().toString().replace("-", "");
		Goods goods = BeanConvertUtils.copy(saveGoods, Goods.class);
		goods.setId(goodsId);
		goods.setCreateTime(new Date());
		
		MemberVo member = memberService.getMemberByUserBaseId(goods.getUid());
		// 设置卖家名为商家名
		goods.setMemberName(member.getBusinessName());
		goods.setMemberType(member.getMemberType());
		if (CommonUtils.isBusiness(member.getMemberType())) {
			if (saveGoods.getSpecs() == null || saveGoods.getSpecs().length == 0) {
				throw new JeecgBootException(StatusEnum.SpecificationBlankError);
			}
		}
		
		save(goods);
		
		// 商品图片
		if (saveGoods.getImages() != null) {
			for (GoodsImages image : saveGoods.getImages()) {
				image.setGoodsId(goodsId);
			}
			goodsImagesService.saveBatch(Arrays.asList(saveGoods.getImages()));
		}
		
		// 商品规格
		if (saveGoods.getSpecs() != null) {
			for (GoodsSpecifications spec : saveGoods.getSpecs()) {
				spec.setGoodsId(goodsId);
			}
			goodsSpecificationsService.saveBatch(Arrays.asList(saveGoods.getSpecs()));
		}
		
		return true;
	}
	
	@Override
	@Transactional
	public boolean update(MGoodsVo updateGoods) {
		Goods goods = BeanConvertUtils.copy(updateGoods, Goods.class);
		
		MemberVo memberVo = memberService.getMemberByUserBaseId(goods.getUid());
		// 设置卖家名为商家名
		goods.setMemberName(memberVo.getBusinessName());
		goods.setMemberType(memberVo.getMemberType());
		if (CommonUtils.isBusiness(memberVo.getMemberType())) {
			if (updateGoods.getSpecs() == null || updateGoods.getSpecs().length == 0) {
				throw new JeecgBootException(StatusEnum.SpecificationBlankError);
			}
		}
		
		if (!updateById(goods)) {
			throw new JeecgBootException(StatusEnum.InternalError);
		}
		
		// 商品图片
		if (updateGoods.getImages() != null) {
			for (GoodsImages image : updateGoods.getImages()) {
				image.setGoodsId(goods.getId());
			}
			// 删除原来的图片
			goodsImagesService.remove(new LambdaQueryWrapper<GoodsImages>().eq(GoodsImages::getGoodsId, goods.getId()));
			goodsImagesService.saveBatch(Arrays.asList(updateGoods.getImages()));
		}
		
		// 商品规格
		if (updateGoods.getSpecs() != null) {
			for (GoodsSpecifications spec : updateGoods.getSpecs()) {
				spec.setGoodsId(goods.getId());
			}
			// 删除原来的规格
			goodsSpecificationsService.remove(new LambdaQueryWrapper<GoodsSpecifications>().eq(GoodsSpecifications::getGoodsId, goods.getId()));
			goodsSpecificationsService.saveBatch(Arrays.asList(updateGoods.getSpecs()));
		}
		
		return true;
	}
	
	@Override
	public IPage<UserGoodsListVo> queryPageList(Page page) {
		return baseMapper.queryUserOrdersPageList(page);
	}
	
	@Override
	@Transactional
	public boolean addOrUpdateUserGoods(UserGoodsVo userGoodsVo, boolean isUpdate) {
		String uid = SecurityUtils.getCurrentUserUid();
		Goods goods = BeanConvertUtils.copy(userGoodsVo, Goods.class);
		
		if (userGoodsVo.getGoodsImages().length == 0) {
			throw new JeecgBootException(StatusEnum.GoodsImagesEmptyError);
		}
		
		String goodsId;
		if (!isUpdate) {
			goodsId  = UUID.randomUUID().toString().replaceAll("-", "");
			goods.setId(goodsId);
		} else {
			// 是否是商品所有者
			goodsId = goods.getId();
			Goods goodsConfirm = getById(goodsId);
			if (!goodsConfirm.getUid().equals(uid)) {
				throw new JeecgBootException(StatusEnum.GoodsOwnError);
			}
		}
		
		MemberVo member = memberService.getMemberByUserBaseId(uid);
		// 公共属性
		goods.setUid(uid);
		goods.setMemberName(member.getMemberName());
		goods.setMemberType(member.getMemberType());
		goods.setCreateTime(new Date());
		goods.setDelFlag(0);
		goods.setStatus(GoodsStatusEnum.IN_SALE.code());
		
		
		if (CommonUtils.isBusiness(member.getMemberType())) {
			// 商家
			UserGoodsVo.BusinessProperty businessProperty = userGoodsVo.getBusinessProperty();
			if (businessProperty.getTaxes() == null || businessProperty.getGoodsSpecifications() == null
					|| businessProperty.getGoodsSpecifications().length == 0) {
				throw new JeecgBootException(StatusEnum.UserGoodsPropertyError);
			}
			
			goods.setTaxes(businessProperty.getTaxes());
			goods.setCityId(member.getCityId());
			
			if (isUpdate) {
				// 删除原来的规格
				goodsSpecificationsService.remove(new LambdaQueryWrapper<GoodsSpecifications>().eq(GoodsSpecifications::getGoodsId, goodsId));
			}
			
			if (businessProperty.getGoodsSpecifications().length == 0) {
				throw new JeecgBootException(StatusEnum.SpecificationBlankError);
			}
			
			for (GoodsSpecifications spec : businessProperty.getGoodsSpecifications()) {
				if (spec.getSpecificationsStock() == null || spec.getSpecificationsName() == null || spec.getSpecificationsPrice() == null) {
					throw new JeecgBootException(StatusEnum.SpecificationBlankError);
				}
				
				spec.setGoodsId(goodsId);
			}
			
			// 添加规格钱，保存/更新商品
			if (isUpdate) {
				updateById(goods);
			} else {
				save(goods);
			}
			
			// 添加规格
			goodsSpecificationsService.saveBatch(Arrays.asList(businessProperty.getGoodsSpecifications()));
		} else {
			UserGoodsVo.GeneralProperty generalProperty = userGoodsVo.getGeneralProperty();
			if (generalProperty == null || generalProperty.getStock() == null || generalProperty.getSalePrice() == null) {
				throw new JeecgBootException(StatusEnum.UserGoodsPropertyError);
			}
			
			goods.setSalePrice(generalProperty.getSalePrice());
			goods.setStock(generalProperty.getStock());
			goods.setEndDate(generalProperty.getEndDate());
			goods.setOriginPrice(generalProperty.getOriginPrice());
			goods.setQuality(generalProperty.getQuality());
			
			goods.setUniversityId(member.getUniversityId());
			
			if (isUpdate) {
				updateById(goods);
			} else {
				save(goods);
			}
		}
		
		if (isUpdate) {
			// 删除图片
			goodsImagesService.remove(new LambdaQueryWrapper<GoodsImages>().eq(GoodsImages::getGoodsId, goodsId));
		}
		for (GoodsImages images : userGoodsVo.getGoodsImages()) {
			if (images.getGoodsImage() == null) {
				throw new JeecgBootException(StatusEnum.GoodsImagesEmptyError);
			}
			images.setGoodsId(goodsId);
		}
		// 添加图片
		goodsImagesService.saveBatch(Arrays.asList(userGoodsVo.getGoodsImages()));
		
		return true;
	}
	
	@Override
	public UserGoodsDetailVo queryUserGoodsDetail(String goodsId) {
		GoodsDetailVo goodsDetailVo = queryGoodsDetail(goodsId, false);
		return BeanConvertUtils.copy(goodsDetailVo, UserGoodsDetailVo.class);
	}
	
	@Override
	public void updateStock(int type, String id, Integer stock) {
		baseMapper.updateStock(type, id, stock);
	}
}
