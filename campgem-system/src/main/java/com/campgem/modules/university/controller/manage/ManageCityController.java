package com.campgem.modules.university.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.modules.university.dto.CityQueryDto;
import com.campgem.modules.university.entity.City;
import com.campgem.modules.university.service.ICityService;
import com.campgem.modules.university.vo.CityVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@Api(tags="【管理端】城市信息管理接口")
@Slf4j
@RequestMapping("/api/manage/v1")
public class ManageCityController {

    @Resource
    private ICityService cityService;

    /**
     * 城市信息分页查询
     * @param queryDto
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @ApiOperation(value="城市信息-分页列表查询", notes="城市信息-分页列表查询")
    @GetMapping(value = "/city/pageList")
    public Result<IPage<CityVo>> queryPageList(CityQueryDto queryDto,
                                               @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                               @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                               HttpServletRequest req) {
        Result<IPage<CityVo>> result = new Result<>();
        Page<CityQueryDto> page = new Page<CityQueryDto>(pageNo, pageSize);
        IPage<CityVo> pageList = cityService.queryPageList(page, queryDto);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }


    /**
     * 城市添加
     * @param city
     * @return
     */
    @ApiOperation(value="城市信息-添加", notes="城市信息-添加")
    @PostMapping(value = "/city/add")
    public Result<City> add(@Valid City city) {
        Result<City> result = new Result<City>();
        try {
            cityService.save(city);
            result.success("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            result.error500("操作失败");
        }
        return result;
    }


    /**
     * 城市编辑
     * @param city
     * @return
     */
    @ApiOperation(value="城市信息-编辑", notes="城市信息-编辑")
    @PostMapping(value = "/city/edit")
    public Result<City> edit(@Valid City city) {
        Result<City> result = new Result<City>();
        City cityEntity = cityService.getById(city.getId());
        if(cityEntity==null) {
            result.error500("未找到对应实体");
        }else {
            boolean ok = cityService.updateById(city);
            if(ok) {
                result.success("修改成功!");
            }
        }
        return result;
    }

    /**
     * 城市详情
     * @param id
     * @return
     */
    @ApiOperation(value="城市信息-详情查询", notes="城市信息-详情查询")
    @GetMapping(value = "/city/details")
    public Result<CityVo> queryById(@RequestParam(name="id",required=true) String id) {
        Result<CityVo> result = new Result<CityVo>();
        CityVo cityVo = cityService.queryDetails(id);
        if(null == cityVo) {
            result.error500("未找到对应实体");
        }else {
            result.setResult(cityVo);
            result.setSuccess(true);
        }
        return result;
    }

    /**
     * 城市删除
     * @param id
     * @return
     */
    @ApiOperation(value="城市信息-通过id删除", notes="城市信息-通过id删除")
    @PostMapping(value = "/city/delete")
    public Result<?> delete(@RequestParam(name="id",required=true) String id) {
        try {
            cityService.removeById(id);
        } catch (Exception e) {
            log.error("删除失败",e.getMessage());
            return Result.error("删除失败!");
        }
        return Result.ok("删除成功!");
    }

}
