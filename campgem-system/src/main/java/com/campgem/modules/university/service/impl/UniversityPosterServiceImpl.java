package com.campgem.modules.university.service.impl;

import com.campgem.modules.university.dto.UniversityPosterQueryDto;
import com.campgem.modules.university.entity.UniversityPoster;
import com.campgem.modules.university.mapper.UniversityPosterMapper;
import com.campgem.modules.university.service.IUniversityPosterService;
import com.campgem.modules.university.vo.UniversityPosterVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 海报信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
public class UniversityPosterServiceImpl extends ServiceImpl<UniversityPosterMapper, UniversityPoster> implements IUniversityPosterService {

    @Override
    public IPage<UniversityPosterVo> queryPageList(Page page, UniversityPosterQueryDto queryDto) {
        return null;
    }

    @Override
    public UniversityPosterVo queryDetails(String cityId) {
        return null;
    }
}
