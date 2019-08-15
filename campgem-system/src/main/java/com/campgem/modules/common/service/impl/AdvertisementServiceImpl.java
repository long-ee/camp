package com.campgem.modules.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.util.BeanConvertUtils;
import com.campgem.modules.common.entity.Advertisement;
import com.campgem.modules.common.mapper.AdvertisementMapper;
import com.campgem.modules.common.service.IAdvertisementService;
import com.campgem.modules.common.vo.AdvertisementVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 分类信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
public class AdvertisementServiceImpl extends ServiceImpl<AdvertisementMapper, Advertisement> implements IAdvertisementService {
	@Override
	public List<AdvertisementVo> getAdvertisementByLocation(String location) {
		List<Advertisement> list = baseMapper.selectList(new LambdaQueryWrapper<Advertisement>()
				.eq(Advertisement::getPageLocation, location)
				.orderByDesc(Advertisement::getCreateTime));
		return BeanConvertUtils.copyList(list, AdvertisementVo.class);
	}
}
