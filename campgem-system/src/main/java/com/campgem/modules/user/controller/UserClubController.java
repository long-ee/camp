package com.campgem.modules.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.university.dto.ClubDto;
import com.campgem.modules.university.dto.ClubQueryDto;
import com.campgem.modules.university.service.IClubService;
import com.campgem.modules.university.vo.ClubVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags="我的资源（社团）管理接口")
@Slf4j
@RequestMapping("/api/v1")
public class UserClubController {

    @Autowired
    private IClubService clubService;

    @ApiOperation(value="我的资源（社团）- 社团列表", notes="G15 我的资源（社团）- 社团列表")
    @GetMapping(value = "/user/club/list")
    public Result<IPage<ClubVo>> queryUserClubList(ClubQueryDto queryDto,
                                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                   HttpServletRequest req) {
        String memberId = SecurityUtils.getCurrentUserMemberId();
        queryDto.setMemberId(memberId);
        Page<ClubQueryDto> page = new Page<>(pageNo, pageSize);
        IPage<ClubVo> clubVos = clubService.queryPageList(page, queryDto);
        return new Result<IPage<ClubVo>>().result(clubVos);
    }



}
