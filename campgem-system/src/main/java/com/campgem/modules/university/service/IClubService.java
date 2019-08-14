package com.campgem.modules.university.service;

import com.campgem.modules.university.dto.ClubQueryDto;
import com.campgem.modules.university.entity.Club;
import com.campgem.modules.university.vo.ClubVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 社团信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface IClubService extends IService<Club> {
    /**
     * 根据条件分页查询学校海报分页列表
     * @param queryDto
     * @return
     */
    IPage<ClubVo> queryPageList(Page page, ClubQueryDto queryDto);

    /**
     * 查询学校海报信息详情
     * @param id
     * @return
     */
    ClubVo queryDetails(String id);
}
