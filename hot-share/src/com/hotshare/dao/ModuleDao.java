
 /**  
 * Project Name:hot-share  
 * File Name:ModuleDao.java  
 * Package Name:com.hotshare.dao  
 * Date:2014年8月5日下午3:13:44  
 * Copyright (c) 2014, lhzh All Rights Reserved.  
 *  
*/  


  
package com.hotshare.dao;  

import java.util.List;

import com.hotshare.bean.Module;
import com.hotshare.bean.ModuleFunction;
import com.hotshare.exception.DaoException;

/**  
 * ClassName:ModuleDao <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2014年8月5日 下午3:13:44 <br/>  
 * @author   lhzh  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */


public interface ModuleDao<T> extends BaseDao<T> {

	/**   
	 * @Title: queryAllModule   
	 * @Description: TODO  
	 * @param @return  
	 * @return List<Module>  
	 * @throws   
	 */
	public List<Module> queryAllModule()throws DaoException;
	
	
	
	/**
	 * 根据模块获得子模块列表
	 * @Title: queryModuleByParentID   
	 * @Description: TODO  
	 * @param @param Id
	 * @param @return
	 * @param @throws DaoException  
	 * @return List<Module>  
	 * @throws
	 */
	public List<Module> queryModuleByParentID(int Id)throws DaoException;
	
	
	/**
	 * 根据模块ID获得功能列表
	 * @Title: findFunctionByModuleID   
	 * @Description: TODO  
	 * @param @return  
	 * @return List<Function>  
	 * @throws
	 */
	public List<ModuleFunction> findFunctionsByModuleID(int moduleID);
	
	/**
	 * 验证某个角色某个模块下的某个功能的权限
	 * @Title: checkFunctionCode   
	 * @Description: TODO  
	 * @param @param roleID
	 * @param @param moduleID
	 * @param @param functionID
	 * @param @return  
	 * @return boolean  
	 * @throws
	 */
	public boolean checkFunctionCode(int roleID,int moduleID,int functionID);

	
}
  
