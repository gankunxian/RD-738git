/**
 * Program  : IBaseDao.java
 * Author   : lhzh
 * Create   : 2013-3-29 下午2:45:45
 */

package com.hotshare.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 基础数据库操作接口
 * 
 * @author lhzh
 * @version 1.0.0
 * @2013-3-29 下午2:45:45
 */
public interface BaseDao<T> {

	/**
	 * 保存数据对象
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午2:51:20
	 * @since
	 * @param obj
	 */
	public void save(T obj);

	/**
	 * 更新数据对象
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午2:51:05
	 * @since
	 * @param obj
	 */
	public void update(T obj);

	/**
	 * 保存或更新数据对象
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午2:50:57
	 * @since
	 * @param obj
	 */
	public void saveOrUpdate(T obj);
	
	
	/**
	 * session中两个标识符相同的对象合并
	 * @author misery
	 * @create 2013-4-11 下午5:24:05
	 * @since 
	 * @param obj
	 */
	public void merge(T obj);

	/**
	 * 删除数据对象
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午2:50:48
	 * @since
	 * @param obj
	 */
	public void delete(T obj);

	/**
	 * 查找数据对象集合
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午2:50:30
	 * @since
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> find(String hql, List<Object> params);

	/**
	 * 查找数据对象集合,带分页
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午2:50:22
	 * @since
	 * @param hql
	 * @param currentPage
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public List<T> find(String hql, int currentPage, int pageSize, List<Object> params);

	/**
	 * 获取数据对象
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午2:50:12
	 * @since
	 * @param c
	 * @param id
	 * @return
	 */
	public T get(Class<T> c, Serializable id);

	/**
	 * 获取数据对象
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午2:50:02
	 * @since
	 * @param hql
	 * @param params
	 * @return
	 */
	public T get(String hql, List<Object> params);

	/**
	 * 获取数据对象
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午2:49:53
	 * @since
	 * @param c
	 * @param id
	 * @return
	 */
	public T load(Class<T> c, Serializable id);

	/**
	 * select count(*) from 类
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午2:49:21
	 * @since
	 * @param hql
	 * @param params
	 * @return
	 */
	public Long count(String hql, List<Object> params);

	/**
	 * 执行HQL语句
	 * 
	 * @author lhzh
	 * @create 2013-3-29 下午2:49:09
	 * @since
	 * @param hql
	 * @return
	 */
	public Integer executeHql(String hql);

	/**
	 * 获取数据表数据总条数
	 * 
	 * @author misery
	 * @create 2013-4-1 下午5:45:14
	 * @since
	 * @return
	 */
	public Long findTableCount(String tableName);
}
