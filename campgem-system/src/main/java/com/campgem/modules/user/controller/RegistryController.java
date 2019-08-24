package com.campgem.modules.user.controller;

import com.campgem.common.api.vo.Result;
import com.campgem.common.system.util.JwtUtil;
import com.campgem.common.api.vo.IdentifyInfo;
import com.campgem.modules.user.dto.UserRegistrationDto;
import com.campgem.modules.user.service.IMemberService;
import com.campgem.modules.user.vo.MemberVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@Api(tags="用户注册API接口")
@Slf4j
@RequestMapping("/api/v1")
public class RegistryController {
    @Resource
    private IMemberService memberService;


    @GetMapping(value = "/register/email/validityCode")
    @ApiOperation(value="getEmailValidityCode", notes="A11 ~ A14 发送验证码")
    public Result getEmailValidityCode(String email) {
        memberService.getEmailValidityCode(email);
        Result result = new Result<>();
        result.setSuccess(true);
        return result;
    }

    @PostMapping(value = "/register")
    @ApiOperation(value="register", notes="A11 ~ A14 提交用户注册信息")
    public Result<MemberVo> register(@Valid UserRegistrationDto userRegistrationDto, HttpServletRequest request) {
        IdentifyInfo identifyInfo = JwtUtil.getIdentifierByToken(request);
        userRegistrationDto.setIdentifyInfo(identifyInfo);
        MemberVo memberVo = memberService.registration(userRegistrationDto);
        Result<MemberVo> result = new Result<>();
        result.setSuccess(true);
        result.setResult(memberVo);
        return result;
    }
}
