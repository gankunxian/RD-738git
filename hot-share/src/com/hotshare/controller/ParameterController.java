/**
 * Program  : ParameterController.java
 * Author   : gkx
 * Create   : 2014-8-12 上午9:03:11
 */

package com.hotshare.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotshare.bean.Manager;
import com.hotshare.bean.Parameter;
import com.hotshare.bean.ParameterDetail;
import com.hotshare.bean.User;
import com.hotshare.bean.Zone;
import com.hotshare.constants.Constant;
import com.hotshare.exception.ControllerException;
import com.hotshare.json.bean.DataGrid;
import com.hotshare.json.bean.DataInfo;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;
import com.hotshare.json.bean.ResultBean;
import com.hotshare.service.ParameterService;
import com.hotshare.util.GsonUtils;
import com.hotshare.util.ManagerInfoUtil;


/**
 * 后台系统参数管理控制器
 * 
 * @author gkx
 * @version 1.0.0
 * @2014-8-12 上午9:03:11
 */
@Controller
@RequestMapping("/parameter")
public class ParameterController extends BaseController {

	@Resource(name = "parameterService")
	private ParameterService parameterService;

	private Logger logger = Logger.getLogger(ParameterController.class);

	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}
	
	/**
	 * 浏览系统参数列表
	 * @author gkx
	 * @create 2014-8-05 下午5:35:22
	 */
	@RequestMapping(value = "/listParameter")
	public void findAllParameter(HttpServletRequest request, HttpServletResponse response, Model model, PageBean page, OrderBean order) throws ControllerException {
		DataGrid<Parameter> datas = parameterService.findAllParameter(page, order);
		String resultJson = GsonUtils.objectToJsonDateSerializer(datas, Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}
	
	
	/**
	 * 增加系统参数
	 * @author gkx
	 * @create 2014-8-5 下午20:04:02
	 */
	@RequestMapping(value = "/addParameter")
	public void addParameter(HttpServletRequest request, HttpServletResponse response, Model model,Parameter parameter) throws ControllerException {
		Manager manager = ManagerInfoUtil.getSessionManagerInfo(request.getSession());
		parameter.setCreatePerson(manager.getName());
		parameter.setModifyPerson(manager.getName());
		int result = parameterService.addParameter(parameter);
		ResultBean resultBean = new ResultBean();
		if (result == 0) {
			resultBean.setCode(0);
			resultBean.setMessage("添加系统参数成功");
			resultBean.setSuccess(true);
		} else if(result == 1) {
			resultBean.setCode(1);
			resultBean.setMessage("添加系统参数失败");
			resultBean.setSuccess(false);
		} else if(result == 2) {
			resultBean.setCode(2);
			resultBean.setMessage("该系统参数已经存在");
			resultBean.setSuccess(false);
		}
		String resultJson = GsonUtils.objectToJsonDateSerializer(resultBean, Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}
	
	/**
	 * 删除系统参数
	 * @author gkx
	 * @create 2014-8-5 下午20:04:02
	 */
	@RequestMapping(value = "/delParameter")
	public void delParameter(HttpServletRequest request, HttpServletResponse response, Model model, String ids) throws ControllerException {
		int result = parameterService.delParameter(ids);
		ResultBean resultBean = new ResultBean();
		if (result == 0) {
			resultBean.setCode(0);
			resultBean.setMessage("删除系统参数成功");
			resultBean.setSuccess(true);
		} else if (result == 1) {
			resultBean.setCode(1);
			resultBean.setMessage("删除系统参数失败");
			resultBean.setSuccess(false);
		} 
		String resultJson = GsonUtils.objectToJsonDateSerializer(resultBean, Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}
	
	/**
	 * 获取系统参数详情
	 * @author gkx
	 * @create 2014-8-5 下午20:04:02
	 */
	@RequestMapping(value = "/parameterInfo")
	public void getParameterInfo(HttpServletRequest request, HttpServletResponse response, Model model, String id) throws ControllerException {
		DataInfo<Parameter>  parameter = parameterService.getParameterInfo(id);
		String resultJson = GsonUtils.objectToJsonDateSerializer(parameter, Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}
	
	
	/**
	 * 修改系统参数
	 * @author gkx
	 * @create 2014-8-5 下午20:04:02
	 */
	@RequestMapping(value = "/modifyParameter")
	public void modifyParameter(HttpServletRequest request, HttpServletResponse response, Model model,Parameter parameter) throws ControllerException {
		Manager manager = ManagerInfoUtil.getSessionManagerInfo(request.getSession());
		parameter.setModifyPerson(manager.getName());
		String oldName = request.getParameter("oldName");
		int result = parameterService.modifyParameter(parameter,oldName);
		ResultBean resultBean = new ResultBean();
		if (result == 0) {
			resultBean.setCode(0);
			resultBean.setMessage("修改系统参数成功");
			resultBean.setSuccess(true);
		} else if(result == 1) {
			resultBean.setCode(1);
			resultBean.setMessage("修改系统参数失败");
			resultBean.setSuccess(false);
		} else if(result == 2){
			resultBean.setCode(2);
			resultBean.setMessage("该系统参数已经存在");
			resultBean.setSuccess(false);
		}
		String resultJson = GsonUtils.objectToJsonDateSerializer(resultBean, Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}
	
	
	/**
	 * 获得某个系统参数下的内容列表
	 * @author gkx
	 * @create 2014-8-12 下午17:53:51
	 * @since 
	 * @param request
	 * @param response
	 * @param model
	 * @param page
	 * @param order
	 * @throws ControllerException
	 */
	@RequestMapping(value = "/listParameterDetail")
	public void listParameterDetail(HttpServletRequest request, HttpServletResponse response, Model model, PageBean page, OrderBean order) throws ControllerException {
		int parameterId = Integer.parseInt(request.getParameter("parameterId"));
		DataGrid<ParameterDetail> datas = parameterService.findParameterDetailByPId(page, order,parameterId);
		String resultJson = GsonUtils.objectToJsonDateSerializer(datas, Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}
	
	/**删除详细参数*/
	@RequestMapping(value = "/delParameterDetail")
	public void delParameterDetail(HttpServletRequest request, HttpServletResponse response, Model model, String ids) throws ControllerException {
		int result = parameterService.delParameterDetail(ids);
		ResultBean resultBean = new ResultBean();
		if (result == 0) {
			resultBean.setCode(0);
			resultBean.setMessage("删除成功");
			resultBean.setSuccess(true);
		} else if (result == 1) {
			resultBean.setCode(1);
			resultBean.setMessage("删除失败");
			resultBean.setSuccess(false);
		} 
		String resultJson = GsonUtils.objectToJsonDateSerializer(resultBean, Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}
	
	
	/**
	 * 去详细参数详情列表页面
	 * @author gkx
	 * @create 2014-8-13 下午20:05:16
	 */
	@RequestMapping(value = "/toListParameterDetail")
	public String toListParameterDetail(HttpServletRequest request, HttpServletResponse response, Model model, int parameterId) throws ControllerException, IOException {
		Parameter parameter = parameterService.getParameterById(parameterId);//通过id查出对象
		request.setAttribute("parameter", parameter);
		request.setAttribute("parameterName", parameter.getParameterName());
		return "/jsp/parameter/listParameterDetail";
	}
	
	
	/**
	 * 增加详细参数
	 * @author gkx
	 * @create 2014-8-13 下午20:04:02
	 */
	@RequestMapping(value = "/addParameterDetail")
	public void addParameterDetail(HttpServletRequest request, HttpServletResponse response, Model model,ParameterDetail parameterDetail) throws ControllerException {
		Manager manager = ManagerInfoUtil.getSessionManagerInfo(request.getSession());
		int parameterId = Integer.parseInt(request.getParameter("parameterId"));
		parameterDetail.setParameter(parameterService.getParameterById(parameterId));
		parameterDetail.setCreatePerson(manager.getName());
		parameterDetail.setModifyPerson(manager.getName());
		int result = parameterService.addParameterDetail(parameterDetail);
		ResultBean resultBean = new ResultBean();
		if (result == 0) {
			resultBean.setCode(0);
			resultBean.setMessage("添加详细参数成功");
			resultBean.setSuccess(true);
		} else if(result == 1) {
			resultBean.setCode(1);
			resultBean.setMessage("添加详细参数失败");
			resultBean.setSuccess(false);
		} else if(result == 2) {
			resultBean.setCode(2);
			resultBean.setMessage("该详细参数已经存在");
			resultBean.setSuccess(false);
		}
		String resultJson = GsonUtils.objectToJsonDateSerializer(resultBean, Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}
	
	/**
	 * 获取详细参数详情
	 * @author gkx
	 * @create 2014-8-13 下午20:04:02
	 */
	@RequestMapping(value = "/parameterDetailInfo")
	public void getParameterDetailInfo(HttpServletRequest request, HttpServletResponse response, Model model, String id) throws ControllerException {
		DataInfo<ParameterDetail>  parameterDetail = parameterService.getParameterDetailInfo(id);
		String resultJson = GsonUtils.objectToJsonDateSerializer(parameterDetail, Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}
	
	
	/**
	 * 修改详细参数
	 * @author gkx
	 * @create 2014-8-5 下午20:04:02
	 */
	@RequestMapping(value = "/modifyParameterDetail")
	public void modifyParameterDetail(HttpServletRequest request, HttpServletResponse response, Model model,ParameterDetail parameterDetail) throws ControllerException {
		Manager manager = ManagerInfoUtil.getSessionManagerInfo(request.getSession());
		int parameterId = Integer.parseInt(request.getParameter("parameterId"));
		parameterDetail.setParameter(parameterService.getParameterById(parameterId));
		parameterDetail.setModifyPerson(manager.getName());
		String oldName = request.getParameter("oldName");
		
		int result = parameterService.modifyParameterDetail(parameterDetail,oldName);
		ResultBean resultBean = new ResultBean();
		if (result == 0) {
			resultBean.setCode(0);
			resultBean.setMessage("修改详细参数成功");
			resultBean.setSuccess(true);
		} else if(result == 1) {
			resultBean.setCode(1);
			resultBean.setMessage("修改详细参数失败");
			resultBean.setSuccess(false);
		} else if(result == 2){
			resultBean.setCode(2);
			resultBean.setMessage("该详细参数已经存在");
			resultBean.setSuccess(false);
		}
		String resultJson = GsonUtils.objectToJsonDateSerializer(resultBean, Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}
}
