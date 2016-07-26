/**  
 * Project Name:hot-share  
 * File Name:InitFunctionServiceImpl.java  
 * Package Name:com.hotshare.service.impl  
 * Date:2014年8月5日下午2:38:50  
 * Copyright (c) 2014, lhzh All Rights Reserved.  
 *  
 */


package com.hotshare.service.impl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hotshare.bean.Function;
import com.hotshare.dao.FunctionDao;
import com.hotshare.exception.ServiceException;
import com.hotshare.service.FunctionService;

/**
 * ClassName:InitFunctionServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014年8月5日 下午2:38:50 <br/>
 * 
 * @author lhzh
 * @version
 * @since JDK 1.6
 * @see
 */

@Service("functionService")
public class FunctionServiceImpl implements FunctionService {

	public FunctionDao<Function> functionDao;

	/**
	 * functionDao.
	 * 
	 * @return the functionDao
	 * @since JDK 1.6
	 */
	public FunctionDao<Function> getFunctionDao() {
		return functionDao;
	}

	/**  
	 *    
	 */
	public FunctionServiceImpl() {
		  
		super();  
		System.out.println("load initFunctionService");
		
	}

	/**
	 * functionDao.
	 * 
	 * @param functionDao
	 *            the functionDao to set
	 * @since JDK 1.6
	 */
	@Autowired
	public void setFunctionDao(FunctionDao<Function> functionDao) {
		this.functionDao = functionDao;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.hotshare.service.FunctionService#InsertFunctionToDataBase(java.util.List)
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean InsertFunctionToDataBase(List<Function> functions) throws ServiceException{
		for (int i = 0; i < functions.size(); i++) {
			saveOrUpdate(functions.get(i));
		}
		return true;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, noRollbackFor = Exception.class)
	public synchronized boolean saveOrUpdate(Function fun) {
		List<Function> list = functionDao.find(
				"from Function as p where p.funCode = '"
						+ fun.getFunCode() + "'", null);
		if (list == null || list.isEmpty()) {
			try {
				functionDao.save(fun);
			} catch (Exception e) {
				return false;
			}
		} else {
			Function funcNew = list.get(0);
			funcNew.setFunCode(fun.getFunCode());
			funcNew.setDescInfo(fun.getDescInfo());
			funcNew.setFunName(fun.getFunName());
			try {
				functionDao.update(funcNew);
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

}
