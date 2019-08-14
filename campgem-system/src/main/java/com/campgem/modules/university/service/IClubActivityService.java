package com.campgem.modules.university.service;

import com.campgem.modules.university.dto.ClubActivityQueryDto;
import com.campgem.modules.university.entity.ClubActivity;
import com.campgem.modules.university.vo.ClubActivityVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 社团活动信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface IClubActivityService extends IService<ClubActivity> {
    /**
     * 根据条件分页查询社团活动分页列表
     * @param queryDto
     * @return
     */
    IPage<ClubActivityVo> queryPageList(Page page, ClubActivityQueryDto queryDto);

    /**
     * 查询社团活动详情
     * @param id
     * @return
     */
    ClubActivityVo queryDetails(String id);
}
