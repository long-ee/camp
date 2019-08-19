package com.campgem.modules.university.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.util.BeanConvertUtils;
import com.campgem.modules.university.dto.UniversityPosterDto;
import com.campgem.modules.university.dto.UniversityPosterQueryDto;
import com.campgem.modules.university.entity.UniversityPoster;
import com.campgem.modules.university.mapper.UniversityPosterMapper;
import com.campgem.modules.university.service.IUniversityPosterService;
import com.campgem.modules.university.vo.UniversityPosterVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 海报信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
public class UniversityPosterServiceImpl extends ServiceImpl<UniversityPosterMapper, UniversityPoster> implements IUniversityPosterService {

    @Resource
    private UniversityPosterMapper universityPosterMapper;


    @Override
    public List<UniversityPosterVo> queryList(UniversityPosterQueryDto queryDto) {
        return universityPosterMapper.queryList(queryDto);
    }

    @Override
    public IPage<UniversityPosterVo> queryPageList(Page page, UniversityPosterQueryDto queryDto) {
        return null;
    }


    @Override
    public UniversityPosterVo queryDetails(String posterId) {
        if(StringUtils.isBlank(posterId)){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
        UniversityPosterQueryDto queryDto = new UniversityPosterQueryDto();
        queryDto.setPosterId(posterId);
        List<UniversityPosterVo> universityVos = universityPosterMapper.queryList(queryDto);
        if(CollectionUtils.isEmpty(universityVos)){
            throw new JeecgBootException(StatusEnum.NotFound);
        }
        return universityVos.get(0);
    }

    @Override
    public UniversityPosterVo publish(UniversityPosterDto universityPosterDto) {
        // 参数校验
        universityPosterDto.paramValidation();
        UniversityPoster universityPoster = BeanConvertUtils.convertBean(universityPosterDto, UniversityPoster.class);
        this.save(universityPoster);

        UniversityPosterVo universityPosterVo = this.queryDetails(universityPoster.getId());
        if(null == universityPosterVo){
            throw new JeecgBootException(StatusEnum.UnknownError);
        }
        return universityPosterVo;
    }
}
