package com.hotshare.service;

import java.util.List;

import com.hotshare.bean.SystemMessageUser;

/**
 * @author  lhzh
 * @version 创建时间：2014-8-21 下午04:20:51
 * 类说明
 */
public interface SystemMessageUserService {

	/**
	 * 发送系统消息
	 * @Title: addSystemMessageUsers   
	 * @Description: TODO  
	 * @param @param systemMessageUsers
	 * @param @return  
	 * @return int  
	 * @throws
	 */
	public int addSystemMessageUsers(List<SystemMessageUser> systemMessageUsers);
}
