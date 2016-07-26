package com.hotshare.tag;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.hotshare.constants.Constant;


/**
 * 首页导航菜单权限控制
 * 
 * @author lhzh
 * @create 2013-1-31 下午1:42:16
 * @since
 */
public class MenuTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private static List<Element> xmlList = new ArrayList<Element>();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int doStartTag() {
		JspWriter out = pageContext.getOut();
		if (xmlList.size() == 0) {
			String path = MenuTag.class.getResource("/" + Constant.XML_RESOURCES_CSS).getFile();
			SAXReader reader = new SAXReader();
			Document document = DocumentHelper.createDocument();
			try {
				path =  java.net.URLDecoder.decode(path,"utf-8");
			} catch (UnsupportedEncodingException uee) {
				uee.printStackTrace();
			}
			File file = new File(path);
			try {
				document = reader.read(file);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			xmlList = document.selectNodes("//Resources");
		}
		StringBuffer html = new StringBuffer("");
		if (xmlList.size() > 0) {
			for (int i = 0; i < xmlList.size(); i++) {
				Element element = (Element) xmlList.get(i);
				List subResourceXmlList = element.selectNodes("subResources");
				html.append(createHtml(element, subResourceXmlList));
			}
		}
		try {
			out.write(html.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	@SuppressWarnings("rawtypes")
	private StringBuffer createHtml(Element element, List subResourceList) {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		StringBuffer html = new StringBuffer("");
		html.append("<div title=\"" + element.elementText("name") + "\" iconCls=\"icon-nav\"><ul>");
		for (int i = 0; i < subResourceList.size(); i++) {
			Element subElement = (Element) subResourceList.get(i);
			String src = basePath + subElement.elementText("src");
			String name = subElement.elementText("name");
			if (subElement.elementText("src").equals(""))
				src = "javascript:void(0)";
			html.append("<li><a rel=\"" + name + "\" href=\"" + src + "\" target=\"mainFrame\">" + subElement.elementText("name") + "</a></li>");
		}
		html.append("</ul></div>");
		return html;
	}
}
