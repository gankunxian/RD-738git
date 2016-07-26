package com.hotshare.dao;

import java.util.List;

import com.hotshare.bean.SystemMessage;
import com.hotshare.exception.DaoException;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;

/**
 * @author  lhzh
 * @version 创建时间：2014-8-19 下午08:00:13
 * 类说明
 */
public interface SystemMessageDao<T> extends BaseDao<T> {

	/**
	 * 获取系统消息总数
	 * @Title: getSystemMessageTotal   
	 * @Description: TODO  
	 * @param @return
	 * @param @throws DaoException  
	 * @return Long  
	 * @throws
	 */
	public Long getSystemMessageTotal() throws DaoException;
	
	/**
	 * 获取全部的系统消息
	 * @Title: findAllGroup   
	 * @Description: TODO  
	 * @param @param order
	 * @param @param page
	 * @param @return
	 * @param @throws DaoException  
	 * @return List<Group>  
	 * @throws
	 */
	public List<SystemMessage> findAllSystemMessage(OrderBean order, PageBean page) throws DaoException;
	/**
	 * 
	 * @Title: batchDelete   
	 * @Description: TODO  
	 * @param @param ids
	 * @param @throws DaoException  
	 * @return void  
	 * @throws
	 */
	public void batchDelete(String ids) throws DaoException;
	
	/**
	 * 获取全部的系统消息
	 * @Title: findAllSystemMessage   
	 * @Description: TODO  
	 * @param @return
	 * @param @throws DaoException  
	 * @return List<SystemMessage>  
	 * @throws
	 */
	public List<SystemMessage> findAllSystemMessage()throws DaoException;
}
