/**
 * Program  : ManagerDao.java
 * Author   : lhzh
 * Create   : 2013-1-18 下午2:12:08
 */

package com.hotshare.dao;

import java.util.List;

import com.hotshare.bean.Manager;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;


/**
 * 
 * @author lhzh
 * @version 1.0.0
 * @2013-1-18 下午2:12:08
 */
public interface ManagerDao<T> extends BaseDao<T> {

	/**通过系统人员名称查找对象 */
	public Manager getManagerByAccount(String account);

	/**查询有效的系统人员总数  gkx*/
	public long finAllManagerTotal();

	/**查找有效的系统人员  gkx */
	public List<Manager> finAllManager(OrderBean order, PageBean page);

	/**删除系统人员 gkx*/
	public void batchDelete(String ids);

}
