package com.hotshare.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hotshare.bean.SystemMessage;
import com.hotshare.dao.SystemMessageDao;
import com.hotshare.exception.DaoException;
import com.hotshare.json.bean.DataGrid;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;
import com.hotshare.service.SystemMessageService;

/**
 * @author lhzh
 * @version 创建时间：2014-8-19 下午08:02:39 类说明
 */
@Service("systemMessageService")
public class SystemMessageServiceImpl implements SystemMessageService {

	@Resource(name = "systemMessageDao")
	private SystemMessageDao<SystemMessage> systemMessageDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addSystemMessage(SystemMessage systemMessage) {
		try {
			systemMessageDao.save(systemMessage);
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		return 0;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int addSystemMessages(List<SystemMessage> systemMessages) {
		try {
			for (Iterator iterator = systemMessages.iterator(); iterator.hasNext();) {
				SystemMessage systemMessage = (SystemMessage) iterator.next();
				systemMessageDao.save(systemMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		return 0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public DataGrid<SystemMessage> findAllSystemMessage(PageBean page,
			OrderBean order) {
		Long total = systemMessageDao.getSystemMessageTotal();
		DataGrid<SystemMessage> dataGrid = new DataGrid<SystemMessage>();
		if (total > 0) {
			List<SystemMessage> beanlist = systemMessageDao.findAllSystemMessage(order, page);
			dataGrid.setRows(beanlist);
			dataGrid.setTotal(total);
		}
		return dataGrid;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int delSystemMessage(String ids) {
		try {
			systemMessageDao.batchDelete(ids);
			return 0;
		} catch (DaoException e) {
			e.printStackTrace();
			return 1;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public SystemMessage findSystemMessage(int messageId) {
		return systemMessageDao.get(SystemMessage.class,messageId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int modifySystemMessage(SystemMessage systemMessage) {
		try {
			systemMessageDao.update(systemMessage);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<SystemMessage> findAllSystemMessage() {
			List<SystemMessage> beanlist = systemMessageDao.findAllSystemMessage();
			return beanlist;
	}

}
