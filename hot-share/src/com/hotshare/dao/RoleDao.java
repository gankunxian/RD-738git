package com.hotshare.dao;

import java.util.List;

import com.hotshare.bean.Role;
import com.hotshare.exception.DaoException;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;

/**
 * @author  lhzh
 * @version 创建时间：2014-8-11 下午02:49:15
 * 类说明
 */
public interface RoleDao<T> extends BaseDao<T> {
	/**
	 *查询角色列表
	 * @Title: findRoles   
	 * @Description: TODO  
	 * @param @param order
	 * @param @return
	 * @param @throws DaoException  
	 * @return List<Role>  
	 * @throws
	 */
	public List<Role> findRoles()throws DaoException;
	
	/**
	 * 查询角色列表（分页）
	 * @Title: findRolesByPage   
	 * @Description: TODO  
	 * @param @param order
	 * @param @param page
	 * @param @return
	 * @param @throws DaoException  
	 * @return List<Role>  
	 * @throws
	 */
	public List<Role> findRolesByPage(OrderBean order, PageBean page)throws DaoException;

}
