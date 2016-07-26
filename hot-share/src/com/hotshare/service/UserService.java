/**
 * Program  : UserService.java
 * Author   : lhzh
 * Create   : 2013-1-18 下午1:44:36
 *
 */

package com.hotshare.service;

import com.hotshare.bean.User;
import com.hotshare.json.bean.DataGrid;
import com.hotshare.json.bean.DataInfo;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;


/**
 * 
 * @author lhzh
 * @version 1.0.0
 * @2013-1-18 下午1:44:36
 */
public interface UserService {

	/**查出所有的前端用户 gkx*/
	public DataGrid<User> findAllUser(PageBean page, OrderBean order,String account,String groupId,String state);

	/**新增前端用户 gkx*/
	public int addUser(User user);

	/**修改前端用户状态 gkx*/
	public int doUserState(String ids,int state);

	/**获取前端用户信息 gkx*/
	public DataInfo<User> getUserInfo(String id);

	/**修改前端用户 gkx*/
	public int modifyUser(User user);

	/**通过userId查出用户对象*/
	public User getUser(int userId);
}
