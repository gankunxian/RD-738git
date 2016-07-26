/**
 * Program  : ParameterService.java
 * Author   : gkx
 * Create   : 2013-1-18 下午1:44:36
 */

package com.hotshare.service;


import com.hotshare.bean.Parameter;
import com.hotshare.bean.ParameterDetail;
import com.hotshare.json.bean.DataGrid;
import com.hotshare.json.bean.DataInfo;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;


/**
 * 
 * @author gkx
 * @version 1.0.0
 * @2013-1-18 下午1:44:36
 */
public interface ParameterService {

	/**查出所有的系统参数 gkx*/
	public DataGrid<Parameter> findAllParameter(PageBean page, OrderBean order);

	/**新增系统参数 gkx*/
	public int addParameter(Parameter parameter);

	/**删除系统参数 gkx*/
	public int delParameter(String ids);

	/**获取系统参数信息 gkx*/
	public DataInfo<Parameter> getParameterInfo(String id);

	/**修改系统参数 gkx*/
	public int modifyParameter(Parameter parameter,String oldName);

	/**通过PId找到对应的数据*/
	public DataGrid<ParameterDetail> findParameterDetailByPId(PageBean page,OrderBean order, int parameterId);

	/**删除详细参数*/
	public int delParameterDetail(String ids);

	/**通过id查出对象*/
	public Parameter getParameterById(int parameterId);

	/**新增详细参数 gkx*/
	public int addParameterDetail(ParameterDetail parameterDetail);

	/**获取详细参数信息*/
	public DataInfo<ParameterDetail> getParameterDetailInfo(String detailId);

	/**修改详细参数 gkx*/
	public int modifyParameterDetail(ParameterDetail parameterDetail,String oldName);
}
