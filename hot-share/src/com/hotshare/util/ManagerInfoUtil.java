package com.hotshare.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hotshare.bean.Manager;
import com.hotshare.constants.Constant;


/**
 * 
 * 管理员工具类
 * 
 * @author lhzh
 * @version 1.0.0
 * @2012-12-27 下午12:54:06
 */
public class ManagerInfoUtil {

	/**
	 * 
	 * 存放用户信息
	 * 
	 * @author lhzh
	 * @create 2012-12-27 下午12:56:52
	 * @since
	 * @param session
	 * @param bean
	 */
	public static void setSeesionManagerInfo(HttpSession session, Manager bean) {
		session.setAttribute(Constant.HOT_SHARE_MANAGER_INFO, bean);
	}

	/**
	 * 
	 * 退出系统
	 * 
	 * @author lhzh
	 * @create 2012-4-9 上午10:37:42
	 * @since
	 * @param request
	 * @return
	 */
	public static boolean loginOut(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null)
			session.invalidate();
		return true;
	}

	/**
	 * 
	 * 提取用户信息
	 * 
	 * @author lhzh
	 * @create 2012-4-9 上午10:38:47
	 * @since
	 * @param request
	 * @return
	 */
	public static Manager getSessionManagerInfo(HttpSession session) {
		if (session != null) {
			return (Manager) session.getAttribute(Constant.HOT_SHARE_MANAGER_INFO);
		}
		return null;
	}

}
