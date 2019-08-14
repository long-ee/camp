package com.campgem.modules.university.service.impl;

import com.campgem.modules.university.dto.ClubQueryDto;
import com.campgem.modules.university.entity.Club;
import com.campgem.modules.university.mapper.ClubMapper;
import com.campgem.modules.university.service.IClubService;
import com.campgem.modules.university.vo.ClubVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 社团信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
public class ClubServiceImpl extends ServiceImpl<ClubMapper, Club> implements IClubService {

    @Override
    public IPage<ClubVo> queryPageList(Page page, ClubQueryDto queryDto) {
        return null;
    }

    @Override
    public ClubVo queryDetails(String id) {
        return null;
    }
}
