/**
 * Program  : ParameterServiceImpl.java
 * Author   : gkx
 * Create   : 2014-8-12 下午1:45:03
 *
 */

package com.hotshare.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hotshare.bean.Parameter;
import com.hotshare.bean.ParameterDetail;
import com.hotshare.bean.Zone;
import com.hotshare.dao.ParameterDao;
import com.hotshare.json.bean.DataGrid;
import com.hotshare.json.bean.DataInfo;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;
import com.hotshare.service.ParameterService;
import com.hotshare.util.DateFiend;


/**
 * 
 * @author gkx
 * @version 1.0.0
 * @2014-8-12 下午1:45:03
 */
@Service("parameterService")
public class ParameterServiceImpl implements ParameterService {

	private Logger logger = Logger.getLogger(ParameterServiceImpl.class);

	private ParameterDao parameterDao;

	@Autowired
	public void setParameterDao(ParameterDao parameterDao) {
		this.parameterDao = parameterDao;
	}

	
	/**查出所有的系统参数 gkx*/
	@Override
	@Transactional(readOnly = true)
	public DataGrid<Parameter> findAllParameter(PageBean page, OrderBean order) {
		long total = parameterDao.finAllParameterTotal();
		DataGrid<Parameter> dataGrid = new DataGrid<Parameter>();
		if (total > 0) {
			List<Parameter> beanlist = parameterDao.finAllParameter(order, page);
			dataGrid.setRows(beanlist);
			dataGrid.setTotal(total);
		}
		return dataGrid;
	}

	/**新增系统参数 gkx*/
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int addParameter(Parameter parameter) {
		Parameter parameterBean = parameterDao.getParameterByParameterName(parameter.getParameterName());
		if (parameterBean == null) {
			try {
				Date date = DateFiend.getSystemDate();
				parameter.setCreateTime(date);
				parameter.setModifyTime(date);
				parameterDao.save(parameter);
				return 0;
			} catch (Exception e) {
				e.printStackTrace();
				return 1;
			}
		} else {
			return 2;
		}
	}

	/**删除系统参数 gkx*/
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int delParameter(String ids) {
		try {
			parameterDao.batchDelete(ids);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	/**获取系统参数信息 gkx*/
	@Override
	@Transactional(readOnly = true)
	public DataInfo<Parameter> getParameterInfo(String parameterId) {
		Parameter Parameter = (com.hotshare.bean.Parameter) parameterDao.get(Parameter.class, Integer.parseInt(parameterId));
		DataInfo<Parameter> info = new DataInfo<Parameter>();
		info.setData(Parameter);
		info.setSuccess(true);
		return info;
	}

	/**修改系统参数 gkx*/
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int modifyParameter(Parameter parameter,String oldName) {
		if(!parameter.getParameterName().equals(oldName)){
			Parameter parameterBean = parameterDao.getParameterByParameterName(parameter.getParameterName());
			if(parameterBean!=null){
				return 2;
			}
		}
		try {
			Date date = DateFiend.getSystemDate();
			parameter.setModifyTime(date);
			parameterDao.update(parameter);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}


	/**通过PId找到对应的数据*/
	@Transactional(propagation = Propagation.REQUIRED)
	public DataGrid<ParameterDetail> findParameterDetailByPId(PageBean page,OrderBean order, int parameterId) {
		long total = parameterDao.findParameterDetailTotal(parameterId);
		DataGrid<ParameterDetail> dataGrid = new DataGrid<ParameterDetail>();
		if (total > 0) {
			List<ParameterDetail> beanlist = parameterDao.findParameterDetailByPId(order, page,parameterId);
			dataGrid.setRows(beanlist);
			dataGrid.setTotal(total);
		}
		return dataGrid;
	}


	/**删除详细参数*/
	@Transactional(propagation = Propagation.REQUIRED)
	public int delParameterDetail(String ids) {
		try {
			parameterDao.batchDelParameterDetail(ids);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}


	/**通过id查出对象*/
	@Transactional(readOnly = true)
	public Parameter getParameterById(int parameterId) {
		return parameterDao.getParameterById(parameterId);
	}


	/**新增详细参数 gkx*/
	@Transactional(propagation = Propagation.REQUIRED)
	public int addParameterDetail(ParameterDetail parameterDetail) {
		ParameterDetail parameterDetailBean = parameterDao.getParameterDetailByDetailNameAndPId(parameterDetail.getDetailName(),parameterDetail.getParameter().getParameterId());
		if (parameterDetailBean == null) {
			try {
				Date date = DateFiend.getSystemDate();
				parameterDetail.setCreateTime(date);
				parameterDetail.setModifyTime(date);
				parameterDao.save(parameterDetail);
				return 0;
			} catch (Exception e) {
				e.printStackTrace();
				return 1;
			}
		} else {
			return 2;
		}
	}


	/**获取详细参数信息*/
	@Transactional(readOnly = true)
	public DataInfo<ParameterDetail> getParameterDetailInfo(String detailId) {
		ParameterDetail parameterDetail = (com.hotshare.bean.ParameterDetail) parameterDao.get(ParameterDetail.class, Integer.parseInt(detailId));
		DataInfo<ParameterDetail> info = new DataInfo<ParameterDetail>();
		info.setData(parameterDetail);
		info.setSuccess(true);
		return info;
	}


	/**修改详细参数 gkx*/
	@Transactional(propagation = Propagation.REQUIRED)
	public int modifyParameterDetail(ParameterDetail parameterDetail,String oldName) {
		if(!parameterDetail.getDetailName().equals(oldName)){
			ParameterDetail parameterDetailBean = parameterDao.getParameterDetailByDetailNameAndPId(parameterDetail.getDetailName(),parameterDetail.getParameter().getParameterId());
			if(parameterDetailBean!=null){
				return 2;
			}
		}
		try {
			Date date = DateFiend.getSystemDate();
			parameterDetail.setModifyTime(date);
			parameterDao.update(parameterDetail);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}
}
