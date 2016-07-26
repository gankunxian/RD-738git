/**
 * Program  : UserServiceImpl.java
 * Author   : lhzh
 * Create   : 2013-1-18 下午1:45:03
 *
 */

package com.hotshare.service.impl;

import java.util.Date;
import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.hotshare.bean.User;
import com.hotshare.dao.UserDao;
import com.hotshare.json.bean.DataGrid;
import com.hotshare.json.bean.DataInfo;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;
import com.hotshare.service.UserService;
import com.hotshare.util.DateFiend;
import com.hotshare.util.SecurityUtil;


/**
 * 
 * @author lhzh
 * @version 1.0.0
 * @2013-1-18 下午1:45:03
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	private Logger logger = Logger.getLogger(UserServiceImpl.class);

	private UserDao<User> userDao;

	@Autowired
	public void setUserDao(UserDao<User> userDao) {
		this.userDao = userDao;
	}

	/**查出所有的前端用户 gkx*/
	@Override
	@Transactional(readOnly = true)
	public DataGrid<User> findAllUser(PageBean page, OrderBean order,String account,String groupId,String state) {
		long total = userDao.finAllUserTotal(account,groupId,state);
		DataGrid<User> dataGrid = new DataGrid<User>();
		if (total > 0) {
			List<User> beanlist = userDao.finAllUser(order, page,account,groupId,state);
			dataGrid.setRows(beanlist);
			dataGrid.setTotal(total);
		}
		return dataGrid;
	}

	/**新增前端用户 gkx*/
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int addUser(User user) {
		User UserBean = userDao.getUserByAccount(user.getAccount());
		if (UserBean == null) {
			try {
				Date date = DateFiend.getSystemDate();
				//密码加密
				user.setPassword(SecurityUtil.MD5(user.getPassword()));
				user.setRegTime(date);
				userDao.save(user);
				return 0;
			} catch (Exception e) {
				e.printStackTrace();
				return 1;
			}
		} else {
			return 2;
		}
	}

	/**修改前端用户状态  gkx*/
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int doUserState(String ids,int state) {
		try {
			userDao.doUserState(ids,state);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	/**获取前端用户信息 gkx*/
	@Override
	@Transactional(readOnly = true)
	public DataInfo<User> getUserInfo(String userId) {
		User user = userDao.get(User.class, Integer.parseInt(userId));
		DataInfo<User> info = new DataInfo<User>();
		info.setData(user);
		info.setSuccess(true);
		return info;
	}

	/**修改前端用户 gkx*/
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int modifyUser(User user) {
		try {
			userDao.update(user);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	/**通过userId查出用户对象*/
	@Transactional(readOnly = true)
	public User getUser(int userId) {
		return userDao.get(User.class, userId);
	}
}
