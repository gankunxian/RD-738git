package com.hotshare.service;

import java.util.List;

import com.hotshare.bean.SystemMessage;
import com.hotshare.json.bean.DataGrid;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;

/**
 * @author  lhzh
 * @version 创建时间：2014-8-19 下午08:01:40
 * 类说明
 */
public interface SystemMessageService {
	/**
	 * 发送系统消息
	 * @Title: addSystemMessage   
	 * @Description: TODO  
	 * @param @param systemMessage
	 * @param @return  
	 * @return int  
	 * @throws
	 */
	public int addSystemMessage(SystemMessage systemMessage);
	
	/**
	 * 批量发送系统消息
	 * @Title: addSystemMessages   
	 * @Description: TODO  
	 * @param @param systemMessages
	 * @param @return  
	 * @return int  
	 * @throws
	 */
	public int addSystemMessages(List<SystemMessage> systemMessages);
	
	/**
	 * 查询所有的系统消息
	 * @Title: findAllSystemMessage   
	 * @Description: TODO  
	 * @param @param page
	 * @param @param order
	 * @param @return  
	 * @return DataGrid<SystemMessage>  
	 * @throws
	 */
	public DataGrid<SystemMessage> findAllSystemMessage(PageBean page, OrderBean order) ;
	
	/**
	 * 删除系统消息
	 * @Title: delSystemMessage   
	 * @Description: TODO  
	 * @param @param ids
	 * @param @return  
	 * @return int  
	 * @throws
	 */
	public int delSystemMessage(String ids);
	
	
	/**
	 * 查询系统消息
	 * @Title: findSystemMessage   
	 * @Description: TODO  
	 * @param @param messageId
	 * @param @return  
	 * @return SystemMessage  
	 * @throws
	 */
	public SystemMessage findSystemMessage(int messageId);
	
	
	/**
	 * 编辑系统消息
	 * @Title: modifySystemMessage   
	 * @Description: TODO  
	 * @param @param systemMessage
	 * @param @return  
	 * @return int  
	 * @throws
	 */
	public int modifySystemMessage(SystemMessage systemMessage);
	
	
	/**
	 * 查询所有的系统消息
	 * @Title: findAllSystemMessage   
	 * @Description: TODO  
	 * @param @param page
	 * @param @param order
	 * @param @return  
	 * @return DataGrid<SystemMessage>  
	 * @throws
	 */
	public List<SystemMessage> findAllSystemMessage() ;
}
