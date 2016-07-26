package com.hotshare.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotshare.bean.Manager;
import com.hotshare.bean.SystemMessage;
import com.hotshare.bean.SystemMessageUser;
import com.hotshare.bean.User;
import com.hotshare.constants.Constant;
import com.hotshare.exception.ControllerException;
import com.hotshare.json.bean.DataGrid;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;
import com.hotshare.json.bean.ResultBean;
import com.hotshare.service.SystemMessageService;
import com.hotshare.service.SystemMessageUserService;
import com.hotshare.service.UserService;
import com.hotshare.util.DateFiend;
import com.hotshare.util.GsonUtils;
import com.hotshare.util.ManagerInfoUtil;

/**
 * @author lhzh
 * @version 创建时间：2014-8-19 下午08:12:22 类说明
 */
@Controller
@RequestMapping("/systemMessage")
public class SystemMessageController extends BaseController {

	@Resource(name = "systemMessageService")
	private SystemMessageService systemMessageService;
	
	@Resource(name = "systemMessageUserService")
	private SystemMessageUserService systemMessageUserService;

	@Resource(name = "userService")
	private UserService userService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}

	/**
	 * 跳转到发布系统消息页面
	 * 
	 * @Title: toAdd
	 * @Description: TODO
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @return
	 * @param @throws ControllerException
	 * @return String
	 * @throws
	 */
	@RequestMapping("/toAdd")
	public String toAdd(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, String ids)
			throws ControllerException {
		model.addAttribute("ids", ids);
		return "/jsp/message/addSystemMessage";

	}

	/**
	 * 系统消息列表
	 * @Title: listSystemMessage
	 * @Description: TODO
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @param page
	 * @param @param order
	 * @param @throws ControllerException
	 * @return void
	 * @throws
	 */
	@RequestMapping("/list")
	public void listSystemMessage(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, PageBean page,
			OrderBean order) throws ControllerException {
		DataGrid<SystemMessage> datas = systemMessageService
				.findAllSystemMessage(page, order);
		String resultJson = GsonUtils.objectToJsonDateSerializer(datas,
				Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}

	/**
	 * 发布系统消息
	 * 
	 * @Title: addSystemMessage
	 * @Description: TODO
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/addSystemMessage")
	public void addSystemMessage(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, SystemMessage systemMessage) {
		systemMessage.setCreateTime(DateFiend.getSystemDate());
		int result = systemMessageService.addSystemMessage(systemMessage);
		ResultBean resultBean = new ResultBean();
		if (result == 0) {
			resultBean.setCode(0);
			resultBean.setMessage("增加系统消息成功");
			resultBean.setSuccess(true);
		} else if (result == 1) {
			resultBean.setCode(1);
			resultBean.setMessage("增加系统消息失败");
			resultBean.setSuccess(false);
		}
		String resultJson = GsonUtils.objectToJson(resultBean);
		addResponseData(response, resultJson);

	}
	
	/**
	 * 删除系统系统消息
	 * @Title: delSystemMessage   
	 * @Description: TODO  
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @param ids
	 * @param @throws ControllerException  
	 * @return void  
	 * @throws
	 */
	@RequestMapping("/delSystemMessage")
	public void delSystemMessage(HttpServletRequest request, HttpServletResponse response, Model model, String ids) throws ControllerException {
		int result = systemMessageService.delSystemMessage(ids);
		ResultBean resultBean = new ResultBean();
		if (result == 0) {
			resultBean.setCode(0);
			resultBean.setMessage("删除系統消息成功");
			resultBean.setSuccess(true);
		} else if (result == 1) {
			resultBean.setCode(1);
			resultBean.setMessage("删除系統消息失败");
			resultBean.setSuccess(false);
		} 
		String resultJson = GsonUtils.objectToJsonDateSerializer(resultBean, Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}
	
	
	
	/**
	 * 跳转到编辑系统消息页面
	 * 
	 * @Title: toAdd
	 * @Description: TODO
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @return
	 * @param @throws ControllerException
	 * @return String
	 * @throws
	 */
	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, String messageId)
			throws ControllerException {
		SystemMessage systemMessage = systemMessageService.findSystemMessage(Integer.parseInt(messageId));
		model.addAttribute("systemMessage", systemMessage);
		return "/jsp/message/editSystemMessage";

	}
	
	
	/**
	 * 编辑系统消息
	 * @Title: edit   
	 * @Description: TODO  
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @param messageId
	 * @param @return
	 * @param @throws ControllerException  
	 * @return String  
	 * @throws
	 */
	@RequestMapping("/editSystemMessage")
	public void editSystemMessage(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, SystemMessage systemMessage)
			throws ControllerException {
		int result =  systemMessageService.modifySystemMessage(systemMessage);
		ResultBean resultBean = new ResultBean();
		if (result == 0) {
			resultBean.setCode(0);
			resultBean.setMessage("編輯系統消息成功");
			resultBean.setSuccess(true);
		} else if (result == 1) {
			resultBean.setCode(1);
			resultBean.setMessage("編輯系統消息失败");
			resultBean.setSuccess(false);
		} 
		String resultJson = GsonUtils.objectToJsonDateSerializer(resultBean, Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);

	}
	
	/**
	 * 跳转到发送系统消息页面
	 * @Title: toSendSystemMessage   
	 * @Description: TODO  
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @param ids
	 * @param @return
	 * @param @throws ControllerException  
	 * @return String  
	 * @throws
	 */
	@RequestMapping("/toSendSystemMessage")
	public String toSendSystemMessage(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,String ids)throws ControllerException{
		List<SystemMessage> datas = systemMessageService.findAllSystemMessage();
		model.addAttribute("systemMessageList", datas);
		model.addAttribute("ids", ids);
		return "/jsp/user/sendSystemMessage";
		
	}
	
	
	/**
	 * 发送系统消息
	 * @Title: sendSystemMessage   
	 * @Description: TODO  
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @param ids
	 * @param @return
	 * @param @throws ControllerException  
	 * @return String  
	 * @throws
	 */
	@RequestMapping("/sendSystemMessage")
	public void sendSystemMessage(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,String ids,String messageId)throws ControllerException{
		List<SystemMessageUser> systemMessageUsers = new ArrayList<SystemMessageUser>();
		String[] idsArr = ids.split(",");
		SystemMessage systemMessage = systemMessageService.findSystemMessage(Integer.parseInt(messageId));
		for (int i = 0; i < idsArr.length; i++) {
			Manager manager = ManagerInfoUtil.getSessionManagerInfo(request.getSession());
			User user = userService.getUser(Integer.parseInt(idsArr[i]));
			SystemMessageUser systemMessageUser = new SystemMessageUser();
			systemMessageUser.setAccepter(user);
			systemMessageUser.setSender(manager);
			systemMessageUser.setMessage(systemMessage);
			systemMessageUsers.add(systemMessageUser);
		}
		int result = systemMessageUserService.addSystemMessageUsers(systemMessageUsers);
		ResultBean resultBean = new ResultBean();
		if (result == 0) {
			resultBean.setCode(0);
			resultBean.setMessage("发送系統消息成功");
			resultBean.setSuccess(true);
		} else if (result == 1) {
			resultBean.setCode(1);
			resultBean.setMessage("发送系統消息失败");
			resultBean.setSuccess(false);
		} 
		String resultJson = GsonUtils.objectToJson(resultBean);
		addResponseData(response, resultJson);
	}
	
	
	
	
	
}
