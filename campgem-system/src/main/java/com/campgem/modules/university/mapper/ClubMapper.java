package com.campgem.modules.university.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.modules.university.dto.ClubQueryDto;
import com.campgem.modules.university.entity.Club;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.university.vo.ClubVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 社团信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface ClubMapper extends BaseMapper<Club> {
    /**
     * 根据条件分页查询社团信息
     * @param page
     * @param queryDto
     * @return
     */
    IPage<ClubVo> queryPageList(Page page, @Param("queryDto") ClubQueryDto queryDto);


    /**
     * 根据条件查询社团列表
     * @param queryDto
     * @return
     */
    List<ClubVo> queryList(@Param("queryDto") ClubQueryDto queryDto);

}
