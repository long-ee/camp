package com.campgem.modules.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.modules.trade.entity.RequirementsImages;
import com.campgem.modules.trade.mapper.RequirementsImagesMapper;
import com.campgem.modules.trade.service.IRequirementsImagesService;
import org.springframework.stereotype.Service;

/**
 * @Description: 需求图片
 * @Author: campgem
 * @Date:   2019-08-18
 * @Version: V1.0
 */
@Service
public class RequirementsImagesServiceImpl extends ServiceImpl<RequirementsImagesMapper, RequirementsImages> implements IRequirementsImagesService {
	
	@Override
	public void deleteByRequirementId(String id) {
		baseMapper.delete(new LambdaQueryWrapper<RequirementsImages>().eq(RequirementsImages::getRequirementId, id));
	}
}
