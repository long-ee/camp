package com.campgem.modules.user.controller;

import com.campgem.common.api.vo.Result;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.user.dto.MemberDto;
import com.campgem.modules.user.dto.UserPasswordModifyDto;
import com.campgem.modules.user.service.IMemberService;
import com.campgem.modules.user.vo.MemberVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Api(tags="用户管理接口")
@Slf4j
@RequestMapping("/api/v1")
public class UserController {

    @Resource
    private IMemberService memberService;

    @PostMapping(value = "/user/changPassword")
    @ApiOperation(value="信息设置-用户密码修改", notes="G25 用户密码修改")
    public Result changPassword(@Valid UserPasswordModifyDto passwordModifyDto) {
        String email = SecurityUtils.getCurrentUser().getEmail();
        passwordModifyDto.setEmail(email);
        memberService.modifyPassword(passwordModifyDto);
        return Result.ok();
    }

    @GetMapping(value = "/user/details")
    @ApiOperation(value="信息设置-用户基本资料", notes="G18 ~ G21 用户基本资料详情查询")
    public Result<MemberVo> details() {
        String memberId = SecurityUtils.getCurrentUserMemberId();
        MemberVo memberVo = memberService.queryDetails(memberId);
        return new Result<MemberVo>().result(memberVo);
    }

    @PostMapping(value = "/user/edit")
    @ApiOperation(value="信息设置-用户基本资料编辑", notes="G18 ~ G21 用户基本资料编辑")
    public Result edit(@Valid MemberDto memberDto) {
        String memberId = SecurityUtils.getCurrentUserMemberId();
        if(StringUtils.isBlank(memberId)){
            memberDto.setId(memberId);
        }
        memberService.edit(memberDto);
        return Result.ok();
    }

}
