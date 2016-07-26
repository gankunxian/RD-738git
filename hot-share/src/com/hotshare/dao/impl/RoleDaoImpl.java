package com.hotshare.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotshare.bean.Role;
import com.hotshare.dao.RoleDao;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;

/**
 * @author  lhzh
 * @version 创建时间：2014-8-11 下午02:50:53
 * 类说明
 */
@Repository("roleDao")
public class RoleDaoImpl<T> extends BaseDaoImpl<T> implements RoleDao<T> {

	
	@SuppressWarnings("unchecked")
	public List<Role> findRolesByPage(OrderBean order, PageBean page){
		List<Role> beanlist = new ArrayList<Role>();
		StringBuffer hql = new StringBuffer("from Role");
		if (order.getSort() != null && order.getOrder() != null) {
			hql.append(" order by ");
			hql.append(order.getSort());
			hql.append(" ");
			hql.append(order.getOrder());
		}

		beanlist = (List<Role>) this.find(hql.toString(), page.getPage(), page.getRows(), null);
		return beanlist;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findRoles() {
		List<Role> beanlist = new ArrayList<Role>();
		StringBuffer hql = new StringBuffer("from Role");
		beanlist = (List<Role>) this.find(hql.toString(), null);
		return beanlist;
		
	}
}
