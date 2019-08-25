package com.campgem.modules.user.controller.manage;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.common.system.vo.LoginUser;
import com.campgem.common.util.PasswordUtil;
import com.campgem.common.util.oConvertUtils;
import com.campgem.modules.user.dto.SysUserDto;
import com.campgem.modules.user.entity.SysUser;
import com.campgem.modules.user.service.ISysUserService;
import com.campgem.modules.user.vo.SysUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @Author: campgem
 * @since 2018-12-20
 */
@RestController
@RequestMapping("/api/manage/v1")
@Api(tags="账户管理接口")
@Slf4j
public class ManageAccountController {

	@Autowired
	private ISysUserService sysUserService;

	@GetMapping(value = "/account/pageList")
    @ApiOperation(value="账户管理-账户分页列表", notes="I2")
	public Result<IPage<SysUserVo>> queryPageList(@RequestParam(name="accountName") String accountName,
                                                @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                @RequestParam(name="pageSize", defaultValue="10") Integer pageSize, HttpServletRequest req) {
		Result<IPage<SysUserVo>> result = new Result<IPage<SysUserVo>>();
        Page<SysUser> page = new Page<>(pageNo, pageSize);
		IPage<SysUserVo> pageList = sysUserService.queryPageList(page, accountName);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	@PostMapping(value = "/account/add")
    @ApiOperation(value="账户管理-新增用户", notes="I21")
	public Result<?> add(@Valid SysUserDto sysUserDto) {
		Result<SysUser> result = new Result<SysUser>();
		if(StringUtils.isBlank(sysUserDto.getRoleId())){
            result.error500("请选择用户角色");
        }
		String selectedRoles = sysUserDto.getRoleId();
		try {
			SysUser user = new SysUser();
            user.setUsername(sysUserDto.getUsername());
            user.setPassword(sysUserDto.getPassword());
            user.setCityIds(sysUserDto.getCityIds());
			user.setCreateTime(new Date());//设置创建时间
			String salt = oConvertUtils.randomGen(8);
			user.setSalt(salt);
			String passwordEncode = PasswordUtil.encrypt(user.getUsername(), user.getPassword(), salt);
			user.setPassword(passwordEncode);
			user.setStatus(1);
			user.setDelFlag("0");
			sysUserService.addUserWithRole(user, selectedRoles);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.error500("操作失败");
		}
		return result;
	}

    @PostMapping(value = "/account/edit")
    @ApiOperation(value="账户管理-编辑用户", notes="I21")
	public Result<SysUser> edit(@Valid SysUserDto sysUserDto) {
		Result<SysUser> result = new Result<SysUser>();
		try {
			SysUser sysUser = sysUserService.getById(sysUserDto.getId());
			if(sysUser==null) {
				result.error500("未找到对应实体");
			}else {
                sysUser.setUpdateTime(new Date());
                sysUser.setPassword(sysUser.getPassword());
                sysUser.setCityIds(sysUserDto.getCityIds());
				String roles = sysUserDto.getRoleId();
				sysUserService.editUserWithRole(sysUser, roles);
				result.success("修改成功!");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.error500("操作失败");
		}
		return result;
	}

    @PostMapping(value = "/account/delete")
    @ApiOperation(value="账户管理-删除用户", notes="I2")
	public Result<SysUser> delete(@RequestParam(name="id",required=true) String id) {
		Result<SysUser> result = new Result<SysUser>();
		SysUser sysUser = sysUserService.getById(id);
		if(sysUser==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = sysUserService.removeById(id);
			if(ok) {
				result.success("删除成功!");
			}
		}
		return result;
	}

    @GetMapping(value = "/account/details")
    @ApiOperation(value="账户管理-查询用户详情", notes="I21")
    public Result<SysUserVo> queryById(@RequestParam(name = "id", required = true) String id) {
        Result<SysUserVo> result = new Result<SysUserVo>();
        SysUserVo sysUserVo = sysUserService.queryUserDetails(id);
        if (sysUserVo == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(sysUserVo);
            result.setSuccess(true);
        }
        return result;
    }


    @PostMapping(value = "/account/changPassword")
    @ApiOperation(value="账户管理-用户自己修改密码", notes="I2")
    public Result<?> changPassword(@RequestParam(name = "oldPassword", required = true) String oldPassword,
                                   @RequestParam(name = "newPassword", required = true) String newPassword) {
        Result<SysUser> result = new Result<SysUser>();
        Subject subject = SecurityUtils.getSubject();
        LoginUser loginUser = (LoginUser)subject.getPrincipal();
        SysUser sysUser = sysUserService.getById(loginUser.getId());
        if (sysUser == null) {
            result.error500("未找到对应实体");
        } else {
            String passwordEncode = PasswordUtil.encrypt(sysUser.getUsername(), oldPassword, sysUser.getSalt());
            if(!sysUser.getPassword().equals(passwordEncode)) {
                result.error500("旧密码输入错误!");
                return result;
            }
            String salt = oConvertUtils.randomGen(8);
            sysUser.setSalt(salt);
            String newPasswordEncode = PasswordUtil.encrypt(sysUser.getUsername(), newPassword, salt);
            sysUser.setPassword(newPasswordEncode);
            this.sysUserService.updateById(sysUser);
            result.success("密码修改完成！");
        }
        return result;
    }

    @PostMapping(value = "/account/resetPassword")
    @ApiOperation(value="账户管理-管理员重置密码", notes="I2")
	public Result<SysUser> updatePassword(@RequestParam(name = "id", required = true) String id) {
		Result<SysUser> result = new Result<SysUser>();
		SysUser user = this.sysUserService.getById(id);
		if(user==null) {
			result.error500("未找到用户!");
			return result;
		}
		String passwordEncode = PasswordUtil.encrypt(user.getUsername(), "123456", user.getSalt());
        user.setPassword(passwordEncode);
        this.sysUserService.updateById(user);
		result.success("密码重置完成！");
		return result;
	}

}
