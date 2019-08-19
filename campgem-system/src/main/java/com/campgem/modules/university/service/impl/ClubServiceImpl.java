package com.campgem.modules.university.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.modules.university.dto.ClubDto;
import com.campgem.modules.university.dto.ClubQueryDto;
import com.campgem.modules.university.entity.Club;
import com.campgem.modules.university.entity.ClubMember;
import com.campgem.modules.university.mapper.ClubMapper;
import com.campgem.modules.university.service.IClubMemberService;
import com.campgem.modules.university.service.IClubService;
import com.campgem.modules.university.vo.ClubVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 社团信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
public class ClubServiceImpl extends ServiceImpl<ClubMapper, Club> implements IClubService {

    @Resource
    private ClubMapper clubMapper;
    @Resource
    private IClubMemberService clubMemberService;

    @Override
    public List<ClubVo> queryList(ClubQueryDto queryDto) {
        return clubMapper.queryList(queryDto);
    }

    @Override
    public IPage<ClubVo> queryPageList(Page page, ClubQueryDto queryDto) {
        return clubMapper.queryPageList(page, queryDto);
    }

    @Override
    public ClubVo queryDetails(String id) {
        if(StringUtils.isBlank(id)){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
        ClubQueryDto queryDto = new ClubQueryDto();
        queryDto.setClubId(id);
        List<ClubVo> clubVos = clubMapper.queryList(queryDto);
        if(CollectionUtils.isEmpty(clubVos)){
            throw new JeecgBootException(StatusEnum.NotFound);
        }
        return clubVos.get(0);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void joinClub(ClubDto clubDto) {
        clubDto.paramValidation();
        Club club = this.getById(clubDto.getClubId());
        if(null == club){
            throw new JeecgBootException(StatusEnum.ClubNotExistError);
        }
        LambdaQueryWrapper<ClubMember> query = new LambdaQueryWrapper<>();
        query.eq(ClubMember::getClubId, clubDto.getClubId());
        query.eq(ClubMember::getMemberId, clubDto.getMemberId());
        ClubMember existObj = clubMemberService.getOne(query);
        if(null != existObj){
            throw new JeecgBootException(StatusEnum.ClubExistUserError);
        }
        ClubMember clubMember = new ClubMember();
        clubMember.setClubId(clubDto.getClubId());
        clubMember.setMemberId(clubDto.getMemberId());
        clubMemberService.save(clubMember);
        this.updateClubMemberCount(clubDto.getClubId());
    }

    @Override
    public void dropOutClub(ClubDto clubDto) {
        clubDto.paramValidation();
        Club club = this.getById(clubDto.getClubId());
        if(null == club){
            throw new JeecgBootException(StatusEnum.ClubNotExistError);
        }
        if(StringUtils.equals(club.getCreatorId(), clubDto.getMemberId())){
            throw new JeecgBootException(StatusEnum.MemberIsAdminNotAllowDropOut);
        }
        LambdaQueryWrapper<ClubMember> query = new LambdaQueryWrapper<>();
        query.eq(ClubMember::getClubId, clubDto.getClubId());
        query.eq(ClubMember::getMemberId, clubDto.getMemberId());
        clubMemberService.remove(query);
        this.updateClubMemberCount(clubDto.getClubId());
    }

    @Override
    public boolean isClubMember(String clubId, String memberId) {
        LambdaQueryWrapper<ClubMember> query = new LambdaQueryWrapper<>();
        query.eq(ClubMember::getClubId, clubId);
        query.eq(ClubMember::getMemberId, memberId);
        ClubMember existObj = clubMemberService.getOne(query);
        if(null != existObj){
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateClubMemberCount(String clubId) {
        if(StringUtils.isBlank(clubId)){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
        Club club = this.getById(clubId);
        if(null == club){
            throw new JeecgBootException(StatusEnum.ClubNotExistError);
        }
        LambdaQueryWrapper<ClubMember> query = new LambdaQueryWrapper<>();
        query.eq(ClubMember::getClubId, clubId);
        int memberCount = clubMemberService.count(query);
        club.setMemberCount(memberCount);
        this.updateById(club);
    }
}
