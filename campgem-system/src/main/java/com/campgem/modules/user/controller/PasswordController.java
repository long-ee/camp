package com.campgem.modules.user.controller;

import com.campgem.common.api.vo.Result;
import com.campgem.modules.user.service.IUserAuthService;
import com.campgem.modules.user.dto.PasswordResetCodeVerifyDto;
import com.campgem.modules.user.dto.PasswordResetDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author: campgem
 */
@Slf4j
@Api(tags = "用户密码管理")
@RestController
@RequestMapping("/api/v1")
public class PasswordController {
    @Resource
    private IUserAuthService userAuthService;


    @ApiOperation(value = "用户密码管理-密码重置邮箱验证码获取", notes = "A15 发送验证码")
    @GetMapping(value = "/user/password/reset/validityCode")
    public Result getPasswordResetValidityCode(String email) {
        userAuthService.getPasswordResetValidityCode(email);
        return Result.ok();
    }


    @ApiOperation(value = "用户密码管理-验证邮箱验证码", notes = "A15 重置密码")
    @PostMapping(value = "/user/password/reset/verify")
    public Result validityCode(@Valid PasswordResetCodeVerifyDto verifyDto) {
        userAuthService.passwordResetCodeVerify(verifyDto);
        return Result.ok();
    }

    @ApiOperation(value = "用户密码管理-用户密码重置", notes = "A16 提交")
    @PostMapping(value = "/user/password/reset")
    public Result passwordReset(@Valid PasswordResetDto passwordResetDto) {
        userAuthService.passwordReset(passwordResetDto);
        return Result.ok();
    }



}
