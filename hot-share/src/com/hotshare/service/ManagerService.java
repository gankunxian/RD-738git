/**
 * Program  : ManagerService.java
 * Author   : lhzh
 * Create   : 2013-1-18 下午1:44:36
 *
 */

package com.hotshare.service;

import javax.servlet.http.HttpSession;

import com.hotshare.bean.Manager;
import com.hotshare.json.bean.DataGrid;
import com.hotshare.json.bean.DataInfo;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;
import com.hotshare.json.bean.ResultBean;


/**
 * 
 * @author lhzh
 * @version 1.0.0
 * @2013-1-18 下午1:44:36
 */
public interface ManagerService {

	/**系统人员登录*/
	public Manager managerLogin(String account, String passwrod, HttpSession session);

	/**修改密码*/
	public ResultBean changePassword(String oldPwd, HttpSession session, String newPwd);

	/**查出所有的系统人员 gkx*/
	public DataGrid<Manager> findAllManager(PageBean page, OrderBean order);

	/**新增系统人员 gkx*/
	public int addManager(Manager manager);

	/**删除系统人员 gkx*/
	public int delManager(String ids);

	/**获取系统人员信息 gkx*/
	public DataInfo<Manager> getManagerInfo(String id);

	/**修改系统人员 gkx*/
	public int modifyManager(Manager manager);

	/**通过id查找对象*/
	public Manager getManager(int managerId);
}
