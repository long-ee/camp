package com.campgem.modules.university.mapper;

import com.campgem.modules.university.dto.UniversityQueryDto;
import com.campgem.modules.university.entity.University;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.university.vo.UniversityVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 校园信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface UniversityMapper extends BaseMapper<University> {
    /**
     * 根据条件查询城市列表
     * @param queryDto
     * @return
     */
    List<UniversityVo> queryList(@Param("queryDto") UniversityQueryDto queryDto);
}
