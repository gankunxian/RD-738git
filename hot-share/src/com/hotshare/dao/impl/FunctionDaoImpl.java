
 /**  
 * Project Name:hot-share  
 * File Name:FunctionDaoImpl.java  
 * Package Name:com.hotshare.dao.impl  
 * Date:2014年8月5日下午3:16:57  
 * Copyright (c) 2014, lhzh All Rights Reserved.  
 *  
*/  


  
package com.hotshare.dao.impl;  

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotshare.bean.Function;
import com.hotshare.bean.Module;
import com.hotshare.bean.ModuleFunction;
import com.hotshare.dao.FunctionDao;


/**  
 * ClassName: FunctionDaoImpl <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: 2014年8月5日 下午3:16:57 <br/>  
 *  
 * @author lhzh  
 * @version   
 * @since JDK 1.6  
 */
@Repository("functionDao")
public class FunctionDaoImpl<T> extends BaseDaoImpl<T> implements FunctionDao<T>{

	/**  
	 *    
	 */
	public FunctionDaoImpl() {
		super();  
		 System.out.println("load functionDao");
	}

	

}
  
