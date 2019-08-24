package com.campgem.modules.common.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.campgem.common.api.vo.Result;
import com.campgem.common.util.BeanConvertUtils;
import com.campgem.modules.common.dto.CityQueryDto;
import com.campgem.modules.common.entity.City;
import com.campgem.modules.common.service.ICityService;
import com.campgem.modules.common.vo.CityVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags="城市信息管理接口")
@Slf4j
@RequestMapping("/api/v1")
public class CityController {

    @Resource
    private ICityService cityService;


    @ApiOperation(value="城市信息管理-城市列表查询", notes="B1 首页-城市列表查询")
    @GetMapping(value = "/city/list")
    public Result<List<CityVo>> list(CityQueryDto queryDto) {
        LambdaQueryWrapper<City> query = new LambdaQueryWrapper<>();
        query.eq(City::getCountry, queryDto.getCountry());
        query.eq(City::getState, queryDto.getState());
        query.eq(City::getCityName, queryDto.getCityName());
        query.eq(City::getDelFlag, 0);
        query.orderByDesc(City::getCountry);
        List<City> list = cityService.list(query);
        return new Result<List<CityVo>>().result(BeanConvertUtils.copyList(list, CityVo.class));
    }
}
