package com.campgem.modules.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.util.BeanConvertUtils;
import com.campgem.modules.common.dto.CityQueryDto;
import com.campgem.modules.common.entity.City;
import com.campgem.modules.common.mapper.CityMapper;
import com.campgem.modules.common.service.ICityService;
import com.campgem.modules.common.vo.CityVo;
import org.springframework.stereotype.Service;

/**
 * @Description: 城市信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements ICityService {

    @Override
    public IPage<City> queryPageList(Page page, CityQueryDto queryDto) {
        LambdaQueryWrapper<City> query = new LambdaQueryWrapper<>();
        query.like(City::getCountry, queryDto.getCountry());
        query.like(City::getState, queryDto.getState());
        query.like(City::getCityName, queryDto.getCityName());
        query.eq(City::getDelFlag, 0);
        query.orderByDesc(City::getCountry);
        query.orderByDesc(City::getCreateTime);
        return this.page(page, query);
    }

    @Override
    public CityVo queryDetails(String cityId) {
        City city = this.getById(cityId);
        return BeanConvertUtils.convertBean(city, CityVo.class);
    }
}
