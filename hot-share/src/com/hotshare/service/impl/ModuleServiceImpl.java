/**  
 * Project Name:hot-share  
 * File Name:InitModuleServiceImpl.java  
 * Package Name:com.hotshare.service.impl  
 * Date:2014年8月5日下午2:37:22  
 * Copyright (c) 2014, lhzh All Rights Reserved.  
 *  
 */

package com.hotshare.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.hotshare.bean.Module;
import com.hotshare.bean.ModuleFunction;
import com.hotshare.dao.ModuleDao;
import com.hotshare.exception.ServiceException;
import com.hotshare.json.bean.FunctionJsonBean;
import com.hotshare.json.bean.ModuleTree;
import com.hotshare.service.ModuleService;

/**
 * ClassName:InitModuleServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014年8月5日 下午2:37:22 <br/>
 * 
 * @author lhzh
 * @version
 * @since JDK 1.6
 * @see
 */

@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {

	private ModuleDao<Module> moduleDao;

	/**
	 * moduleDao.
	 * 
	 * @return the moduleDao
	 * @since JDK 1.6
	 */
	public ModuleDao<Module> getModuleDao() {
		return moduleDao;
	}

	/**
	 * moduleDao.
	 * 
	 * @param moduleDao
	 *            the moduleDao to set
	 * @since JDK 1.6
	 */
	@Autowired
	public void setModuleDao(ModuleDao<Module> moduleDao) {
		this.moduleDao = moduleDao;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.hotshare.service.ModuleService#InsertModuleToDataBase(java.util.List)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, noRollbackFor = Exception.class)
	public boolean InsertModuleToDataBase(List<Module> modules)
			throws ServiceException {
		for (int i = 0; i < modules.size(); i++) {
			moduleDao.save(modules.get(i));
		}
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, noRollbackFor = Exception.class)
	public void initModule(String moduleStr) throws ServiceException,
			ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputStream input = new ByteArrayInputStream(
				moduleStr.getBytes("UTF-8"));
		Document doc = builder.parse(input);
		Element root = doc.getDocumentElement();

		List<Module> xmlModList = new ArrayList<Module>();

		NodeList moduleList = root.getElementsByTagName("module");
		Integer moduleLen = moduleList.getLength();
		for (int i = 0; i < moduleLen; ++i) {
			Node moduleNode = moduleList.item(i);
			Module module = new Module();
			for (Node node = moduleNode.getFirstChild(); node != null; node = node
					.getNextSibling()) {
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					String nodeValue = node.getFirstChild().getNodeValue();
					if (node.getNodeName().equals("moduleCode")) {
						module.setModuleCode(nodeValue);
					} else if (node.getNodeName().equals("moduleTitle")) {
						module.setName(nodeValue);
					}
					module.setParentID(0);
				}
			}
			saveOrUpdate(module);
			xmlModList.add(module);
		}

		List<Module> parentModuleList = null;
		try {
			parentModuleList = moduleDao.queryAllModule();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Module parentModule : parentModuleList) {
			NodeList subModuleNodeList = root.getElementsByTagName("subModule");
			Integer subModuleLen = subModuleNodeList.getLength();
			for (int j = 0; j < subModuleLen; ++j) {
				Node subNode = subModuleNodeList.item(j);
				Node parentNode = subNode.getParentNode().getFirstChild()
						.getNextSibling().getFirstChild();
				if (parentNode.getNodeValue().equals(
						parentModule.getModuleCode())) {
					Module module = new Module();
					for (Node node = subNode.getFirstChild(); node != null; node = node
							.getNextSibling()) {
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Node n = node.getFirstChild();
							String subNodeValue = n.getNodeValue();
							if (node.getNodeName().equals("moduleCode")) {
								module.setModuleCode(subNodeValue);
							} else if (node.getNodeName().equals("moduleTitle")) {
								module.setName(subNodeValue);
							} else if (node.getNodeName().equals("moduleURL")) {
								module.setUrl(subNodeValue);
							}
						}
					}
					module.setParentID(parentModule.getId());
					saveOrUpdate(module);
					xmlModList.add(module);

				}
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, noRollbackFor = Exception.class)
	public synchronized boolean saveOrUpdate(Module module) {
		List<Module> list = moduleDao.find(
				"from Module as p where p.moduleCode = '"
						+ module.getModuleCode() + "'", null);
		if (list == null || list.isEmpty()) {
			try {
				moduleDao.save(module);
			} catch (Exception e) {
				return false;
			}
		} else {
			Module moduleNew = list.get(0);
			moduleNew.setModuleCode(module.getModuleCode());
			moduleNew.setModuleDesc(module.getModuleDesc());
			moduleNew.setName(module.getName());
			moduleNew.setParentID(module.getParentID());
			moduleNew.setUrl(module.getUrl());
			try {
				moduleDao.update(moduleNew);
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, noRollbackFor = Exception.class)
	public List<ModuleTree> findModuleTree(int roleID) throws ServiceException {
		List<ModuleTree> treelist = new ArrayList<ModuleTree>();
		List<Module> modules = moduleDao.queryModuleByParentID(0);

		for (Iterator<Module> iter = modules.iterator(); iter.hasNext();) {
			ModuleTree moduleTree = new ModuleTree();
			Module module = iter.next();
			moduleTree.setBusinessID(module.getId()+"");
			moduleTree.setBusinessName(module.getName());
			moduleTree.setParentID(module.getParentID()+"");
			if(module.getParentID() == 0){
				List<Module> subModules = new ArrayList<Module>();
				subModules = moduleDao.queryModuleByParentID(module.getId());
				List<ModuleTree> subModuleTrees = new ArrayList<ModuleTree>();
				for (Iterator<Module> subIter = subModules.iterator(); subIter.hasNext();) {
					ModuleTree subModuleTree = new ModuleTree();
					List<FunctionJsonBean> functions = new ArrayList<FunctionJsonBean>();
					Module subModule = subIter.next();
					subModuleTree.setBusinessID(subModule.getId()+"");
					subModuleTree.setBusinessName(subModule.getName());
					subModuleTree.setParentID(subModule.getParentID()+"");
					List<ModuleFunction> moduleFuns = moduleDao.findFunctionsByModuleID(subModule.getId());
					for (Iterator<ModuleFunction> funsIter = moduleFuns.iterator(); funsIter.hasNext();) {
						ModuleFunction moduleFunction = funsIter.next();
						FunctionJsonBean funcJson = new FunctionJsonBean();
						funcJson.setId(moduleFunction.getId());
						funcJson.setFunctionCode(moduleFunction.getFunction().getFunCode());
						funcJson.setFunctionName(moduleFunction.getFunction().getFunName());
						funcJson.setFunctionFlag(moduleDao.checkFunctionCode(roleID, moduleFunction.getModule().getId(), moduleFunction.getFunction().getId())?"1":"0");
						functions.add(funcJson);
					}
					subModuleTree.setOperation(functions);
					subModuleTrees.add(subModuleTree);
				}
				moduleTree.setChildren(subModuleTrees);
			}
			treelist.add(moduleTree);
			
		}
		return treelist;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, noRollbackFor = Exception.class)
	public List<ModuleTree> findAllModuleTree() throws ServiceException {
		List<ModuleTree> treelist = new ArrayList<ModuleTree>();
		List<Module> modules = moduleDao.queryModuleByParentID(0);

		for (Iterator<Module> iter = modules.iterator(); iter.hasNext();) {
			ModuleTree moduleTree = new ModuleTree();
			Module module = iter.next();
			moduleTree.setBusinessID(module.getId()+"");
			moduleTree.setBusinessName(module.getName());
			moduleTree.setParentID(module.getParentID()+"");
			if(module.getParentID() == 0){
				List<Module> subModules = new ArrayList<Module>();
				subModules = moduleDao.queryModuleByParentID(module.getId());
				List<ModuleTree> subModuleTrees = new ArrayList<ModuleTree>();
				for (Iterator<Module> subIter = subModules.iterator(); subIter.hasNext();) {
					ModuleTree subModuleTree = new ModuleTree();
					List<FunctionJsonBean> functions = new ArrayList<FunctionJsonBean>();
					Module subModule = subIter.next();
					subModuleTree.setBusinessID(subModule.getId()+"");
					subModuleTree.setBusinessName(subModule.getName());
					subModuleTree.setParentID(subModule.getParentID()+"");
					List<ModuleFunction> moduleFuns = moduleDao.findFunctionsByModuleID(subModule.getId());
					for (Iterator<ModuleFunction> funsIter = moduleFuns.iterator(); funsIter.hasNext();) {
						ModuleFunction moduleFunction = funsIter.next();
						FunctionJsonBean funcJson = new FunctionJsonBean();
						funcJson.setId(moduleFunction.getId());
						funcJson.setFunctionCode(moduleFunction.getFunction().getFunCode());
						funcJson.setFunctionName(moduleFunction.getFunction().getFunName());
						funcJson.setFunctionFlag("0");
						functions.add(funcJson);
					}
					subModuleTree.setOperation(functions);
					subModuleTrees.add(subModuleTree);
				}
				moduleTree.setChildren(subModuleTrees);
			}
			treelist.add(moduleTree);
			
		}
		return treelist;
	}

}
