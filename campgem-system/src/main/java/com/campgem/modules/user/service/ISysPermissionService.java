package com.campgem.modules.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.modules.user.entity.SysPermission;
import com.campgem.modules.user.vo.TreeModel;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @Author: campgem
 * @since 2018-12-21
 */
public interface ISysPermissionService extends IService<SysPermission> {
	
	public List<TreeModel> queryListByParentId(String parentId);
	
	/**真实删除*/
	public void deletePermission(String id) throws JeecgBootException;
	/**逻辑删除*/
	public void deletePermissionLogical(String id) throws JeecgBootException;
	
	public void addPermission(SysPermission sysPermission) throws JeecgBootException;
	
	public void editPermission(SysPermission sysPermission) throws JeecgBootException;
	
	public List<SysPermission> queryByUser(String username);
	
	/**
	  * 查询出带有特殊符号的菜单地址的集合
	 * @return
	 */
	public List<String> queryPermissionUrlWithStar();
}
