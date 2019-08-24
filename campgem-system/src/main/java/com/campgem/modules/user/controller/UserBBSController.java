package com.campgem.modules.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.bbs.dto.PostQueryDto;
import com.campgem.modules.bbs.service.IPostService;
import com.campgem.modules.bbs.vo.PostVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags="我的资源（论坛）管理接口")
@Slf4j
@RequestMapping("/api/v1")
public class UserBBSController {
    @Autowired
    private IPostService postService;

    @ApiOperation(value="我的资源（论坛）管理-论坛版块列表", notes="G16 论坛版块列表")
    @GetMapping(value = "/user/post/list")
    public Result<IPage<PostVo>> queryPageList(PostQueryDto queryDto,
                                               @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                               @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                               HttpServletRequest req) {
        String memberId = SecurityUtils.getCurrentUserMemberId();
        queryDto.setAdminId(memberId);
        Page<PostQueryDto> page = new Page<PostQueryDto>(pageNo, pageSize);
        IPage<PostVo> postVos = postService.queryPageList(page, queryDto);
        return new Result<IPage<PostVo>>().result(postVos);
    }
}
