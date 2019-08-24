package com.campgem.modules.user.controller;

import com.campgem.common.api.vo.Result;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.university.dto.UniversityPosterQueryDto;
import com.campgem.modules.university.service.IUniversityPosterService;
import com.campgem.modules.university.vo.UniversityPosterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags="我的资源（海报）管理接口")
@Slf4j
@RequestMapping("/api/v1")
public class UserUniversityPosterController {
    @Resource
    private IUniversityPosterService universityPosterService;

    @ApiOperation(value="我的资源（海报）-海报列表", notes="G16 我的资源（海报）-海报列表")
    @GetMapping(value = "/user/university/poster/list")
    public Result<List<UniversityPosterVo>> queryPageList(UniversityPosterQueryDto queryDto) {
        String memberId = SecurityUtils.getCurrentUserMemberId();
        queryDto.setPublisherId(memberId);
        List<UniversityPosterVo> universityPosterVos = universityPosterService.queryList(queryDto);
        return new Result<List<UniversityPosterVo>>().result(universityPosterVos);
    }

}
