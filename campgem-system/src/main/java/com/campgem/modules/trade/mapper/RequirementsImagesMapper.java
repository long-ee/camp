package com.campgem.modules.trade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.trade.entity.RequirementsImages;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 需求图片
 * @Author: campgem
 * @Date:   2019-08-18
 * @Version: V1.0
 */
public interface RequirementsImagesMapper extends BaseMapper<RequirementsImages> {
	public List<RequirementsImages> queryRequirementsImages(@Param("requirementId") String requirementId);
}
