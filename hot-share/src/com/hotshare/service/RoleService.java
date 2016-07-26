package com.hotshare.service;

import java.util.List;

import com.hotshare.bean.Function;
import com.hotshare.bean.Role;
import com.hotshare.exception.ServiceException;
import com.hotshare.json.bean.ModuleTree;

/**
 * @author  lhzh
 * @version 创建时间：2014-8-11 下午02:03:25
 * 类说明
 */
public interface RoleService {
	
	/**
	 * 获取角色列表
	 * @return
	 */
	public List<Role> queryAllRole()throws ServiceException;
	
    /**分页获取角色列表
     * @param cp
     * @param ps
     * @return
     */
	public List<Role> queryRoleBySplit(Integer cp, Integer ps)throws ServiceException;
 
	/**
	 * 根据角色ID获取角色
	 * @param roleID
	 * @return
	 */
	public Role getRoleByID(Integer roleID)throws ServiceException;
	
	/**
	 * 根据角色获取操作权限列表
	 * @param id
	 * @return
	 */
	public List<Function> getAuthCodeByRole(int id)throws ServiceException;

	/**
	 * 修改角色
	 * @Title: modRole   
	 * @Description: TODO  
	 * @param @param role
	 * @param @param moduleFunctions  
	 * @return void  
	 * @throws
	 */
	public boolean modRole(Role role, String moduleFunctions)throws ServiceException;
	/**
	 * 添加角色
	 * @Title: addRole   
	 * @Description: TODO  
	 * @param @param role
	 * @param @param moduleFunctions
	 * @param @return
	 * @param @throws ServiceException  
	 * @return boolean  
	 * @throws
	 */
	public boolean addRole(Role role, String moduleFunctions) throws ServiceException;
	
	/**
	 * 删除角色 
	 * @Title: delRole   
	 * @Description: TODO  
	 * @param @param id
	 * @param @return
	 * @param @throws ServiceException  
	 * @return boolean  
	 * @throws
	 */
	public boolean delRole(int id) throws ServiceException;
	
}
