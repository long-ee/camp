package com.campgem.modules.university.service.impl;

import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.modules.university.dto.UniversityQueryDto;
import com.campgem.modules.university.entity.University;
import com.campgem.modules.university.mapper.UniversityMapper;
import com.campgem.modules.university.service.IUniversityService;
import com.campgem.modules.university.vo.UniversityVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 校园信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
public class UniversityServiceImpl extends ServiceImpl<UniversityMapper, University> implements IUniversityService {

    @Resource
    private UniversityMapper universityMapper;


    @Override
    public List<UniversityVo> queryList(UniversityQueryDto queryDto) {
        return universityMapper.queryList(queryDto);
    }

    @Override
    public IPage<UniversityVo> queryPageList(Page page, UniversityQueryDto queryDto) {
        return null;
    }

    @Override
    public UniversityVo queryDetails(String universityId) {
        if(StringUtils.isBlank(universityId)){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
        UniversityQueryDto queryDto = new UniversityQueryDto();
        queryDto.setUniversityId(universityId);
        List<UniversityVo> universityVos = universityMapper.queryList(queryDto);
        if(CollectionUtils.isEmpty(universityVos)){
            throw new JeecgBootException(StatusEnum.NotFound);
        }
        return universityVos.get(0);
    }
}
