package com.hotshare.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hotshare.bean.Function;
import com.hotshare.bean.ModuleFunction;
import com.hotshare.bean.Role;
import com.hotshare.bean.RoleModuleFunction;
import com.hotshare.dao.ModuleFunctionDao;
import com.hotshare.dao.RoleDao;
import com.hotshare.dao.RoleModuleFunctionDao;
import com.hotshare.exception.ServiceException;
import com.hotshare.json.bean.PageBean;
import com.hotshare.service.RoleService;

/**
 * @author lhzh
 * @version 创建时间：2014-8-11 下午02:03:44 类说明
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Resource(name = "roleDao")
	private RoleDao<Role> roleDao;

	@Resource(name = "roleModuleFunctionDao")
	private RoleModuleFunctionDao<RoleModuleFunction> roleModuleFunctionDao;

	@Resource(name = "moduleFuncDao")
	private ModuleFunctionDao<ModuleFunction> moduleFuncDao;

	@Override
	@Transactional(readOnly = true)
	public List<Role> queryAllRole() throws ServiceException {
		return roleDao.findRoles();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Role> queryRoleBySplit(Integer cp, Integer ps)
			throws ServiceException {
		PageBean page = new PageBean();
		page.setPage(cp);
		page.setRows(ps);
		return roleDao.findRolesByPage(null, page);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Role getRoleByID(Integer roleID) throws ServiceException {
		return roleDao.get(Role.class, roleID);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Function> getAuthCodeByRole(int id) throws ServiceException {
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean modRole(Role role, String moduleFunctions)
			throws ServiceException {

		roleDao.update(role);
		roleModuleFunctionDao.removeModuleFunctionByRoleID(role.getId());
		if (!moduleFunctions.equals("")) {
			String[] moduleFuns = moduleFunctions.split("\\|");
			for (int i = 0; i < moduleFuns.length; i++) {
				RoleModuleFunction rmf = new RoleModuleFunction();
				rmf.setRole(role);
				ModuleFunction moduleFunciton = new ModuleFunction();
				moduleFunciton.setId(Integer.parseInt(moduleFuns[i]));
				rmf.setModuleFunction(moduleFunciton);
				roleModuleFunctionDao.save(rmf);
			}
		}
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean addRole(Role role, String moduleFunctions)
			throws ServiceException {
		if (!moduleFunctions.equals("")) {
		String[] moduleFuns = moduleFunctions.split("\\|");
		for (int i = 0; i < moduleFuns.length; i++) {
			RoleModuleFunction rmf = new RoleModuleFunction();
			rmf.setRole(role);
			ModuleFunction moduleFunciton = new ModuleFunction();
			moduleFunciton.setId(Integer.parseInt(moduleFuns[i]));
			moduleFunciton = moduleFuncDao.get(ModuleFunction.class,
					Integer.parseInt(moduleFuns[i]));
			rmf.setModuleFunction(moduleFunciton);
			roleModuleFunctionDao.save(rmf);
		}
		}
		return true;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean delRole(int id) throws ServiceException {
		Role role = roleDao.get(Role.class, id);
		roleDao.delete(role);
		return true;
	}

}
