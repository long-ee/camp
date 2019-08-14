package com.campgem.modules.university.service.impl;

import com.campgem.modules.university.dto.ClubActivityQueryDto;
import com.campgem.modules.university.entity.ClubActivity;
import com.campgem.modules.university.mapper.ClubActivityMapper;
import com.campgem.modules.university.service.IClubActivityService;
import com.campgem.modules.university.vo.ClubActivityVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 社团活动信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
public class ClubActivityServiceImpl extends ServiceImpl<ClubActivityMapper, ClubActivity> implements IClubActivityService {

    @Override
    public IPage<ClubActivityVo> queryPageList(Page page, ClubActivityQueryDto queryDto) {
        return null;
    }

    @Override
    public ClubActivityVo queryDetails(String id) {
        return null;
    }
}
