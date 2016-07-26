/**  
 * Project Name:hot-share  
 * File Name:InitFunctionService.java  
 * Package Name:com.hotshare.service  
 * Date:2014年8月5日下午2:38:20  
 * Copyright (c) 2014, lhzh All Rights Reserved.  
 *  
 */


package com.hotshare.service;

import java.util.List;

import com.hotshare.bean.Function;

/**
 * ClassName:InitFunctionService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014年8月5日 下午2:38:20 <br/>
 * 
 * @author lhzh
 * @version
 * @since JDK 1.6
 * @see
 */

public interface FunctionService {
	
	/**
	 * 功能权限插入数据库
	 * InsertFunctionToDataBase:(这里用一句话描述这个方法的作用). <br/>  
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>  
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
	 *  
	 * @author lhzh  
	 * @param functions
	 * @return  
	 * @since JDK 1.6
	 */
	public boolean InsertFunctionToDataBase(List<Function> functions);

}
