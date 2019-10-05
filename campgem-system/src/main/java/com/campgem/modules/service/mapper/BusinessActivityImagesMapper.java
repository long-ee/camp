package com.campgem.modules.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.service.entity.BusinessActivityImages;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 商家活动图片
 * @Author: campgem
 * @Date:   2019-08-21
 * @Version: V1.0
 */
public interface BusinessActivityImagesMapper extends BaseMapper<BusinessActivityImages> {
	List<BusinessActivityImages> queryActivityImages(@Param("activityId") String activityId);
}
