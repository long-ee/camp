package com.campgem.modules.user.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.modules.user.dto.MemberDto;
import com.campgem.modules.user.dto.MemberQueryDto;
import com.campgem.modules.user.entity.UserBase;
import com.campgem.modules.user.service.IMemberService;
import com.campgem.modules.user.service.IUserBaseService;
import com.campgem.modules.user.vo.MemberVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@Api(tags="【管理端】用户管理接口")
@Slf4j
@RequestMapping("/api/manage/v1")
public class ManageUserController {

    @Resource
    private IMemberService memberService;
    @Resource
    private IUserBaseService userBaseService;

    /**
     * 分页列表查询
     * @param queryDto
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @ApiOperation(value="用户信息-分页列表查询", notes="B1")
    @GetMapping(value = "/user/pageList")
    public Result<IPage<MemberVo>> queryPageList(MemberQueryDto queryDto,
                                               @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                               @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                               HttpServletRequest req) {
        Result<IPage<MemberVo>> result = new Result<>();
        Page<MemberQueryDto> page = new Page<MemberQueryDto>(pageNo, pageSize);
        IPage<MemberVo> pageList = memberService.queryPageList(page, queryDto);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    @PostMapping(value = "/user/edit")
    @ApiOperation(value="用户信息-编辑用户信息", notes="B11")
    public Result edit(@Valid MemberDto memberDto) {
        memberService.edit(memberDto);
        return Result.ok();
    }

    @GetMapping(value = "/user/details")
    @ApiOperation(value="信息设置-用户基本资料", notes="B1")
    public Result<MemberVo> details(@RequestParam(name="id",required=true) String id) {
        MemberVo memberVo = memberService.queryDetails(id);
        return new Result<MemberVo>().result(memberVo);
    }

    @PostMapping(value = "/user/edit/status")
    @ApiOperation(value="用户信息-编辑用户信息", notes="B11")
    public Result enable(@Valid MemberDto memberDto) {
        memberService.edit(memberDto);
        return Result.ok();
    }


    @PostMapping(value = "/user/enable")
    @ApiOperation(value="用户信息-启用用户", notes="B11")
    public Result enable(@RequestParam(name="userBaseId",required=true) String userBaseId) {
        Result<?> result = new Result<>();
        UserBase userBase = userBaseService.getById(userBaseId);
        if(userBase==null) {
            result.error500("未找到对应实体");
        }else {
            userBase.setUserStatus("1");
            boolean ok = userBaseService.updateById(userBase);
            //TODO 返回false说明什么？
            if(ok) {
                result.success("启用成功!");
            }
        }
        return result;
    }
    @PostMapping(value = "/user/disable")
    @ApiOperation(value="用户信息-禁用用户", notes="B11")
    public Result disable(@RequestParam(name="userBaseId",required=true) String userBaseId) {
        Result<?> result = new Result<>();
        UserBase userBase = userBaseService.getById(userBaseId);
        if(userBase==null) {
            result.error500("未找到对应实体");
        }else {
            userBase.setUserStatus("2");
            boolean ok = userBaseService.updateById(userBase);
            //TODO 返回false说明什么？
            if(ok) {
                result.success("禁用成功!");
            }
        }
        return result;
    }


}
