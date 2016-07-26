/**
 * Program  : UserDaoImpl.java
 * Author   : lhzh
 * Create   : 2013-1-18 下午2:12:58
 */

package com.hotshare.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.hotshare.bean.User;
import com.hotshare.dao.UserDao;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;


/**
 * 
 * @author lhzh
 * @version 1.0.0
 * @2013-1-18 下午2:12:58
 */
@Repository("userDao")
public class UserDaoImpl<T> extends BaseDaoImpl<T> implements UserDao<T> {

	public User getUserByAccount(String account) {
		String hql = "FROM User WHERE account = ?";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter(0, account);
		List<User> list = query.list();
		return list == null || list.isEmpty() ? null : list.get(0);
	}

	/**查询所有的前端用户总数  gkx*/
	@Override
	public long finAllUserTotal(String account,String groupId,String state) {
		StringBuffer hql = new StringBuffer("select count(*) from User where 1=1 ");
		List<Object> list = new ArrayList<Object>();
		if(account!=null&&!"".equals(account)){
			hql.append(" and account like ?");
			list.add("%"+account+"%");
		}
		if(groupId!=null&&!"".equals(groupId)){
			hql.append(" and group.groupId = ?");
			list.add(Integer.parseInt(groupId));
		}
		if(state!=null&&!"".equals(state)){
			hql.append(" and state = ?");
			list.add(Short.parseShort(state));
		}
		Long total = this.count(hql.toString(), list);
		return total;
	}

	/**查找有效的前端用户  gkx */
	@Override
	@SuppressWarnings("unchecked")
	public List<User> finAllUser(OrderBean order, PageBean page,String account,String groupId,String state) {
		List<User> beanlist = null;
		StringBuffer hql = new StringBuffer("from User where 1=1 ");
		List<Object> list = new ArrayList<Object>();
		if(account!=null&&!"".equals(account)){
			hql.append(" and account like ?");
			list.add("%"+account+"%");
		}
		if(groupId!=null&&!"".equals(groupId)){
			hql.append(" and group.groupId = ?");
			list.add(Integer.parseInt(groupId));
		}
		if(state!=null&&!"".equals(state)){
			hql.append(" and state = ?");
			list.add(Short.parseShort(state));
		}
		if (order.getSort() != null && order.getOrder() != null) {
			hql.append(" order by ");
			hql.append(order.getSort());
			hql.append(" ");
			hql.append(order.getOrder());
		}

		beanlist = (List<User>) this.find(hql.toString(), page.getPage(), page.getRows(), list);
		return beanlist;
	}


	/**修改前端用户状态 gkx*/
	public void doUserState(String ids,int state) {
		int doState = -1;//原来是正常的,说明要禁用
		if(state==-1){//否则,说明要启用
			doState = 0;
		}
		StringBuffer sb = new StringBuffer("update User set state =");
		sb.append(doState);
		sb.append(" where userId in(");
		sb.append(ids);
		sb.append(")");
		this.executeHql(sb.toString());
	}
}
