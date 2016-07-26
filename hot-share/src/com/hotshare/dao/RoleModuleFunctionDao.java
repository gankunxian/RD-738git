package com.hotshare.dao;

import com.hotshare.exception.DaoException;

/**
 * @author  lhzh
 * @version 创建时间：2014-8-13 上午09:36:08
 * 类说明
 */
public interface RoleModuleFunctionDao<T> extends BaseDao<T>{
	
	/**
	 * 删除某个角色的全部权限
	 * @Title: removeModuleFunctionByRoleID   
	 * @Description: TODO  
	 * @param @param roleID
	 * @param @throws DaoException  
	 * @return void  
	 * @throws
	 */
	public void removeModuleFunctionByRoleID(int roleID)throws DaoException;

}
