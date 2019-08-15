package com.campgem.modules.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.common.entity.Advertisement;
import com.campgem.modules.common.vo.AdvertisementVo;

import java.util.List;

/**
 * @Description: 广告信息
 * @Author: campgem
 * @Date: 2019-08-05
 * @Version: V1.0
 */
public interface IAdvertisementService extends IService<Advertisement> {
	List<AdvertisementVo> getAdvertisementByLocation(String location);
}
