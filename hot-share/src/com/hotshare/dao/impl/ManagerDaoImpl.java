/**
 * Program  : ManagerDaoImpl.java
 * Author   : lhzh
 * Create   : 2013-1-18 下午2:12:58
 */

package com.hotshare.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.hotshare.bean.Manager;
import com.hotshare.dao.ManagerDao;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;


/**
 * 
 * @author lhzh
 * @version 1.0.0
 * @2013-1-18 下午2:12:58
 */
@Repository("managerDao")
public class ManagerDaoImpl<T> extends BaseDaoImpl<T> implements ManagerDao<T> {

	public Manager getManagerByAccount(String account) {
		String hql = "FROM Manager WHERE account = ?";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter(0, account);
		List<Manager> list = query.list();
		return list == null || list.isEmpty() ? null : list.get(0);
	}

	/**查询有效的系统人员总数  gkx*/
	@Override
	public long finAllManagerTotal() {
		StringBuffer hql = new StringBuffer("select count(*) from Manager where state = 0 ");
		Long total = this.count(hql.toString(), null);
		return total;
	}

	/**查找有效的系统人员  gkx */
	@Override
	@SuppressWarnings("unchecked")
	public List<Manager> finAllManager(OrderBean order, PageBean page) {
		List<Manager> beanlist = null;
		StringBuffer hql = new StringBuffer("from Manager where state = 0 ");
		if (order.getSort() != null && order.getOrder() != null) {
			hql.append(" order by ");
			hql.append(order.getSort());
			hql.append(" ");
			hql.append(order.getOrder());
		}

		beanlist = (List<Manager>) this.find(hql.toString(), page.getPage(), page.getRows(), null);
		return beanlist;
	}


	/**删除系统人员 gkx*/
	public void batchDelete(String ids) {
		StringBuffer sb = new StringBuffer("delete from Manager where managerId in(");
//		StringBuffer sb = new StringBuffer("update Manager set state = -1 where managerId in(");
		sb.append(ids);
		sb.append(")");
		this.executeHql(sb.toString());
	}
}
