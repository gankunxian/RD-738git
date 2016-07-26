/**
 * Program  : ManagerServiceImpl.java
 * Author   : lhzh
 * Create   : 2013-1-18 下午1:45:03
 *
 */

package com.hotshare.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.hotshare.bean.Manager;
import com.hotshare.bean.User;
import com.hotshare.constants.Constant;
import com.hotshare.dao.ManagerDao;
import com.hotshare.json.bean.DataGrid;
import com.hotshare.json.bean.DataInfo;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;
import com.hotshare.json.bean.ResultBean;
import com.hotshare.service.ManagerService;
import com.hotshare.util.DateFiend;
import com.hotshare.util.SecurityUtil;
import com.hotshare.util.ManagerInfoUtil;


/**
 * 
 * @author lhzh
 * @version 1.0.0
 * @2013-1-18 下午1:45:03
 */
@Service("managerService")
public class ManagerServiceImpl implements ManagerService {

	private Logger logger = Logger.getLogger(ManagerServiceImpl.class);

	private ManagerDao<Manager> managerDao;

	@Autowired
	public void setManagerDao(ManagerDao<Manager> managerDao) {
		this.managerDao = managerDao;
	}

	/**
	 * 管理员登录
	 * 
	 * @author lhzh
	 * @create 2013-1-21 下午1:49:09
	 * @since
	 * @param account
	 * @param password
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Manager managerLogin(String account, String password, HttpSession session) {
		try {
			System.out.println("客户端传进来的加密后的密码:"+password);
			String sessionVerifyRandom = (String)session.getAttribute(account+Constant.CSS_WEB_USER_RANDOM);
			System.out.println("拿到session里随机时间串的key值:"+account+Constant.CSS_WEB_USER_RANDOM+"===="+session.getId());
			System.out.println("拿到session里随机时间串的value值:"+sessionVerifyRandom);
			Manager manager = managerDao.getManagerByAccount(account);
			String sqlPassword = SecurityUtil.MD5(SecurityUtil.MD5(manager.getPassword()+sessionVerifyRandom)+account);
			System.out.println("数据库密码与随机时间串加密:"+sqlPassword);
			String nowTime = System.currentTimeMillis()+"";
			session.setAttribute(account+Constant.CSS_WEB_USER_RANDOM, nowTime);
			System.out.println("更新session里随机时间串:"+(String)session.getAttribute(account+Constant.CSS_WEB_USER_RANDOM));
			if (manager != null && sqlPassword.equals(password)) {
				// 更新用户登录次数
				return manager;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 修改用户密码
	 * 
	 * @author lhzh
	 * @create 2013-1-21 下午2:25:53
	 * @since
	 * @param oldPwd
	 * @param manager
	 * @param newPwd
	 * @return
	 */

	@Transactional(propagation = Propagation.REQUIRED)
	public ResultBean changePassword(String oldPwd, HttpSession session, String newPwd) {
		ResultBean resultBean = new ResultBean();
		String security_password = "";
		String security_passwordNew = "";
		Manager manager = ManagerInfoUtil.getSessionManagerInfo(session);
		try {
			security_password = SecurityUtil.MD5(oldPwd);
			security_passwordNew = SecurityUtil.MD5(newPwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (security_password.equals(manager.getPassword())) {
			Manager managerNew = managerDao.get(Manager.class, manager.getManagerId());
			managerNew.setPassword(security_passwordNew);
			managerDao.update(managerNew);
			resultBean.setSuccess(true);
			resultBean.setMessage("用户密码修改成功");
			ManagerInfoUtil.setSeesionManagerInfo(session, managerNew);
		} else {
			resultBean.setSuccess(false);
			resultBean.setMessage("旧密码输入不正确,请重新输入"); // 密码输入错误
		}
		return resultBean;
	}

	
	/**查出所有的系统人员 gkx*/
	@Override
	@Transactional(readOnly = true)
	public DataGrid<Manager> findAllManager(PageBean page, OrderBean order) {
		long total = managerDao.finAllManagerTotal();
		DataGrid<Manager> dataGrid = new DataGrid<Manager>();
		if (total > 0) {
			List<Manager> beanlist = managerDao.finAllManager(order, page);
			dataGrid.setRows(beanlist);
			dataGrid.setTotal(total);
		}
		return dataGrid;
	}

	/**新增系统人员 gkx*/
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int addManager(Manager manager) {
		Manager managerBean = managerDao.getManagerByAccount(manager.getAccount());
		if (managerBean == null) {
			try {
				Date date = DateFiend.getSystemDate();
				//密码加密
				manager.setPassword(SecurityUtil.MD5(manager.getPassword()));
				manager.setCreateTime(date);
				manager.setModifyTime(date);
				managerDao.save(manager);
				return 0;
			} catch (Exception e) {
				e.printStackTrace();
				return 1;
			}
		} else {
			return 2;
		}
	}

	/**删除系统人员 gkx*/
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int delManager(String ids) {
		try {
			managerDao.batchDelete(ids);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	/**获取系统人员信息 gkx*/
	@Override
	@Transactional(readOnly = true)
	public DataInfo<Manager> getManagerInfo(String managerId) {
		Manager manager = managerDao.get(Manager.class, Integer.parseInt(managerId));
		DataInfo<Manager> info = new DataInfo<Manager>();
		info.setData(manager);
		info.setSuccess(true);
		return info;
	}

	/**修改系统人员 gkx*/
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int modifyManager(Manager manager) {
		try {
			Date date = DateFiend.getSystemDate();
			manager.setModifyTime(date);
			//密码加密
			manager.setPassword(SecurityUtil.MD5(manager.getPassword()));
			managerDao.update(manager);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	/**通过id查找对象*/
	@Transactional(propagation = Propagation.REQUIRED)
	public Manager getManager(int managerId) {
		return managerDao.get(Manager.class, managerId);
	}
}
