package com.hotshare.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hotshare.bean.SystemMessageUser;
import com.hotshare.dao.SystemMessageUserDao;
import com.hotshare.service.SystemMessageUserService;

/**
 * @author  lhzh
 * @version 创建时间：2014-8-21 下午04:21:17
 * 类说明
 */
@Service("systemMessageUserService")
public class SystemMessageUserServiceImpl implements SystemMessageUserService{

	@Resource(name = "systemMessageUserDao")
	private SystemMessageUserDao<SystemMessageUser> systemMessageUserDao;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public int addSystemMessageUsers(List<SystemMessageUser> systemMessageUsers){
		try {
			for (Iterator iterator = systemMessageUsers.iterator(); iterator
					.hasNext();) {
				SystemMessageUser systemMessageUser = (SystemMessageUser) iterator
						.next();
				systemMessageUserDao.save(systemMessageUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		return 0;
		
	}
	
	
}
