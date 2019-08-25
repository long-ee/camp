package com.campgem.modules.user.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.IdentifyInfo;
import com.campgem.common.api.vo.Result;
import com.campgem.common.system.util.JwtUtil;
import com.campgem.modules.user.dto.MemberQueryDto;
import com.campgem.modules.user.dto.UserPasswordModifyDto;
import com.campgem.modules.user.entity.Member;
import com.campgem.modules.user.service.IMemberService;
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

    /**
     * 分页列表查询
     * @param queryDto
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @ApiOperation(value="用户信息-分页列表查询", notes="用户信息-分页列表查询")
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

    /**
     *   添加
     * @param member
     * @return
     */
    @ApiOperation(value="用户信息-添加", notes="用户信息-添加")
    @PostMapping(value = "/user/add")
    public Result<Member> add(@Valid Member member) {
        Result<Member> result = new Result<Member>();
        try {
            memberService.save(member);
            result.success("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            result.error500("操作失败");
        }
        return result;
    }

    /**
     *  编辑
     * @param member
     * @return
     */
    @ApiOperation(value="用户信息-编辑", notes="用户信息-编辑")
    @PostMapping(value = "/user/edit")
    public Result<Member> edit(@Valid Member member) {
        Result<Member> result = new Result<Member>();
        Member memberEntity = memberService.getById(member.getId());
        if(memberEntity==null) {
            result.error500("未找到对应实体");
        }else {
            boolean ok = memberService.updateById(member);
            //TODO 返回false说明什么？
            if(ok) {
                result.success("修改成功!");
            }
        }

        return result;
    }

    /**
     *   通过id删除
     * @param id
     * @return
     */
    @ApiOperation(value="用户信息-通过id删除", notes="用户信息-通过id删除")
    @PostMapping(value = "/user/delete")
    public Result<?> delete(@RequestParam(name="id",required=true) String id) {
        try {
            memberService.removeById(id);
        } catch (Exception e) {
            log.error("删除失败",e.getMessage());
            return Result.error("删除失败!");
        }
        return Result.ok("删除成功!");
    }



    /**
     * 通过id查询
     * @param id
     * @return
     */
    @ApiOperation(value="用户信息-通过id查询", notes="用户信息-通过id查询")
    @GetMapping(value = "/user/details")
    public Result<MemberVo> queryDetails(@RequestParam(name="id",required=true) String id) {
        Result<MemberVo> result = new Result<MemberVo>();
        MemberVo member = memberService.queryDetails(id);
        if(member==null) {
            result.error500("未找到对应实体");
        }else {
            result.setResult(member);
            result.setSuccess(true);
        }
        return result;
    }

}
