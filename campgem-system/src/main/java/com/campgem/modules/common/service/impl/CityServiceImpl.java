package com.campgem.modules.common.service.impl;

import com.campgem.modules.common.dto.CityQueryDto;
import com.campgem.modules.common.entity.City;
import com.campgem.modules.common.mapper.CityMapper;
import com.campgem.modules.common.service.ICityService;
import com.campgem.modules.common.vo.CityVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 城市信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements ICityService {

    @Override
    public IPage<CityVo> queryPageList(Page page, CityQueryDto queryDto) {
        return null;
    }

    @Override
    public CityVo queryDetails(String cityId) {
        return null;
    }
}