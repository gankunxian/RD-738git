/**
 * Program  : UserController.java
 * Author   : lhzh
 * Create   : 2013-1-18 上午9:03:11
 */

package com.hotshare.controller;

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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotshare.bean.User;
import com.hotshare.constants.Constant;
import com.hotshare.exception.ControllerException;
import com.hotshare.json.bean.DataGrid;
import com.hotshare.json.bean.DataInfo;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;
import com.hotshare.json.bean.ResultBean;
import com.hotshare.service.UserService;
import com.hotshare.util.DateFiend;
import com.hotshare.util.GsonUtils;

/**
 * 前端用户管理控制器
 * 
 * @author lhzh
 * @version 1.0.0
 * @2013-1-18 上午9:03:11
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Resource(name = "userService")
	private UserService userService;


	private Logger logger = Logger.getLogger(UserController.class);

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}

	/** 跳转到用户列表页 */
	@RequestMapping("toListUser")
	public String toListUser(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, String account,
			String groupId, String state) throws ControllerException {
		modelMap.addAttribute("account", account);
		modelMap.addAttribute("groupId", groupId);
		modelMap.addAttribute("state", state);
		return "/jsp/user/listUser";
	}

	/**
	 * 浏览前端用户列表
	 * 
	 * @author lhzh
	 * @create 2014-8-05 下午5:35:22
	 */
	@RequestMapping(value = "/listUser")
	public void findAllUser(HttpServletRequest request,
			HttpServletResponse response, Model model, PageBean page,
			OrderBean order, String account, String groupId, String state)
			throws ControllerException {
		DataGrid<User> datas = userService.findAllUser(page, order, account,
				groupId, state);
		String resultJson = GsonUtils.objectToJsonDateSerializer(datas,
				Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}

	/**
	 * 修改前端用户状态
	 * 
	 * @author gkx
	 * @create 2014-8-5 下午20:04:02
	 */
	@RequestMapping(value = "/doUserState")
	public void doUserState(HttpServletRequest request,
			HttpServletResponse response, Model model, String ids, int state)
			throws ControllerException {
		int result = userService.doUserState(ids, state);
		String str = "启用";
		if (state == 0) {
			str = "禁用";
		}
		ResultBean resultBean = new ResultBean();
		if (result == 0) {
			resultBean.setCode(0);
			resultBean.setMessage(str + "用户成功");
			resultBean.setSuccess(true);
		} else if (result == 1) {
			resultBean.setCode(1);
			resultBean.setMessage(str + "用户失败");
			resultBean.setSuccess(false);
		}
		String resultJson = GsonUtils.objectToJsonDateSerializer(resultBean,
				Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}

	/**
	 * 获取前端用户详情
	 * 
	 * @author gkx
	 * @create 2014-8-5 下午20:04:02
	 */
	@RequestMapping(value = "/userInfo")
	public void getUserInfo(HttpServletRequest request,
			HttpServletResponse response, Model model, String id)
			throws ControllerException {
		DataInfo<User> User = userService.getUserInfo(id);
		String resultJson = GsonUtils.objectToJsonDateSerializer(User,
				Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}

	/**
	 * 修改前端用户
	 * 
	 * @author gkx
	 * @create 2014-8-5 下午20:04:02
	 */
	@RequestMapping(value = "/modifyUser")
	public void modifyUser(HttpServletRequest request,
			HttpServletResponse response, Model model, User user)
			throws ControllerException {
		String groupId = request.getParameter("groupId");
		int result = userService.modifyUser(user);
		ResultBean resultBean = new ResultBean();
		if (result == 0) {
			resultBean.setCode(0);
			resultBean.setMessage("修改前端用户成功");
			resultBean.setSuccess(true);
		} else if (result == 1) {
			resultBean.setCode(1);
			resultBean.setMessage("修改前端用户失败");
			resultBean.setSuccess(false);
		}
		String resultJson = GsonUtils.objectToJsonDateSerializer(resultBean,
				Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}

	/**
	 * 跳转到添加用户页面
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
	@RequestMapping(value = "/toAdd")
	public String toAdd(HttpServletRequest request,
			HttpServletResponse response, ModelMap model)
			throws ControllerException {
		return "jsp/user/addUser";

	}

	/**
	 * 增加用户
	 * 
	 * @Title: addUser
	 * @Description: TODO
	 * @param @param request
	 * @param @param response
	 * @param @param models
	 * @param @param user
	 * @param @throws ControllerException
	 * @return void
	 * @throws
	 */
	@RequestMapping(value = "/addUser")
	public void addUser(HttpServletRequest request,
			HttpServletResponse response, ModelMap models, User user,
			String groupId) throws ControllerException {
		user.setRegTime(DateFiend.getSystemDate());
		int result = userService.addUser(user);
		ResultBean resultBean = new ResultBean();
		if (result == 0) {
			resultBean.setCode(0);
			resultBean.setMessage("添加前端用户成功");
			resultBean.setSuccess(true);
		} else if (result == 1) {
			resultBean.setCode(1);
			resultBean.setMessage("添加前端用户失败");
			resultBean.setSuccess(false);
		} else if (result == 2) {
			resultBean.setCode(2);
			resultBean.setMessage("该前端用户已经存在");
			resultBean.setSuccess(false);
		}
		String resultJson = GsonUtils.objectToJson(resultBean);
		addResponseData(response, resultJson);

	}

	/**
	 * 跳转到修改页
	 * 
	 * @Title: toEdit
	 * @Description: TODO
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @param managerId
	 * @param @return
	 * @param @throws ControllerException
	 * @return String
	 * @throws
	 */
	@RequestMapping("toEdit")
	public String toEdit(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, int userId)
			throws ControllerException {
		User user = userService.getUser(userId);
		modelMap.addAttribute("user", user);
		return "/jsp/user/editUser";
	}
}
