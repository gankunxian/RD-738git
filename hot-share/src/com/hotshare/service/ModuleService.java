/**  
 * Project Name:hot-share  
 * File Name:InitModuleService.java  
 * Package Name:com.hotshare.service  
 * Date:2014年8月5日下午2:37:49  
 * Copyright (c) 2014, lhzh All Rights Reserved.  
 *  
 */

package com.hotshare.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.hotshare.bean.Module;
import com.hotshare.exception.ServiceException;
import com.hotshare.json.bean.ModuleTree;

/**
 * ClassName:InitModuleService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014年8月5日 下午2:37:49 <br/>
 * 
 * @author lhzh
 * @version
 * @since JDK 1.6
 * @see
 */

public interface ModuleService {
	/**
	 * 模块数据插入数据库 InsertModuleToDataBase:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author lhzh
	 * @param modules
	 * @return
	 * @since JDK 1.6
	 */
	public boolean InsertModuleToDataBase(List<Module> modules);

	/**
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws UnsupportedEncodingException
	 *             初始化模块
	 * 
	 * @Title: initModule
	 * @Description: TODO
	 * @param @param moduleStr
	 * @param @throws ServiceException
	 * @return void
	 * @throws
	 */
	public void initModule(String moduleStr) throws ServiceException,
			UnsupportedEncodingException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 获得模块树
	 * 
	 * @Title: findModuleTree
	 * @Description: TODO
	 * @param @return
	 * @param @throws ServiceException
	 * @return List<ModuleTree>
	 * @throws
	 */
	public List<ModuleTree> findModuleTree(int roleID) throws ServiceException;

	/**
	 * 获得全部模块树
	 * @Title: findAllModuleTree
	 * @Description: TODO
	 * @param @return
	 * @param @throws ServiceException
	 * @return List<ModuleTree>
	 * @throws
	 */
	public List<ModuleTree> findAllModuleTree() throws ServiceException;
}
