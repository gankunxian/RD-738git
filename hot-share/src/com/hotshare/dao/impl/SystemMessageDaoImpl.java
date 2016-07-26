package com.hotshare.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotshare.bean.SystemMessage;
import com.hotshare.dao.SystemMessageDao;
import com.hotshare.exception.DaoException;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;

/**
 * @author  lhzh
 * @version 创建时间：2014-8-19 下午08:00:42
 * 类说明
 */
@Repository("systemMessageDao")
public class SystemMessageDaoImpl<T> extends BaseDaoImpl<T> implements SystemMessageDao<T> {

	@Override
	public Long getSystemMessageTotal() throws DaoException {
		StringBuffer hql = new StringBuffer("select count(*) from SystemMessage");
		Long total = this.count(hql.toString(), null);
		return total;
	}

	@Override
	public List<SystemMessage> findAllSystemMessage(OrderBean order, PageBean page)
			throws DaoException {
		List<SystemMessage> beanlist = null;
		StringBuffer hql = new StringBuffer("from SystemMessage ");
		if (order.getSort() != null && order.getOrder() != null) {
			hql.append(" order by ");
			hql.append(order.getSort());
			hql.append(" ");
			hql.append(order.getOrder());
			hql.append(" ,createTime desc ");
		}

		beanlist = (List<SystemMessage>) this.find(hql.toString(), page.getPage(), page.getRows(), null);
		return beanlist;
	}
	
	

	@Override
	public void batchDelete(String ids) throws DaoException {
		StringBuffer sb = new StringBuffer("delete from SystemMessage where id in(");
		sb.append(ids);
		sb.append(")");
		this.executeHql(sb.toString());
	}

	@Override
	public List<SystemMessage> findAllSystemMessage() throws DaoException {
		List<SystemMessage> beanlist = null;
		StringBuffer hql = new StringBuffer("from SystemMessage ");
		return (List<SystemMessage>) this.find(hql.toString(), null);
	}

	
}
