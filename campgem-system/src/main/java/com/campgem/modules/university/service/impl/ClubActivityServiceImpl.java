package com.campgem.modules.university.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.util.BeanConvertUtils;
import com.campgem.common.util.DateUtils;
import com.campgem.modules.university.dto.ClubActivityDto;
import com.campgem.modules.university.dto.ClubActivityQueryDto;
import com.campgem.modules.university.entity.Club;
import com.campgem.modules.university.entity.ClubActivity;
import com.campgem.modules.university.mapper.ClubActivityMapper;
import com.campgem.modules.university.service.IClubActivityService;
import com.campgem.modules.university.service.IClubService;
import com.campgem.modules.university.vo.ClubActivityCalendarVo;
import com.campgem.modules.university.vo.ClubActivityVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 社团活动信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
public class ClubActivityServiceImpl extends ServiceImpl<ClubActivityMapper, ClubActivity> implements IClubActivityService {

    @Resource
    private IClubService clubService;
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

    @Override
    public void create(ClubActivityDto clubActivityDto) {
        if(StringUtils.isBlank(clubActivityDto.getPublisherId())){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
        Club club = clubService.getById(clubActivityDto.getClubId());
        if(null == club){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
        ClubActivity clubActivity = BeanConvertUtils.convertBean(clubActivityDto, ClubActivity.class);
        clubActivity.setUniversityId(club.getUniversityId());
        this.save(clubActivity);
    }

    @Override
    public void edit(ClubActivityDto clubActivityDto) {
        if(StringUtils.isBlank(clubActivityDto.getId())){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
        ClubActivity clubActivity = this.getById(clubActivityDto.getId());
        if(null == clubActivity){
            throw new JeecgBootException(StatusEnum.NotFound);
        }
        clubActivity = BeanConvertUtils.convertBean(clubActivityDto, ClubActivity.class);
        this.updateById(clubActivity);
    }

    @Override
    public List<ClubActivityCalendarVo> queryActivityCalendar(String universityId) {
        String firstDay = DateUtils.getFirstDayOfMonth();
        String endDay = DateUtils.getEndDayOfNextMonth();
        return clubActivityMapper.queryActivityCalendar(universityId, firstDay, endDay);
    }
}
