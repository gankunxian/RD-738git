/**
 * Program  : ZoneDaoImpl.java
 * Author   : gkx
 * Create   : 2014-8-4 下午1:18:50
 */

package com.hotshare.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.hotshare.bean.Zone;
import com.hotshare.dao.ZoneDao;
import com.hotshare.exception.DaoException;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;


/**
 * /地区dao实现
 * 
 * @author gkx
 * @version 1.0.0
 * @2014-8-4 下午1:18:50
 */
@Repository("zoneDao")
public class ZoneDaoImpl<T> extends BaseDaoImpl<T> implements ZoneDao<T> {

	/**
	 * @author gkx
	 * @create 2014-8-4 下午1:19:21
	 * @since
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Zone getZoneByName(String name,String fatherNo) throws DaoException {
		StringBuffer sb = new StringBuffer("from Zone where state=0 and zoneName = ");
		sb.append("'" + name + "'");
		sb.append(" and fatherNo = ");
		sb.append("'" + fatherNo + "'");
		List<Zone> beanlist = (List<Zone>) find(sb.toString(), null);
		return beanlist.size() == 0 ? null : beanlist.get(0);
	}

	/**
	 * @author gkx
	 * @create 2014-8-4 下午1:42:26
	 * @since
	 * @return
	 */
	@Override
	public Long getZoneTotal() throws DaoException {
		StringBuffer hql = new StringBuffer("select count(*) from Zone where state = 0");
		Long total = this.count(hql.toString(), null);
		return total;
	}

	@SuppressWarnings("unchecked")
	public List<Zone> findAllZone(OrderBean order, PageBean page) throws DaoException {
		List<Zone> beanlist = null;
		StringBuffer hql = new StringBuffer("from Zone where state = 0");
		if (order.getSort() != null && order.getOrder() != null) {
			hql.append(" order by ");
			hql.append(order.getSort());
			hql.append(" ");
			hql.append(order.getOrder());
			hql.append(" , createTime desc ");
		}

		beanlist = (List<Zone>) this.find(hql.toString(), page.getPage(), page.getRows(), null);
		return beanlist;
	}
	
	/**
	 * 批量删除
	 * @author gkx
	 * @create 2014-8-4 下午3:02:13
	 * @since 
	 * @param ids
	 * @throws DaoException
	 */
	public void batchDelete(String ids) throws DaoException {
		StringBuffer sb = new StringBuffer("delete from Zone where id in(");
		sb.append(ids);
		sb.append(")");
		this.executeHql(sb.toString());
	}

	
	/**根据级别,父编号,获取地区列表*/
	public List<Zone> findNextZone(String level, String zoneNo) {
		StringBuffer sb = new StringBuffer("from Zone where state=0 and level=? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(Short.parseShort(level));
		if(!zoneNo.equals("1")){
			sb.append(" and fatherNo=?");
			paramList.add(zoneNo);
		}
		sb.append(" order by sort desc ");
		return (List<Zone>)find(sb.toString(),paramList);
	}

	
	/**查找数据库里No最大的zone*/
	public Zone findMaxZone(String no) {
		StringBuffer sb = new StringBuffer("from Zone where state !=-1 and fatherNo = ? order by zoneNo desc");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(no);
		List<Zone> list = (List<Zone>)find(sb.toString(),paramList);
		if(list.size()!=0){
			return list.get(0);
		}
		return null;
	}

	
	/**通过id拿到城市对象*/
	public Zone getZone(int zoneId) {
		return (Zone)this.get((Class<T>) Zone.class, zoneId);
	}

	
	/**获得对应父级地区*/
	public Zone getZoneByZoneNo(String fatherNo) {
		StringBuffer sb = new StringBuffer("from Zone where state !=-1 and zoneNo = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(fatherNo);
		List<Zone> list = (List<Zone>)find(sb.toString(),paramList);
		if(list.size()!=0){
			return list.get(0);
		}
		return null;
	}

	/**批量更新数据状态*/
	public void batchUpdate(String ids) {
		StringBuffer sb = new StringBuffer("update Zone set state=-1 where id in(");
		sb.append(ids);
		sb.append(")");
		this.executeHql(sb.toString());
	}

}
