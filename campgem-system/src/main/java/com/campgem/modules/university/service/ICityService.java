package com.campgem.modules.university.service;

import com.campgem.modules.university.dto.CityQueryDto;
import com.campgem.modules.university.entity.City;
import com.campgem.modules.university.vo.CityVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 城市信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface ICityService extends IService<City> {
    /**
     * 根据条件分页查询城市分页列表
     * @param queryDto
     * @return
     */
    IPage<CityVo> queryPageList(Page page, CityQueryDto queryDto);

    /**
     * 查询城市信息详情
     * @param cityId
     * @return
     */
    CityVo queryDetails(String cityId);
}
