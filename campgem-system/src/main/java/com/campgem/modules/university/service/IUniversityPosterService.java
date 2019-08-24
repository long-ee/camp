package com.campgem.modules.university.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.university.dto.UniversityPosterDto;
import com.campgem.modules.university.dto.UniversityPosterQueryDto;
import com.campgem.modules.university.entity.UniversityPoster;
import com.campgem.modules.university.vo.UniversityPosterVo;

import java.util.List;

/**
 * @Description: 海报信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface IUniversityPosterService extends IService<UniversityPoster> {

    /**
     * 根据条件查询学校海报列表
     * @param queryDto
     * @return
     */
    List<UniversityPosterVo> queryList(UniversityPosterQueryDto queryDto);

    /**
     * 根据条件分页查询学校海报分页列表
     * @param page
     * @param queryDto
     * @return
     */
    IPage<UniversityPosterVo> queryPageList(Page page, UniversityPosterQueryDto queryDto);

    /**
     * 查询学校海报信息详情
     * @param id
     * @return
     */
    UniversityPosterVo queryDetails(String id);


    /**
     * 发布海报
     * @param universityPosterDto
     * @return
     */
    UniversityPosterVo publish(UniversityPosterDto universityPosterDto);

    /**
     * 编辑海报
     * @param universityPosterDto
     */
    public void edit(UniversityPosterDto universityPosterDto);
}
