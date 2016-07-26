/**
 * Program  : ZoneServiceImpl.java
 * Author   : gkx
 * Create   : 2014-8-4 下午1:07:42
 */

package com.hotshare.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hotshare.bean.Zone;
import com.hotshare.dao.ZoneDao;
import com.hotshare.exception.ServiceException;
import com.hotshare.json.bean.DataGrid;
import com.hotshare.json.bean.DataInfo;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;
import com.hotshare.service.ZoneService;
import com.hotshare.util.DateFiend;
import com.hotshare.util.Pinyin4jUtil;


/**
 * 地区服务层实现
 * 
 * @author gkx
 * @version 1.0.0
 * @2014-8-4 下午1:07:42
 */
@Service("zoneService")
public class ZoneServiceImpl implements ZoneService {

	private ZoneDao<Zone> zoneDao;


	public ZoneDao<Zone> getZoneDao() {
		return zoneDao;
	}

	@Autowired
	public void setZoneDao(ZoneDao<Zone> zoneDao) {
		this.zoneDao = zoneDao;
	}

	/**
	 * @author gkx
	 * @create 2014-8-4 下午1:14:58
	 * @since
	 * @param Zone
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int addZone(Zone zone) throws ServiceException {
		Zone zoneBean = zoneDao.getZoneByName(zone.getZoneName(),zone.getFatherNo());
		if (zoneBean == null) {
			try {
				Date date = DateFiend.getSystemDate();
				zone.setCreateTime(date);
				//设置  initial 字段
				String str = zone.getZoneName();
				char chineseChar = str.charAt(0);
				String[] s = Pinyin4jUtil.noRepeatHanyuPinyinStringArray(chineseChar);
				String initial = s[0].charAt(0)+"";
				zone.setInitial(initial.toUpperCase());   //数据库中写入的大写的
				zoneDao.save(zone);
				return 0;
			} catch (Exception e) {
				e.printStackTrace();
				return 1;
			}
		} else {
			return 2;
		}

	}

	/**
	 * @author gkx
	 * @create 2014-8-4 下午1:14:58
	 * @since
	 * @param Zone
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int delZone(String ids) throws ServiceException {
		try {
			zoneDao.batchUpdate(ids);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}

	}

	/**
	 * @author gkx
	 * @create 2014-8-4 下午1:14:58
	 * @since
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public DataGrid<Zone> findAllZone(PageBean page, OrderBean order) throws ServiceException {
		Long total = zoneDao.getZoneTotal();
		DataGrid<Zone> dataGrid = new DataGrid<Zone>();
		if (total > 0) {
			List<Zone> beanlist = zoneDao.findAllZone(order, page);
			dataGrid.setRows(beanlist);
			dataGrid.setTotal(total);
		}
		return dataGrid;
	}

	/**
	 * @author gkx
	 * @create 2014-8-4 下午1:14:58
	 * @since
	 * @param Zone
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int modifyZone(Zone zone,String oldName) throws ServiceException {
		if(!zone.getZoneName().equals(oldName)){
			Zone zoneBean = zoneDao.getZoneByName(zone.getZoneName(),zone.getFatherNo());
			if(zoneBean!=null){
				return 2;
			}
		}
		try {
			zoneDao.update(zone);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	/**
	 * @author gkx
	 * @create 2014-8-4 下午1:54:45
	 * @since
	 * @param ZoneId
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public DataInfo<Zone> getZoneInfo(String zoneId) {
		Zone zone = zoneDao.get(Zone.class, Integer.parseInt(zoneId));
		DataInfo<Zone> info = new DataInfo<Zone>();
		info.setData(zone);
		info.setSuccess(true);
		return info;
	}

	/**根据级别,父编号,获取地区列表*/
	@Override
	@Transactional(readOnly = true)
	public List<Zone> findNextZone(String level, String zoneNo) {
		return zoneDao.findNextZone(level,zoneNo);
	}

	/**查找数据库里No最大的zone*/
	@Transactional(readOnly = true)
	public Zone findMaxZone(String no) {
		return zoneDao.findMaxZone(no);
	}

	
	/**通过id拿到城市对象*/
	@Transactional(readOnly = true)
	public Zone getZone(int zoneId) {
		return zoneDao.getZone(zoneId);
	}

	/**获得对应父级地区*/
	@Transactional(readOnly = true)
	public Zone getZoneByZoneNo(String fatherNo) {
		return zoneDao.getZoneByZoneNo(fatherNo);
	}
}

