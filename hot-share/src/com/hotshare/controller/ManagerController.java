/**
 * Program  : ManagerController.java
 * Author   : lhzh
 * Create   : 2013-1-18 上午9:03:11
 */

package com.hotshare.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xml.sax.SAXException;

import com.hotshare.bean.Manager;
import com.hotshare.bean.Role;
import com.hotshare.constants.Constant;
import com.hotshare.exception.ControllerException;
import com.hotshare.exception.InitSystemException;
import com.hotshare.json.bean.DataGrid;
import com.hotshare.json.bean.DataInfo;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;
import com.hotshare.json.bean.ResultBean;
import com.hotshare.service.ManagerService;
import com.hotshare.service.RoleService;
import com.hotshare.util.GsonUtils;
import com.hotshare.util.InitModuleAndFunction;
import com.hotshare.util.ManagerInfoUtil;


/**
 * 后台用户管理控制器
 * 
 * @author lhzh
 * @version 1.0.0
 * @2013-1-18 上午9:03:11
 */
@Controller
@RequestMapping("/manager")
public class ManagerController extends BaseController {

	@Resource(name = "managerService")
	private ManagerService managerService;
	
	@Resource(name = "roleService")
	private RoleService roleService;
	

	private Logger logger = Logger.getLogger(ManagerController.class);

	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}
	/**
	 * 后台用户登录控制器
	 * 
	 * @author lhzh
	 * @create 2013-1-19 下午3:19:11
	 * @since
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws DocumentException 
	 * @throws InitSystemException 
	 */

	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, HttpServletResponse response) throws InitSystemException, DocumentException, ParserConfigurationException, SAXException, IOException {
		String account = ServletRequestUtils.getStringParameter(request, "account", null);
		String password = ServletRequestUtils.getStringParameter(request, "password", null);
		String verifyCode = ServletRequestUtils.getStringParameter(request, "verifyCode", null);
		if (ManagerInfoUtil.getSessionManagerInfo(request.getSession()) == null) {
			String sessionVerifyCode = (String) request.getSession().getAttribute(Constant.HOT_SHARE_MANAGE_VERIFY_CODE);
			if (verifyCode == null || !verifyCode.equalsIgnoreCase(sessionVerifyCode)) {
				request.setAttribute("R_String_message", "验证码输入错误!");
				return "jsp/login/login";
			} else {
				Manager manager = managerService.managerLogin(account, password, request.getSession());
				//拿到当前时间毫秒数
				System.out.println("客户端传进来的加密后的密码:"+password);
				String nowTime = System.currentTimeMillis()+"";
				request.getSession().setAttribute(account+Constant.CSS_WEB_USER_RANDOM, nowTime);
				System.out.println("更新session里随机时间串:"+(String)request.getSession().getAttribute(account+Constant.CSS_WEB_USER_RANDOM));
				if (manager != null) {
					ManagerInfoUtil.setSeesionManagerInfo(request.getSession(), manager);
					InitModuleAndFunction initModuleAndFunction = new InitModuleAndFunction();
					initModuleAndFunction.initFunction();
					initModuleAndFunction.initModule();
					return "redirect:/admin/main/index";
				} else {
					request.setAttribute("R_String_message", "用户名密码不匹配!");
					return "jsp/login/login";
				}
			}
		} else {
			InitModuleAndFunction initModuleAndFunction = new InitModuleAndFunction();
			initModuleAndFunction.initFunction();
			initModuleAndFunction.initModule();
			return "redirect:/admin/main/index";
		}
	}

	/**
	 * 后台用户退出管理系统控制器
	 * 
	 * @author lhzh
	 * @create 2013-1-19 下午3:20:34
	 * @since
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/loginOut")
	public String loginOut(HttpServletRequest request, HttpServletResponse response) {
		ManagerInfoUtil.loginOut(request);
		return "redirect:jsp/login/login.jsp";
	}

	/**
	 * 管理员修改密码控制器
	 * 
	 * @author lhzh
	 * @create 2013-1-20 下午5:35:22
	 * @since
	 * @param oldPwd
	 * @param newPwd
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/changePassword")
	public void changePassword(@RequestParam(value = "oldpwd", required = true) String oldPwd, @RequestParam(value = "newpwd", required = true) String newPwd, HttpServletRequest request, HttpServletResponse response) {
		ResultBean resultBean = new ResultBean();
		try {
			resultBean = managerService.changePassword(oldPwd, request.getSession(), newPwd);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			resultBean.setSuccess(false);
			resultBean.setMessage("密码更新异常,请联系相关技术人员");
		}
		addResponseData(response, JSONObject.fromObject(resultBean).toString());
	}
	
	
	
	/**
	 * 浏览系统人员列表
	 * @author lhzh
	 * @create 2014-8-05 下午5:35:22
	 */
	@RequestMapping(value = "/listManager")
	public void findAllManager(HttpServletRequest request, HttpServletResponse response, Model model, PageBean page, OrderBean order) throws ControllerException {
		DataGrid<Manager> datas = managerService.findAllManager(page, order);
		String resultJson = GsonUtils.objectToJsonDateSerializer(datas, Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}
	
	
	/**
	 * 增加系统人员
	 * @author gkx
	 * @create 2014-8-5 下午20:04:02
	 */
	@RequestMapping(value = "/addManager")
	public void addManager(HttpServletRequest request, HttpServletResponse response, Model model,Manager manager,String roleID,String groupId) throws ControllerException {
		Role roleBean = roleService.getRoleByID(Integer.parseInt(roleID));
		int result = managerService.addManager(manager);
		ResultBean resultBean = new ResultBean();
		if (result == 0) {
			resultBean.setCode(0);
			resultBean.setMessage("添加系统人员成功");
			resultBean.setSuccess(true);
		} else if(result == 1) {
			resultBean.setCode(1);
			resultBean.setMessage("添加系统人员失败");
			resultBean.setSuccess(false);
		} else if(result == 2) {
			resultBean.setCode(2);
			resultBean.setMessage("该系统人员已经存在");
			resultBean.setSuccess(false);
		}
		String resultJson = GsonUtils.objectToJsonDateSerializer(resultBean, Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}
	
	/**
	 * 删除系统人员
	 * @author gkx
	 * @create 2014-8-5 下午20:04:02
	 */
	@RequestMapping(value = "/delManager")
	public void delManager(HttpServletRequest request, HttpServletResponse response, Model model, String ids) throws ControllerException {
		int result = managerService.delManager(ids);
		ResultBean resultBean = new ResultBean();
		if (result == 0) {
			resultBean.setCode(0);
			resultBean.setMessage("删除系统人员成功");
			resultBean.setSuccess(true);
		} else if (result == 1) {
			resultBean.setCode(1);
			resultBean.setMessage("删除系统人员失败");
			resultBean.setSuccess(false);
		} 
		String resultJson = GsonUtils.objectToJsonDateSerializer(resultBean, Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}
	
	/**
	 * 获取系统人员详情
	 * @author gkx
	 * @create 2014-8-5 下午20:04:02
	 */
	@RequestMapping(value = "/managerInfo")
	public void getManagerInfo(HttpServletRequest request, HttpServletResponse response, Model model, String id) throws ControllerException {
		DataInfo<Manager>  manager = managerService.getManagerInfo(id);
		String resultJson = GsonUtils.objectToJsonDateSerializer(manager, Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}
	
	
	/**
	 * 修改系统人员
	 * @author gkx
	 * @create 2014-8-5 下午20:04:02
	 */
	@RequestMapping(value = "/modifyManager")
	public void modifyManager(HttpServletRequest request, HttpServletResponse response, Model model,Manager manager,String roleID,String groupId) throws ControllerException {
		Role roleBean = roleService.getRoleByID(Integer.parseInt(roleID));
		int result = managerService.modifyManager(manager);
		ResultBean resultBean = new ResultBean();
		if (result == 0) {
			resultBean.setCode(0);
			resultBean.setMessage("修改系统人员成功");
			resultBean.setSuccess(true);
		} else if(result == 1) {
			resultBean.setCode(1);
			resultBean.setMessage("修改系统人员失败");
			resultBean.setSuccess(false);
		} 
		String resultJson = GsonUtils.objectToJsonDateSerializer(resultBean, Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}
	
	/**跳转到修改页
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
	public String toEdit(HttpServletRequest request, HttpServletResponse response, ModelMap model,String managerId)throws ControllerException{
		List<Role> roles = roleService.queryAllRole();
		Manager manager = managerService.getManager(Integer.parseInt(managerId));
		model.addAttribute("roleList", roles);
		model.addAttribute("manager", manager);
		return "/jsp/manager/editManager";
		
	}
	
	/**
	 * 跳转到增加页面
	 * @Title: toAdd   
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
	@RequestMapping("toAdd")
	public String toAdd(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws ControllerException{
		List<Role> roles = roleService.queryAllRole();
		model.addAttribute("roleList", roles);
		return "/jsp/manager/addManager";
		
	}
	
	
	/**获得登陆随机串*/
	@RequestMapping(value = "/getRandom")
	public void getRandom(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,String account){
		if(account==null||"".equals(account)){
			account = "public";
		}
		ResultBean resultBean = new ResultBean();
		resultBean.setCode(0);
		//拿到当前时间毫秒数
		String nowTime = System.currentTimeMillis()+"";
		resultBean.setMessage(nowTime+"");
		//存到session里
		HttpSession session = request.getSession(true);
		session.setAttribute(account+Constant.CSS_WEB_USER_RANDOM, nowTime);
		System.out.println("往session里放随机时间串key值:"+account+Constant.CSS_WEB_USER_RANDOM+"-----"+"往session里放随机时间串value值:"+session.getAttribute(account+Constant.CSS_WEB_USER_RANDOM)+"===="+session.getId());
		resultBean.setSuccess(true);
		String resultJson = GsonUtils.objectToJson(resultBean);
		addResponseData(response, resultJson);
	}
}
