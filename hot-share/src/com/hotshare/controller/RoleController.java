package com.hotshare.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotshare.bean.Function;
import com.hotshare.bean.Role;
import com.hotshare.exception.ControllerException;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;
import com.hotshare.json.bean.ResultBean;
import com.hotshare.service.RoleService;
import com.hotshare.util.GsonUtils;

/**
 * 
 * @author lhzh
 * @version 创建时间：2014-8-11 下午01:56:36
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

	@Resource(name = "roleService")
	private RoleService roleService;

	/**
	 * 角色列表
	 * 
	 * @author lhzh
	 * @create 2014-8-05 下午5:35:22
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/list")
	public void findAllRole(HttpServletRequest request,
			HttpServletResponse response, Model model, PageBean page,
			OrderBean order) throws ControllerException {
		String cp = request.getParameter("cp");
		String ps = request.getParameter("ps");
		Integer totalSize; // 总个数

		if (StringUtils.isEmpty(cp) || StringUtils.isBlank(cp)) {
			cp = "1";
		}
		List<Role> roleList = null;
		try {
			List<Role> allRole = roleService.queryAllRole();
			totalSize = allRole.size();
			if (StringUtils.isEmpty(ps) || StringUtils.isBlank(ps)) {
				roleList = allRole;
				ps = String.valueOf(totalSize);
			} else {
				Integer temp = Integer.valueOf(ps);
				roleList = roleService.queryRoleBySplit(Integer.valueOf(cp),
						temp);
			}

			StringBuilder json = new StringBuilder("{\"rows\":");
			json.append("[");

			if (roleList != null && !roleList.isEmpty()) {
				for (Role role : roleList) {
					json.append("{\"id\":").append(role.getId()).append(",");
					json.append("\"groupName\":\"").append(role.getName())
							.append("\",");
					json.append("\"groupDesc\":\"").append(role.getGroupDesc())
							.append("\",");
					json.append("\"roleList\":")
							.append(getOperByRole(role.getId())).append("},");
				}
				json.deleteCharAt(json.length() - 1);
			}
			json.append("],");

			json.append("\"total\":").append(totalSize);

			json.append("}");

			this.addResponseData(response, json.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControllerException("queryManagerUserBySplit error!");

		}

	}

	private StringBuilder getOperByRole(Integer roleID) throws Exception {
		StringBuilder groupJson = new StringBuilder("[");
		Role role = roleService.getRoleByID(roleID);

		if (role == null) {
			return new StringBuilder("[]");
		}

		List<Function> list = roleService.getAuthCodeByRole(role.getId());
		if (list != null && list.size() != 0) {
			int i = 1;
			for (Function function : list) {
				// groupJson.append("{\"id\":").append(i).append(",");
				// groupJson.append("\"permission\":\"").append(function.get).append(".").append(map.get("funCode")).append("\"},");
				//
				// ++i;
			}
			groupJson.deleteCharAt(groupJson.length() - 1);
		}
		groupJson.append("]");
		return groupJson;
	}

	/**
	 * 角色信息修改
	 * @Title: roleMod   
	 * @Description: TODO  
	 * @param @param request
	 * @param @param response
	 * @param @throws Exception  
	 * @return void  
	 * @throws
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/mod")
	public void roleMod(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ResultBean resultBean  = new ResultBean();
		String id = request.getParameter("id");
		String roleName = request.getParameter("roleName");
		String roleDesc = request.getParameter("roleDesc");
		String moduleFunctions = request.getParameter("moduleFunctions");
		Role role = new Role();
		role.setId(Integer.parseInt(id));
		role.setName(roleName);
		role.setGroupDesc(roleDesc);
		boolean result  = roleService.modRole(role,moduleFunctions);
		if(result){
			resultBean.setSuccess(true); 
			resultBean.setCode(0);
			resultBean.setMessage("更新角色成功");
		}
		
		this.addResponseData(response, GsonUtils.objectToJson(resultBean));
		
	}
	
	
	/**
	 * 角色添加
	 * @Title: roleAdd   
	 * @Description: TODO  
	 * @param @param request
	 * @param @param response
	 * @param @throws Exception  
	 * @return void  
	 * @throws
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/add")
	public void roleAdd(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ResultBean resultBean  = new ResultBean();
		String roleName = request.getParameter("roleName");
		String roleDesc = request.getParameter("roleDesc");
		String moduleFunctions = request.getParameter("moduleFunctions");
		Role role = new Role();
		role.setName(roleName);
		role.setGroupDesc(roleDesc);
		boolean result  = roleService.addRole(role,moduleFunctions);
		if(result){
			resultBean.setSuccess(true); 
			resultBean.setCode(0);
			resultBean.setMessage("添加角色成功");
		}
		
		this.addResponseData(response, GsonUtils.objectToJson(resultBean));
		
	}
	
	
	/**
	 * 角色删除
	 * @Title: roleDel   
	 * @Description: TODO  
	 * @param @param request
	 * @param @param response
	 * @param @throws Exception  
	 * @return void  
	 * @throws
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/del")
	public void roleDel(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ResultBean resultBean  = new ResultBean();
		String id = request.getParameter("id");
		boolean result  = roleService.delRole(Integer.parseInt(id));
		if(result){
			resultBean.setSuccess(true); 
			resultBean.setCode(0);
			resultBean.setMessage("删除角色成功");
		}
		
		this.addResponseData(response, GsonUtils.objectToJson(resultBean));
		
	}

}
