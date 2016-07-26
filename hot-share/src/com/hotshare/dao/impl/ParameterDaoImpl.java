/**
 * Program  : ParameterDaoImpl.java
 * Author   : gkx
 * Create   : 2014-8-12 下午2:12:58
 */

package com.hotshare.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.hotshare.bean.Parameter;
import com.hotshare.bean.ParameterDetail;
import com.hotshare.bean.User;
import com.hotshare.dao.ParameterDao;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;


/**
 * 
 * @author gkx
 * @version 1.0.0
 * @2014-8-12 下午2:12:58
 */
@Repository("parameterDao")
public class ParameterDaoImpl<T> extends BaseDaoImpl<T> implements ParameterDao<T> {

	public Parameter getParameterByParameterName(String parameterName) {
		String hql = "FROM Parameter WHERE parameterName = ?";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter(0, parameterName);
		List<Parameter> list = query.list();
		return list == null || list.isEmpty() ? null : list.get(0);
	}

	/**查询有效的系统参数总数  gkx*/
	@Override
	public long finAllParameterTotal() {
		StringBuffer hql = new StringBuffer("select count(*) from Parameter where state = 0 ");
		Long total = this.count(hql.toString(), null);
		return total;
	}

	/**查找有效的系统参数  gkx */
	@Override
	@SuppressWarnings("unchecked")
	public List<Parameter> finAllParameter(OrderBean order, PageBean page) {
		List<Parameter> beanlist = null;
		StringBuffer hql = new StringBuffer("from Parameter where state = 0 ");
		if (order.getSort() != null && order.getOrder() != null) {
			hql.append(" order by ");
			hql.append(order.getSort());
			hql.append(" ");
			hql.append(order.getOrder());
			hql.append(" , modifyTime desc ");
		}

		beanlist = (List<Parameter>) this.find(hql.toString(), page.getPage(), page.getRows(), null);
		return beanlist;
	}


	/**删除系统参数 gkx*/
	public void batchDelete(String ids) {
		StringBuffer sb = new StringBuffer("delete from Parameter where parameterId in(");
		sb.append(ids);
		sb.append(")");
		this.executeHql(sb.toString());
	}

	@Override
	public long findParameterDetailTotal(int parameterId) {
		StringBuffer hql = new StringBuffer("select count(*) from ParameterDetail where state = 0 and parameter.parameterId = ?");
		List<Object> list = new ArrayList<Object>();
		list.add(parameterId);
		Long total = this.count(hql.toString(), list);
		return total;
	}

	@Override
	public List<ParameterDetail> findParameterDetailByPId(OrderBean order,PageBean page, int parameterId) {
		List<ParameterDetail> beanlist = null;
		StringBuffer hql = new StringBuffer("from ParameterDetail where state = 0 and parameter.parameterId ="+parameterId);
		if (order.getSort() != null && order.getOrder() != null) {
			hql.append(" order by ");
			hql.append(order.getSort());
			hql.append(" ");
			hql.append(order.getOrder());
			hql.append(" , modifyTime desc "); 
		}
		beanlist = (List<ParameterDetail>) this.find(hql.toString(), page.getPage(), page.getRows(), null);
		return beanlist;
	}

	/**删除详细参数*/
	public void batchDelParameterDetail(String ids) {
		StringBuffer sb = new StringBuffer("delete from ParameterDetail where detailId in(");
		sb.append(ids);
		sb.append(")");
		this.executeHql(sb.toString());
	}

	/**通过id查出对象*/
	public Parameter getParameterById(int parameterId) {
		return (Parameter) this.get((Class<T>) Parameter.class, parameterId);
	}

	
	/**通过名称和父id找到ParameterDetail对象*/
	public ParameterDetail getParameterDetailByDetailNameAndPId(String detailName, int parameterId) {
		String hql = "FROM ParameterDetail where state=0 and detailName = ? and parameter.parameterId = ?";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter(0, detailName);
		query.setParameter(1, parameterId);
		List<ParameterDetail> list = query.list();
		return list == null || list.isEmpty() ? null : list.get(0);
	}
}
