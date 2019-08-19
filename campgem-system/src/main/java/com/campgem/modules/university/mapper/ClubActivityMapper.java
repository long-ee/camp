package com.campgem.modules.university.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.modules.university.dto.ClubActivityQueryDto;
import com.campgem.modules.university.entity.ClubActivity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.university.vo.ClubActivityVo;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 社团活动信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface ClubActivityMapper extends BaseMapper<ClubActivity> {

    /**
     * 根据条件分页查询社团活动
     * @param page
     * @param queryDto
     * @return
     */
    IPage<ClubActivityVo> queryPageList(Page page, ClubActivityQueryDto queryDto);

    /**
     * 查询社团详情
     * @param activityId
     * @return
     */
    ClubActivityVo queryDetails(@Param("activityId") String activityId);
}
