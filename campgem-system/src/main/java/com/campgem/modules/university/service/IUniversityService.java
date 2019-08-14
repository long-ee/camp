package com.campgem.modules.university.service;

import com.campgem.modules.university.dto.UniversityQueryDto;
import com.campgem.modules.university.entity.University;
import com.campgem.modules.university.vo.UniversityVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 校园信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface IUniversityService extends IService<University> {
    /**
     * 根据条件分页查询学校分页列表
     * @param queryDto
     * @return
     */
    IPage<UniversityVo> queryPageList(Page page, UniversityQueryDto queryDto);

    /**
     * 查询城市信息详情
     * @param universityId
     * @return
     */
    UniversityVo queryDetails(String universityId);
}
