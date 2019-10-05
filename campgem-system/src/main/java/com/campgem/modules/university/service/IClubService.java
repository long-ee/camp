package com.campgem.modules.university.service;

import com.campgem.modules.university.dto.ClubAdminDto;
import com.campgem.modules.university.dto.ClubCreateOrUpdateDto;
import com.campgem.modules.university.dto.ClubDto;
import com.campgem.modules.university.dto.ClubQueryDto;
import com.campgem.modules.university.entity.Club;
import com.campgem.modules.university.vo.ClubVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.user.vo.MemberVo;

import java.util.List;

/**
 * @Description: 社团信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface IClubService extends IService<Club> {

    /**
     * 根据条件查询社团列表
     * @param queryDto
     * @return
     */
    public List<ClubVo> queryList(ClubQueryDto queryDto);

    /**
     * 根据条件分页查询学校海报分页列表
     * @param page
     * @param queryDto
     * @return
     */
    public IPage<ClubVo> queryPageList(Page page, ClubQueryDto queryDto);

    /**
     * 查询学校海报信息详情
     * @param id
     * @return
     */
    public ClubVo queryDetails(String id);

    /**
     * 加入社团
     * @param clubDto
     */
    public void joinClub(ClubDto clubDto);

    /**
     * 退出社团
     * @param clubDto
     */
    public void dropOutClub(ClubDto clubDto);

    /**
     * 解散社团
     * @param clubId
     */
    public void dismissClub(String clubId);

    /**
     * 用户是否是社团成员
     * @param clubId
     * @param memberId
     * @return
     */
    public boolean isClubMember(String clubId, String memberId);

    /**
     * 创建社团
     * @param createOrUpdateDto
     */
    public void createClub(ClubCreateOrUpdateDto createOrUpdateDto);

    /**
     * 更新社团
     * @param createOrUpdateDto
     */
    public void updateClub(ClubCreateOrUpdateDto createOrUpdateDto);

    /**
     * 社团管理员列表
     * @param clubId
     */
    public List<MemberVo> listAdmin(String clubId, boolean includePrimaryAdmin);

    /**
     * 添加管理员
     * @param clubAdminDto
     */
    public void addAdmin(ClubAdminDto clubAdminDto);

    /**
     * 删除管理员
     * @param clubAdminDto
     */
    public void removeAdmin(ClubAdminDto clubAdminDto);

    /**
     * 转让管理员
     * @param clubAdminDto
     */
    public void transferAdmin(ClubAdminDto clubAdminDto);
}
