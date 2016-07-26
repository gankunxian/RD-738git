/**  
 * Project Name:hot-share  
 * File Name:ModuleDaoImpl.java  
 * Package Name:com.hotshare.dao.impl  
 * Date:2014年8月5日下午3:15:38  
 * Copyright (c) 2014, lhzh All Rights Reserved.  
 *  
 */

package com.hotshare.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotshare.bean.Module;
import com.hotshare.bean.ModuleFunction;
import com.hotshare.dao.ModuleDao;
import com.hotshare.exception.DaoException;

/**
 * ClassName:ModuleDaoImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014年8月5日 下午3:15:38 <br/>
 * 
 * @author lhzh
 * @version
 * @since JDK 1.6
 * @see
 */
@Repository("moduleDao")
public class ModuleDaoImpl<T> extends BaseDaoImpl<T> implements ModuleDao<T> {

	@SuppressWarnings("unchecked")
	public List<Module> queryAllModule() {
		String hql = "from Module ";
		List<Module> modules = (List<Module>) find(hql, null);
		return modules;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Module> queryModuleByParentID(int Id) throws DaoException {
		StringBuffer hql = new StringBuffer("from Module where parentID =");
		hql.append("'" +Id  + "'");
		List<Module> modules = (List<Module>) find(hql.toString(), null);
		return modules;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ModuleFunction> findFunctionsByModuleID(int moduleID) {
		StringBuffer hql = new StringBuffer("from ModuleFunction mf where mf.module.id = ");
		hql.append("'" +moduleID  + "'");
		List<ModuleFunction> modules = (List<ModuleFunction>) find(hql.toString(), null);
		return modules;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkFunctionCode(int roleID, int moduleID, int functionID) {
		StringBuffer hql = new StringBuffer("select mf from ModuleFunction mf,RoleModuleFunction rmf where mf.id = rmf.moduleFunction.id AND rmf.role.id=");
		hql.append("'" +roleID  + "'");
		hql.append(" AND mf.module.id = ");
		hql.append("'" +moduleID  + "'");
		hql.append(" AND mf.function.id = ");
		hql.append("'" +functionID  + "'");
		List<ModuleFunction> modules = (List<ModuleFunction>) find(hql.toString(), null);
		return modules.size()>0?true:false;
	}
}
