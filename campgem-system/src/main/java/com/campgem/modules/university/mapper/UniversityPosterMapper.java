package com.campgem.modules.university.mapper;

import com.campgem.modules.university.dto.UniversityPosterQueryDto;
import com.campgem.modules.university.entity.UniversityPoster;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.university.vo.UniversityPosterVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 海报信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface UniversityPosterMapper extends BaseMapper<UniversityPoster> {

    /**
     * 根据条件查询校园海报列表信息
     * @param queryDto
     * @return
     */
    List<UniversityPosterVo> queryList(@Param("queryDto") UniversityPosterQueryDto queryDto);

}
