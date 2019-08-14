package com.campgem.modules.university.service.impl;

import com.campgem.modules.university.dto.UniversityQueryDto;
import com.campgem.modules.university.entity.University;
import com.campgem.modules.university.mapper.UniversityMapper;
import com.campgem.modules.university.service.IUniversityService;
import com.campgem.modules.university.vo.UniversityVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 校园信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
public class UniversityServiceImpl extends ServiceImpl<UniversityMapper, University> implements IUniversityService {

    @Override
    public IPage<UniversityVo> queryPageList(Page page, UniversityQueryDto queryDto) {
        return null;
    }

    @Override
    public UniversityVo queryDetails(String universityId) {
        return null;
    }
}
