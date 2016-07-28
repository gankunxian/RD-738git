/**
 * Program  : BaseDaoImpl.java
 * Author   : lhzh
 * Create   : 2013-3-29 下午2:53:26
 */

package com.hotshare.dao.impl;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hotshare.dao.BaseDao;


/**
 * 基础数据库操作类
 * 
 * @author lhzh
 * @version 1.0.0
 * @2013-3-29 下午2:53:26
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 保存数据对象
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午2:54:40
	 * @since
	 * @param obj
	 */
	public void save(T obj) {
		this.getCurrentSession().save(obj);
	}

	/**
	 * 更新数据对象
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午2:54:50
	 * @since
	 * @param obj
	 */
	public void update(T obj) {
		this.getCurrentSession().update(obj);
	}

	/**
	 * 保存或更新数据对象
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午2:54:57
	 * @since
	 * @param obj
	 */
	public void saveOrUpdate(T obj) {
		this.getCurrentSession().saveOrUpdate(obj);
	}

	/**
	 * 删除数据对象
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午2:55:05
	 * @since
	 * @param obj
	 */
	public void delete(T obj) {
		this.getCurrentSession().delete(obj);
	}

	/**
	 * 查找数据对象集合
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午2:55:14
	 * @since
	 * @param hql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String hql, List<Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && params.size() > 0) {
			for (int i = 0, len = params.size(); i < len; i++) {
				q.setParameter(i, params.get(i));
			}
		}
		return q.list();
	}

	/**
	 * 查找数据对象集合,带分页
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午2:55:25
	 * @since
	 * @param hql
	 * @param currentPage
	 * @param pageSize
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String hql, int currentPage, int pageSize, List<Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && params.size() > 0) {
			for (int i = 0, len = params.size(); i < len; i++) {
				q.setParameter(i, params.get(i));
			}
		}
		return q.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
	}
	


	/**
	 * 获取数据对象
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午2:55:35
	 * @since
	 * @param c
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}

	/**
	 * 获取数据对象
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午2:55:42
	 * @since
	 * @param hql
	 * @param params
	 * @return
	 */
	public T get(String hql, List<Object> params) {
		List<T> l = this.find(hql, params);
		if (l != null && l.size() > 0) {
			return (T) l.get(0);
		}
		return null;
	}

	/**
	 * 获取数据对象
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午2:55:58
	 * @since
	 * @param c
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T load(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().load(c, id);
	}

	/**
	 * select count(*) from 类
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午2:56:08
	 * @since
	 * @param hql
	 * @param params
	 * @return
	 */
	public Long count(String hql, List<Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && params.size() > 0) {
			for (int i = 0, len = params.size(); i < len; i++) {
				q.setParameter(i, params.get(i));
			}
		}
		Long total = (Long) q.uniqueResult();
		return total == null ? 0 : total;
	}

	/**
	 * 执行HQL语句
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午2:56:19
	 * @since
	 * @param hql
	 * @return
	 */
	public Integer executeHql(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.executeUpdate();
	}

	/**
	 * 获取数据表数据总条数
	 * 
	 * @author misery
	 * @create 2013-4-1 下午5:45:14
	 * @since
	 * @return
	 */
	public Long findTableCount(String tableName) {
		String hql = "SELECT COUNT(*) FROM " + tableName;
		return this.count(hql, null);
	}

	/**
	 * @author misery
	 * @create 2013-4-11 下午5:24:28
	 * @since 
	 * @param obj
	 */
	@Override
	public void merge(T obj) {
		this.getCurrentSession().merge(obj);
	}
	
	
	public Query getMyQuery(String parameter) {
		
		return this.getSessionFactory().getCurrentSession().createQuery(parameter);
	}
}
