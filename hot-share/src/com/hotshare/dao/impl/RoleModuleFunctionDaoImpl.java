package com.hotshare.dao.impl;

import org.springframework.stereotype.Repository;

import com.hotshare.dao.RoleModuleFunctionDao;
import com.hotshare.exception.DaoException;

/**
 * @author  lhzh
 * @version 创建时间：2014-8-13 上午09:36:44
 * 类说明
 */
@Repository("roleModuleFunctionDao")
public class RoleModuleFunctionDaoImpl<T> extends BaseDaoImpl<T> implements RoleModuleFunctionDao<T> {

	@Override
	public void removeModuleFunctionByRoleID(int roleID) throws DaoException {
		StringBuffer sb = new StringBuffer("delete from RoleModuleFunction rmf where rmf.role.id = ");
		sb.append(roleID);
		sb.append(")");
		this.executeHql(sb.toString());
	}

}
