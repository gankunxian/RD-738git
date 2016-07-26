/**
 * Program  : UserDao.java
 * Author   : lhzh
 * Create   : 2013-1-18 下午2:12:08
 *
 */

package com.hotshare.dao;

import java.util.List;

import com.hotshare.bean.User;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;


/**
 * 
 * @author lhzh
 * @version 1.0.0
 * @2013-1-18 下午2:12:08
 */
public interface UserDao<T> extends BaseDao<T> {

	/**通过前端用户名称查找对象 */
	public User getUserByAccount(String account);

	/**查询有效的前端用户总数  gkx*/
	public long finAllUserTotal(String account,String groupId,String state);

	/**查找有效的前端用户  gkx */
	public List<User> finAllUser(OrderBean order, PageBean page,String account,String groupId,String state);

	/**修改前端用户状态 gkx*/
	public void doUserState(String ids,int state);

}
