package com.hotshare.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotshare.exception.ControllerException;
import com.hotshare.json.bean.ModuleTree;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;
import com.hotshare.service.ModuleService;
import com.hotshare.util.GsonUtils;

/**
 * @author lhzh
 * @version 创建时间：2014-8-12 下午03:45:06 类说明
 */
@Controller
@RequestMapping("/module")
public class ModuleController extends BaseController{
	
	@Resource(name = "moduleService")
	private ModuleService moduleService;
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/tree")
	public void findModuleFunctionTree(HttpServletRequest request,
			HttpServletResponse response, Model model, PageBean page,
			OrderBean order) throws ControllerException {
		String roleID = request.getParameter("roleID");
		List<ModuleTree> treeList = moduleService.findModuleTree(Integer.parseInt(roleID));
		this.addResponseData(response, GsonUtils.objectToJson(treeList));
	}
	
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/allTree")
	public void findModuleFunctionAllTree(HttpServletRequest request,
			HttpServletResponse response, Model model, PageBean page,
			OrderBean order) throws ControllerException {
		List<ModuleTree> treeList = moduleService.findAllModuleTree();
		this.addResponseData(response, GsonUtils.objectToJson(treeList));
	}


}
