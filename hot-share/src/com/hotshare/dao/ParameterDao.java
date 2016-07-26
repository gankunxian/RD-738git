/**
 * Program  : ParameterDao.java
 * Author   : gkx
 * Create   : 2014-8-12 下午2:12:08
 *
 */

package com.hotshare.dao;

import java.util.List;

import com.hotshare.bean.Parameter;
import com.hotshare.bean.ParameterDetail;
import com.hotshare.bean.User;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;


/**
 * 
 * @author gkx
 * @version 1.0.0
 * @2014-8-12 下午2:12:08
 */
public interface ParameterDao<T> extends BaseDao<T> {

	/**通过系统参数名称查找对象 */
	public Parameter getParameterByParameterName(String parameterName);

	/**查询有效的系统参数总数  gkx*/
	public long finAllParameterTotal();

	/**查找有效的系统参数  gkx */
	public List<Parameter> finAllParameter(OrderBean order, PageBean page);

	/**删除系统参数 gkx*/
	public void batchDelete(String ids);

	
	public long findParameterDetailTotal(int parameterId);

	public List<ParameterDetail> findParameterDetailByPId(OrderBean order, PageBean page,int parameterId);

	/**删除详细参数*/
	public void batchDelParameterDetail(String ids);

	/**通过id查出对象*/
	public Parameter getParameterById(int parameterId);

	/**通过名称和父id找到ParameterDetail对象*/
	public ParameterDetail getParameterDetailByDetailNameAndPId(String detailName, int parameterId);

}
