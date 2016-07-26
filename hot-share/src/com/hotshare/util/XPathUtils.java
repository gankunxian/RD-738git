/**
 * Program  : XPathUtils.java
 * Author   : misery
 * Create   : 2013-4-10 上午8:55:03
 */

package com.hotshare.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * xpath解析xml工具�?
 * 
 * @author misery
 * @create 2013-4-10 上午8:56:59
 * @since
 */
@SuppressWarnings("rawtypes")
public class XPathUtils {

	private String xml = null;

	private static Logger logger = Logger.getLogger(XPathUtils.class);

	public XPathUtils(String xml) {
		this.xml = xml;
	}

	public XPathUtils(Document xmlDocument) {
		this.xmlDocument = xmlDocument;
	}

	/**
	 * 从一个xml加载数据
	 * 
	 * @author misery
	 * @create 2013-4-10 上午8:57:42
	 * @since
	 * @param xml
	 * @return
	 */
	public static XPathUtils createXmlPathReaderByXml(String xml) {
		XPathUtils xmlReader = null;

		try {
			xmlReader = new XPathUtils(xml);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return xmlReader;
	}

	/**
	 * 从一个XML文件中读取数�?
	 * 
	 * @author misery
	 * @create 2013-4-10 上午8:57:50
	 * @since
	 * @param xmlFile
	 * @return
	 */
	public static XPathUtils createXmlPathReaderByFile(File xmlFile) {
		XPathUtils xmlReader = null;
		Document xmlDocument = null;

		try {
			xmlDocument = new SAXReader().read(new FileInputStream(xmlFile));
			xmlReader = new XPathUtils(xmlDocument);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return xmlReader;
	}

	/**
	 * 从系统环境中查找�?��xml文件，加载数�?
	 * 
	 * @author misery
	 * @create 2013-4-10 上午8:58:32
	 * @since
	 * @param res
	 * @return
	 */
	public static XPathUtils createXmlPathReaderByResource(String res) {
		XPathUtils xmlReader = null;
		Document xmlDocument = null;
		URL url = ClassLoader.getSystemResource(res);
		if (null == url) {
			throw new RuntimeException(res + " not found.");
		}

		try {
			xmlDocument = new SAXReader().read(new FileInputStream(new File(url.toURI())));
			xmlReader = new XPathUtils(xmlDocument);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return xmlReader;
	}

	/**
	 * 返回xpath�?��示的结点�?
	 * 
	 * @author misery
	 * @create 2013-4-10 上午8:58:38
	 * @since
	 * @param xpath
	 * @return
	 */
	public int countNode(String xpath) {
		Document fileDocument = readDocument();

		if (!findXPath(xpath)) {
			return 0;
		}

		List es = fileDocument.selectNodes(xpath);

		if (null == es) {
			return 0;
		} else {
			return es.size();
		}
	}

	/**
	 * 以xpath方式得到结果的文�?
	 * 
	 * @author misery
	 * @create 2013-4-10 上午8:58:46
	 * @since
	 * @param xpath
	 * @return
	 */
	public String getText(String xpath) {
		String text = "";
		Document fileDocument = readDocument();

		if (!findXPath(xpath)) {
			return "";
		}

		Node node = fileDocument.selectSingleNode(xpath);

		text = node.getText();
		return text;
	}

	/**
	 * 以xpath方式得到结点某属性的�?
	 * 
	 * @author misery
	 * @create 2013-4-10 上午8:58:53
	 * @since
	 * @param xpath
	 * @param attrName
	 * @return
	 */
	public String getAttribute(String xpath, String attrName) {
		String attr = "";
		Document fileDocument = readDocument();

		if (!findXPath(xpath)) {
			return "";
		}

		List es = fileDocument.selectNodes(xpath);
		attr = ((Element) es.get(0)).attributeValue(attrName);

		return attr;
	}

	
	public List getNodes(String xpath) {
		Document fileDocument = readDocument();

		if (!findXPath(xpath)) {
			return null;
		}

		List es = fileDocument.selectNodes(xpath);

		return es;
	}

	/**
	 * 查找xpath是否存在
	 * 
	 * @author misery
	 * @create 2013-4-10 上午8:59:01
	 * @since
	 * @param xpath
	 * @return
	 */
	public boolean findXPath(String xpath) {
		Document fileDocument = readDocument();

		try {
			List es = fileDocument.selectNodes(xpath);

			if (0 == es.size()) {
				return false;
			} else {
				return true;
			}
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * 读取配置文件
	 * 
	 * @author misery
	 * @create 2013-4-10 上午8:59:10
	 * @since
	 * @return
	 */
	private Document readDocument() {
		SAXReader reader = new SAXReader();
		try {
			if (null == xmlDocument) {
				xmlDocument = reader.read(new StringReader(xml));
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}

		return xmlDocument;
	}

	private Document xmlDocument = null;

	public static void main(String[] args) {
		File file = new File(FileUtil.getAbsPathOfProject() + "res/oitv-channel-GuangZhou.xml");
		XPathUtils xmlReader = XPathUtils.createXmlPathReaderByFile(file);
		String frequence = xmlReader.getAttribute("//channel", "frequence");
		System.out.println(frequence);
	}
}
