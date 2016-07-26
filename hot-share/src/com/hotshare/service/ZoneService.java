/**
 * Program  : ZoneService.java
 * Author   : gkx
 * Create   : 2014-8-4 下午1:07:16
 *
 *
 */

package com.hotshare.service;

import java.util.List;

import com.hotshare.bean.Zone;
import com.hotshare.exception.ServiceException;
import com.hotshare.json.bean.DataGrid;
import com.hotshare.json.bean.DataInfo;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;



/**
* 地区服务层接口
* @author   gkx
* @version  1.0.0
* @2014-8-4 下午1:07:16
*/
public interface ZoneService {
	/**
	 * 增加地区
	 * @author gkx
	 * @create 2014-8-4 下午1:11:47
	 * @since 
	 * @param Zone
	 * @return
	 */
	public int addZone(Zone zone)throws ServiceException;
	
	
	/**
	 * 删除地区
	 * @author gkx
	 * @create 2014-8-4 下午1:12:41
	 * @since 
	 * @param Zone
	 * @return
	 */
	public int delZone(String ids)throws ServiceException;
	
	
	/**
	 * 查询所有地区
	 * @author gkx
	 * @create 2014-8-4 下午1:13:38
	 * @since 
	 * @return
	 */
	public DataGrid<Zone> findAllZone(PageBean page,OrderBean order)throws ServiceException;
	
	/**
	 * 修改地区
	 * @author gkx
	 * @create 2014-8-4 下午1:14:47
	 * @since 
	 * @param Zone
	 * @return
	 */
	public int modifyZone(Zone zone,String oldName)throws ServiceException;
	
	
	/**
	 * 获得地区详细信息
	 * @author gkx
	 * @create 2014-8-4 下午1:54:25
	 * @since 
	 * @param ZoneId
	 * @return
	 */
	public DataInfo<Zone> getZoneInfo(String zoneId)throws ServiceException;


	/**根据级别,父编号,获取地区列表*/
	public List<Zone> findNextZone(String level, String zoneNo);


	/**查找数据库里No最大的zone*/
	public Zone findMaxZone(String no);

	/**通过id拿到城市对象*/
	public Zone getZone(int zoneId);

	/**获得对应父级地区*/
	public Zone getZoneByZoneNo(String fatherNo);

	
}

