package com.campgem.modules.user.controller;

import com.campgem.common.api.vo.Result;
import com.campgem.common.util.BeanConvertUtils;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.user.dto.LocalLoginDto;
import com.campgem.modules.user.dto.ThirdPartyLoginDto;
import com.campgem.modules.user.entity.enums.IdentityTypeEnum;
import com.campgem.modules.user.service.IMemberService;
import com.campgem.modules.user.service.IUserAuthService;
import com.campgem.modules.user.vo.LoginVo;
import com.campgem.modules.user.vo.MemberVo;
import com.xkcoding.justauth.AuthRequestFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@Api(tags="用户登录API接口")
@Slf4j
@RequestMapping("/api/v1")
public class LoginController {
    @Resource
    private IUserAuthService userAuthService;
    @Resource
    private IMemberService memberService;
    @Resource
    private AuthRequestFactory factory;

    @PostMapping(value = "/account/login")
    @ApiOperation(value="用户登录", notes="用户登录")
    public Result<LoginVo> localLogin(@Valid LocalLoginDto loginDto) {
        LoginVo loginVo = userAuthService.localLogin(loginDto);
        Result<LoginVo> result = new Result<>();
        result.setSuccess(true);
        result.setResult(loginVo);
        return result;
    }

    /**
     * 第三方授权登录，根据Code换区Token
     * @param ThirdPartyLoginDto
     * @return
     */
    @PostMapping(value = "/account/thirdParty/login")
    @ApiOperation(value="第三方平台登录", notes="第三方平台登录")
    public Result<LoginVo> thirdPartyLogin(@Valid ThirdPartyLoginDto ThirdPartyLoginDto) {
        LoginVo loginVo = userAuthService.thirdPartyLogin(ThirdPartyLoginDto);
        Result<LoginVo> result = new Result<>();
        result.setSuccess(true);
        result.setResult(loginVo);
        return result;
    }

    /**
     * 登录用户详情查询
     * @return
     */
    @GetMapping(value = "/user/info")
    @ApiOperation(value="登录用户详情查询", notes="登录用户详情查询")
    public Result<MemberVo> userInfo(){
        Result<MemberVo> result = new Result<MemberVo>();
        String memberId = SecurityUtils.getCurrentUserMemberId();
        MemberVo member = memberService.queryDetails(memberId);
        if(member==null) {
            result.error500("未找到对应实体");
        }else {
            result.setResult(member);
            result.setSuccess(true);
        }
        return result;
    }

    /**
     * 重定向授权页面
     * @param authType
     * @param response
     * @throws IOException
     */
    @GetMapping("/oauth/login/{authType}")
    public void oauthLogin(@PathVariable(name = "authType") String authType, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = factory.get(AuthSource.valueOf(authType.toUpperCase()));
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }


    /**
     * 授权回调地址
     * @param authCallback
     * @return
     */
    @RequestMapping(value = "/oauth/{authType}/callback")
    @ApiOperation(value="授权回调地址", notes="授权回调地址")
    public Result<LoginVo> oauthLoginCallBack(@PathVariable(name = "authType") String authType, AuthCallback authCallback) {
        ThirdPartyLoginDto loginDto = BeanConvertUtils.convertBean(authCallback, ThirdPartyLoginDto.class);
        loginDto.setIdentityType(IdentityTypeEnum.valueOf(authType.toUpperCase()).code());
        LoginVo loginVo = userAuthService.thirdPartyLogin(loginDto);
        Result<LoginVo> result = new Result<>();
        result.setSuccess(true);
        result.setResult(loginVo);
        return result;
    }
}
