package com.campgem.modules.university.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.enums.StatusEnum;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.util.BeanConvertUtils;
import com.campgem.modules.university.dto.ClubAdminDto;
import com.campgem.modules.university.dto.ClubCreateOrUpdateDto;
import com.campgem.modules.university.dto.ClubDto;
import com.campgem.modules.university.dto.ClubQueryDto;
import com.campgem.modules.university.entity.Club;
import com.campgem.modules.university.mapper.ClubMapper;
import com.campgem.modules.university.service.IClubMemberService;
import com.campgem.modules.university.service.IClubService;
import com.campgem.modules.university.vo.ClubVo;
import com.campgem.modules.user.service.IMemberService;
import com.campgem.modules.user.vo.MemberVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

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
    @Resource
    private IMemberService memberService;

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
        if(club.getMemberIds().contains(clubDto.getMemberId())){
            throw new JeecgBootException(StatusEnum.ClubExistUserError);
        }
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(club.getMemberIds());
        joiner.add(clubDto.getMemberId());
        String newMemberIds = joiner.toString();
        club.setMemberIds(newMemberIds);
        this.join(club);
    }

    /***
     * 加入
     * @param club
     */
    private synchronized void join(Club club){
        int memberCount = club.getMemberCount() + 1;
        club.setMemberCount(memberCount);
        this.updateById(club);
    }

    /**
     * 退出
     * @param club
     */
    private synchronized void dropOut(Club club){
        int memberCount = club.getMemberCount() - 1;
        club.setMemberCount(memberCount);
        this.updateById(club);
    }

    @Override
    public void dropOutClub(ClubDto clubDto) {
        clubDto.paramValidation();
        Club club = this.getById(clubDto.getClubId());
        if(null == club){
            throw new JeecgBootException(StatusEnum.ClubNotExistError);
        }
        if(club.getAdminIds().contains(clubDto.getMemberId())){
            throw new JeecgBootException(StatusEnum.MemberIsAdminNotAllowDropOut);
        }
        List<String> memberIds =  Arrays.asList(club.getMemberIds().split(",")).stream().collect(Collectors.toList());
        memberIds.removeIf(s -> s.contains(clubDto.getMemberId()));
        String newMemberIds = memberIds.stream().collect(Collectors.joining(","));
        club.setMemberIds(newMemberIds);
        this.dropOut(club);
    }

    @Override
    public boolean isClubMember(String clubId, String memberId) {
        Club club = this.getById(clubId);
        if(null == club){
            throw new JeecgBootException(StatusEnum.ClubNotExistError);
        }
        if(club.getMemberIds().contains(memberId)){
            return true;
        }
        return false;
    }

    @Override
    public void createClub(ClubCreateOrUpdateDto createOrUpdateDto) {
        if(StringUtils.isBlank(createOrUpdateDto.getUniversityId()) || StringUtils.isBlank(createOrUpdateDto.getCreatorId())){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
        Club club = BeanConvertUtils.convertBean(createOrUpdateDto, Club.class);
        club.setMemberCount(1);
        club.setAdminIds(createOrUpdateDto.getCreatorId());
        club.setMemberIds(createOrUpdateDto.getCreatorId());
        this.save(club);
    }

    @Override
    public void updateClub(ClubCreateOrUpdateDto createOrUpdateDto) {
        if(StringUtils.isBlank(createOrUpdateDto.getId())){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
        Club oldClub = this.getById(createOrUpdateDto.getId());
        if(null == oldClub){
            throw new JeecgBootException(StatusEnum.NotFound);
        }
        oldClub.setClubIcon(createOrUpdateDto.getClubIcon());
        oldClub.setClubName(createOrUpdateDto.getClubName());
        oldClub.setCategoryId(createOrUpdateDto.getCategoryId());
        oldClub.setInformation(createOrUpdateDto.getInformation());
        this.updateById(oldClub);
    }

    @Override
    public List<MemberVo> listAdmin(String clubId, boolean includePrimaryAdmin) {
        if(StringUtils.isBlank(clubId)){
            throw new JeecgBootException(StatusEnum.BadRequest);
        }
        Club club = this.getById(clubId);
        if(null == club){
            throw new JeecgBootException(StatusEnum.ClubNotExistError);
        }
        String adminIds = club.getAdminIds();
        if(!includePrimaryAdmin){
            List<String> adminIdList =  Arrays.asList(adminIds.split(",")).stream().collect(Collectors.toList());
            adminIdList.removeIf(s -> s.contains(club.getCreatorId()));
            adminIds = adminIdList.stream().collect(Collectors.joining(","));
        }
        List<MemberVo> memberVos = memberService.queryMemberByIds(adminIds);
        return memberVos;
    }

    @Override
    public void addAdmin(ClubAdminDto clubAdminDto) {
        clubAdminDto.paramValidation();
        Club club = this.getById(clubAdminDto.getClubId());
        if(null == club){
            throw new JeecgBootException(StatusEnum.ClubNotExistError);
        }
        if(club.getAdminIds().contains(clubAdminDto.getMemberId())){
            throw new JeecgBootException(StatusEnum.MemberIsAdmin);
        }
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(club.getAdminIds());
        joiner.add(clubAdminDto.getMemberId());
        String newAdminIds = joiner.toString();
        club.setAdminIds(newAdminIds);
        this.join(club);
    }

    @Override
    public void removeAdmin(ClubAdminDto clubAdminDto) {
        clubAdminDto.paramValidation();
        Club club = this.getById(clubAdminDto.getClubId());
        if(null == club){
            throw new JeecgBootException(StatusEnum.ClubNotExistError);
        }
        if(StringUtils.equals(club.getCreatorId(), clubAdminDto.getMemberId())){
            throw new JeecgBootException(StatusEnum.MemberIsPrimaryAdminNotAllowRemove);
        }
        List<String> adminIds =  Arrays.asList(club.getAdminIds().split(",")).stream().collect(Collectors.toList());
        adminIds.removeIf(s -> s.contains(clubAdminDto.getMemberId()));
        String newAdminIds = adminIds.stream().collect(Collectors.joining(","));
        club.setAdminIds(newAdminIds);
        this.dropOut(club);
    }

    @Override
    public void transferAdmin(ClubAdminDto clubAdminDto) {
        clubAdminDto.paramValidation();
        Club club = this.getById(clubAdminDto.getClubId());
        if(null == club){
            throw new JeecgBootException(StatusEnum.ClubNotExistError);
        }
        List<String> adminIds =  Arrays.asList(club.getAdminIds().split(",")).stream().collect(Collectors.toList());
        adminIds.removeIf(s -> s.contains(club.getCreatorId()));
        String newAdminIds = adminIds.stream().collect(Collectors.joining(","));
        club.setAdminIds(newAdminIds);
        club.setCreatorId(clubAdminDto.getMemberId());
        this.dropOut(club);
    }
}
