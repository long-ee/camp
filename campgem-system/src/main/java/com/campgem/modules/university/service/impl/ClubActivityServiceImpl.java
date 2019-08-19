package com.campgem.modules.university.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.modules.university.dto.ClubActivityQueryDto;
import com.campgem.modules.university.entity.ClubActivity;
import com.campgem.modules.university.mapper.ClubActivityMapper;
import com.campgem.modules.university.service.IClubActivityService;
import com.campgem.modules.university.vo.ClubActivityVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: 社团活动信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
public class ClubActivityServiceImpl extends ServiceImpl<ClubActivityMapper, ClubActivity> implements IClubActivityService {


    @Resource
    private ClubActivityMapper clubActivityMapper;

    @Override
    public IPage<ClubActivityVo> queryPageList(Page page, ClubActivityQueryDto queryDto) {
        return clubActivityMapper.queryPageList(page, queryDto);
    }

    @Override
    public ClubActivityVo queryDetails(String id) {
        if(StringUtils.isBlank(id)){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
        ClubActivityVo activityVo = clubActivityMapper.queryDetails(id);
        if(null == activityVo){
            throw new JeecgBootException(StatusEnum.NotFound);
        }
        return activityVo;
    }
}
