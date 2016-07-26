/**     
 * @FileName: InitModuleAndOperation.java   
 * @Package:com.hotshare.util   
 * @Description: TODO  
 * @author: lhzh    
 * @date:2014年8月5日 下午4:06:57   
 * @version V1.0     
 */

package com.hotshare.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import com.hotshare.bean.Function;
import com.hotshare.constants.Constant;
import com.hotshare.exception.InitSystemException;
import com.hotshare.exception.ServiceException;
import com.hotshare.service.FunctionService;
import com.hotshare.service.ModuleService;
import com.hotshare.util.SystemUtil;

/**
 * @ClassName: InitModuleAndOperation
 * @Description: TODO
 * @author: lhzh
 * @date:2014年8月5日 下午4:06:57
 */
public class InitModuleAndFunction{
	private static Logger logger = Logger.getLogger(InitModuleAndFunction.class);

	/**
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws UnsupportedEncodingException 
	 * @throws ServiceException 
	 * @throws DocumentException 
	 * @throws InitSystemException 
	 * 初始化模块
	 * @Title: initModule   
	 * @Description: TODO  
	 * @param @return  
	 * @return boolean  
	 * @throws
	 */
	public  boolean initModule() throws InitSystemException, DocumentException, ServiceException, UnsupportedEncodingException, ParserConfigurationException, SAXException, IOException {
		ModuleService moduleService = (ModuleService) ApplicationContextUtil.getBean("moduleService");
		String moduleStr = getConfigContent("init_module.xml");
		moduleService.initModule(moduleStr);
		return false;

	}

	/**
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * 初始化功能项
	 * @Title: initFunction   
	 * @Description: TODO  
	 * @param @return
	 * @param @throws InitSystemException
	 * @param @throws DocumentException  
	 * @return boolean  
	 * @throws
	 */
	public boolean initFunction() throws InitSystemException, DocumentException, ParserConfigurationException, SAXException, IOException{
		FunctionService functionService = (FunctionService) ApplicationContextUtil.getBean("functionService");
		String operation = getConfigContent(Constant.XML_Operation);
		List<Function> functions = getFunctions(operation);
		boolean result = functionService.InsertFunctionToDataBase(functions);
		if(result)
			return true;
		else
		return false;
	}

	/**
	 * 根据xml获取功能项数据
	 * @Title: getFunctions   
	 * @Description: TODO  
	 * @param @param xmlStr
	 * @param @return
	 * @param @throws InitSystemException
	 * @param @throws ParserConfigurationException
	 * @param @throws SAXException
	 * @param @throws IOException  
	 * @return List<Function>  
	 * @throws
	 */
	public List<Function> getFunctions(String xmlStr) throws InitSystemException, ParserConfigurationException, SAXException, IOException {
		List<Function> list = new ArrayList<Function>();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputStream input = new ByteArrayInputStream(xmlStr.getBytes("UTF-8"));
		org.w3c.dom.Document doc = builder.parse(input);
		Element root = doc.getDocumentElement();

		NodeList operationList = root.getElementsByTagName("operation");
		Integer operationListLen = operationList.getLength();
		for (int i = 0; i < operationListLen; ++i) {
			Node operationNode = operationList.item(i);
			Function function = new Function();
			for (Node node = operationNode.getFirstChild(); node != null; node = node
					.getNextSibling()) {
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					String nodeValue = node.getFirstChild().getNodeValue();
					if (node.getNodeName().equals("operationName")) {
						function.setFunName(nodeValue);
					}
					if (node.getNodeName().equals("operationCode")) {
						function.setFunCode(nodeValue);
					}
					if (node.getNodeName().equals("operationDesc")) {
						function.setDescInfo(nodeValue);
					}

				}
			}
			list.add(function);
		}

		return list;
	}

	/**
	 * 
	 * 获取配置文件内容
	 * 
	 * @author lhzh
	 * @create 2012-5-15 上午10:26:31
	 * @since
	 * @param name
	 * @return
	 * @throws DocumentException
	 */
	public static String getConfigContent(String name) throws InitSystemException, DocumentException {
		logger.info("Reading configuration file[" + name
				+ "] information, please waitting...");
		String path = SystemUtil.class.getResource("/" + name).getFile();
		if (System.getProperty("os.name").startsWith("Windows")) {
			path = path.substring(1).replaceAll("%20", " ");
		}
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(path));
		logger.info("Reading configuration file[" + name
				+ "] information success!");
		return document.asXML();
	}

}
