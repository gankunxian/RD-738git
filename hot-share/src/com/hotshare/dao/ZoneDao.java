/**
 * Program  : ZoneDao.java
 * Author   : gkx
 * Create   : 2014-8-4 下午1:17:13
 *
 */

package com.hotshare.dao;

import java.util.List;

import com.hotshare.bean.Zone;
import com.hotshare.exception.DaoException;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;


/**
 * 地区dao
 * 
 * @author gkx
 * @version 1.0.0
 * @2014-8-4 下午1:17:13
 */
public interface ZoneDao<T> extends BaseDao<T> {

	/**
	 * 根据地区名称获得地区
	 * 
	 * @author gkx
	 * @create 2014-8-4 下午1:18:23
	 * @since
	 * @param name
	 * @return
	 */
	public Zone getZoneByName(String name,String fatherNo) throws DaoException;

	/**
	 * 获得地区总数
	 * 
	 * @author gkx
	 * @create 2014-8-4 下午1:42:12
	 * @since
	 * @return
	 */
	public Long getZoneTotal() throws DaoException;

	/**
	 * 获得地区列表
	 * 
	 * @author gkx
	 * @create 2014-8-4 下午1:48:28
	 * @since
	 * @param order
	 * @param page
	 * @return
	 * @throws DaoException
	 */
	public List<Zone> findAllZone(OrderBean order, PageBean page) throws DaoException;

	/**
	 * 批量删除
	 * 
	 * @author gkx
	 * @create 2014-8-4 下午3:02:46
	 * @since
	 * @param ids
	 * @throws DaoException
	 */
	public void batchDelete(String ids) throws DaoException;

	/**根据级别,父编号,获取地区列表*/
	public List<Zone> findNextZone(String level, String zoneNo);

	/**查找数据库里No最大的zone*/
	public Zone findMaxZone(String no);

	/**通过id拿到城市对象*/
	public Zone getZone(int zoneId);

	/**获得对应父级地区*/
	public Zone getZoneByZoneNo(String fatherNo);

	/**批量更新数据状态*/
	public void batchUpdate(String ids);
	
}
