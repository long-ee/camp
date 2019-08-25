package com.campgem.modules.user.controller.manage;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campgem.common.api.vo.Result;
import com.campgem.common.constant.CacheConstant;
import com.campgem.common.constant.CommonConstant;
import com.campgem.common.system.query.QueryGenerator;
import com.campgem.common.util.oConvertUtils;
import com.campgem.modules.user.entity.SysPermission;
import com.campgem.modules.user.entity.SysRole;
import com.campgem.modules.user.entity.SysRolePermission;
import com.campgem.modules.user.service.ISysPermissionService;
import com.campgem.modules.user.service.ISysRolePermissionService;
import com.campgem.modules.user.service.ISysRoleService;
import com.campgem.modules.user.vo.TreeModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @Author: campgem
 * @since 2018-12-19
 */
@RestController
@RequestMapping("/api/manage/v1")
@Api(tags="角色管理接口")
@Slf4j
public class ManageRoleController {
	@Autowired
	private ISysRoleService sysRoleService;

	@Autowired
	private ISysRolePermissionService sysRolePermissionService;
	
	@Autowired
	private ISysPermissionService sysPermissionService;


	@GetMapping(value = "/role/pageList")
	@ApiOperation(value="角色管理-账户分页列表", notes="I1")
	public Result<IPage<SysRole>> queryPageList(SysRole role,
                                                @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                HttpServletRequest req) {
		Result<IPage<SysRole>> result = new Result<IPage<SysRole>>();
		QueryWrapper<SysRole> queryWrapper = QueryGenerator.initQueryWrapper(role, req.getParameterMap());
		Page<SysRole> page = new Page<SysRole>(pageNo, pageSize);
		IPage<SysRole> pageList = sysRoleService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	@PostMapping(value = "/role/add")
	@ApiOperation(value="角色管理-添加角色", notes="I11")
	public Result<SysRole> add(SysRole role) {
		Result<SysRole> result = new Result<SysRole>();
		try {
			role.setCreateTime(new Date());
			sysRoleService.save(role);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.error500("操作失败");
		}
		return result;
	}

	@PostMapping(value = "/role/edit")
	@ApiOperation(value="角色管理-编辑角色", notes="I11")
	public Result<SysRole> edit(SysRole role) {
		Result<SysRole> result = new Result<SysRole>();
		SysRole sysrole = sysRoleService.getById(role.getId());
		if(sysrole==null) {
			result.error500("未找到对应实体");
		}else {
			role.setUpdateTime(new Date());
			boolean ok = sysRoleService.updateById(role);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}
		
		return result;
	}

	@CacheEvict(value= CacheConstant.LOGIN_USER_RULES_CACHE, allEntries=true)
	@PostMapping(value = "/role/delete")
	@ApiOperation(value="角色管理-删除角色", notes="I1")
	public Result<SysRole> delete(@RequestParam(name="id",required=true) String id) {
		Result<SysRole> result = new Result<SysRole>();
		SysRole sysrole = sysRoleService.getById(id);
		if(sysrole==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = sysRoleService.removeById(id);
			if(ok) {
				result.success("删除成功!");
			}
		}
		
		return result;
	}

	@GetMapping(value = "/role/details")
	@ApiOperation(value="角色管理-角色详情信息", notes="I11")
	public Result<SysRole> queryById(@RequestParam(name="id",required=true) String id) {
		Result<SysRole> result = new Result<SysRole>();
		SysRole sysrole = sysRoleService.getById(id);
		if(sysrole==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(sysrole);
			result.setSuccess(true);
		}
		return result;
	}

	@GetMapping(value = "/role/list")
	@ApiOperation(value="角色管理-角色列表", notes="I21")
	public Result<List<SysRole>> list() {
		Result<List<SysRole>> result = new Result<>();
		List<SysRole> list = sysRoleService.list();
		if(list==null||list.size()<=0) {
			result.error500("未找到角色信息");
		}else {
			result.setResult(list);
			result.setSuccess(true);
		}
		return result;
	}
	

	@GetMapping(value = "/menu/queryTreeList")
	@ApiOperation(value="角色管理-用户角色授权功能，查询菜单权限树", notes="I21")
	public Result<Map<String,Object>> queryTreeList(HttpServletRequest request) {
		Result<Map<String,Object>> result = new Result<>();
		//全部权限ids
		List<String> ids = new ArrayList<>();
		try {
			LambdaQueryWrapper<SysPermission> query = new LambdaQueryWrapper<SysPermission>();
			query.eq(SysPermission::getDelFlag, CommonConstant.DEL_FLAG_0);
			query.orderByAsc(SysPermission::getSortNo);
			List<SysPermission> list = sysPermissionService.list(query);
			for(SysPermission sysPer : list) {
				ids.add(sysPer.getId());
			}
			List<TreeModel> treeList = new ArrayList<>();
			getTreeModelList(treeList, list, null);
			Map<String,Object> resMap = new HashMap<String,Object>();
			resMap.put("treeList", treeList); //全部树节点数据
			resMap.put("ids", ids);//全部树ids
			result.setResult(resMap);
			result.setSuccess(true);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}
	
	private void getTreeModelList(List<TreeModel> treeList,List<SysPermission> metaList,TreeModel temp) {
		for (SysPermission permission : metaList) {
			String tempPid = permission.getParentId();
			TreeModel tree = new TreeModel(permission.getId(), tempPid, permission.getName(),permission.getRuleFlag(), permission.isLeaf());
			if(temp==null && oConvertUtils.isEmpty(tempPid)) {
				treeList.add(tree);
				if(!tree.getIsLeaf()) {
					getTreeModelList(treeList, metaList, tree);
				}
			}else if(temp!=null && tempPid!=null && tempPid.equals(temp.getKey())){
				temp.getChildren().add(tree);
				if(!tree.getIsLeaf()) {
					getTreeModelList(treeList, metaList, tree);
				}
			}
			
		}
	}


	@GetMapping(value = "/role/queryRolePermission")
	@ApiOperation(value="角色管理-查询角色菜单权限配置信息", notes="I12")
	public Result<List<String>> queryRolePermission(@RequestParam(name = "roleId", required = true) String roleId) {
		Result<List<String>> result = new Result<>();
		try {
			List<SysRolePermission> list = sysRolePermissionService.list(new QueryWrapper<SysRolePermission>().lambda().eq(SysRolePermission::getRoleId, roleId));
			result.setResult(list.stream().map(SysRolePermission -> String.valueOf(SysRolePermission.getPermissionId())).collect(Collectors.toList()));
			result.setSuccess(true);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}

	@PostMapping(value = "/role/saveRolePermission")
	@ApiOperation(value="角色管理-菜单权限配置", notes="I12")
	public Result<String> saveRolePermission(@RequestBody JSONObject json) {
		long start = System.currentTimeMillis();
		Result<String> result = new Result<>();
		try {
			String roleId = json.getString("roleId");
			String permissionIds = json.getString("permissionIds");
			String lastPermissionIds = json.getString("lastpermissionIds");
			this.sysRolePermissionService.saveRolePermission(roleId, permissionIds, lastPermissionIds);
			result.success("保存成功！");
			log.info("======角色授权成功=====耗时:" + (System.currentTimeMillis() - start) + "毫秒");
		} catch (Exception e) {
			result.error500("授权失败！");
			log.error(e.getMessage(), e);
		}
		return result;
	}

}
