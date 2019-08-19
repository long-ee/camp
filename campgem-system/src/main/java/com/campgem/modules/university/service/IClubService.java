package com.campgem.modules.university.service;

import com.campgem.modules.university.dto.ClubDto;
import com.campgem.modules.university.dto.ClubQueryDto;
import com.campgem.modules.university.entity.Club;
import com.campgem.modules.university.vo.ClubVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

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
    List<ClubVo> queryList(ClubQueryDto queryDto);

    /**
     * 根据条件分页查询学校海报分页列表
     * @param page
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

    /**
     * 加入社团
     * @param clubDto
     */
    void joinClub(ClubDto clubDto);

    /**
     * 退出社团
     * @param clubDto
     */
    void dropOutClub(ClubDto clubDto);

    /**
     * 用户是否是社团成员
     * @param clubId
     * @param memberId
     * @return
     */
    boolean isClubMember(String clubId, String memberId);

    /**
     * 重置社团成员数量
     * @param clubId
     */
    void updateClubMemberCount(String clubId);
}
