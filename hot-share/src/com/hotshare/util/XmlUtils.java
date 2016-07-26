/**
 * Program  : XmlUtils.java
 * Author   : misery
 * Create   : 2013-4-9 下午3:05:48
 */

package com.hotshare.util;
import java.io.File;  
import java.io.FileWriter;  
import java.io.StringWriter;  
  
import org.dom4j.Document;  
import org.dom4j.DocumentException;  
import org.dom4j.DocumentHelper;  
import org.dom4j.Element;  
import org.dom4j.io.OutputFormat;  
import org.dom4j.io.SAXReader;  
import org.dom4j.io.XMLWriter;  

/**
* xml dom4j工具�?
* @author   misery
* @version  1.0.0
* @2013-4-9 下午3:05:48
*/
 
public class XmlUtils {  
  
    /** 
     * 返回格式化的XML字段�?
     *  
     * @param document 
     *            要格式化的文�?
     * @param encoding 
     *            使用的编�?如果为null刚使用默认编�?gb2312) 
     * @return 格式化的XML字段�?
     */  
    public static String toXMLString(Document document, String encoding) {  
        if (encoding == null) {  
            encoding = "gb2312";  
        }  
        StringWriter writer = new StringWriter();  
        OutputFormat format = OutputFormat.createPrettyPrint();  
        format.setEncoding("gb2312");  
        XMLWriter xmlwriter = new XMLWriter(writer, format);  
        try {  
            xmlwriter.write(document);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return writer.toString();  
    }  
  
    /** 
     * 返回格式化的XML字段�?
     *  
     * @param element 
     *            要格式化的节点元�?
     * @param encoding 
     *            使用的编�?如果为null刚使用默认编�?gb2312) 
     * @return 格式化的XML字段�?
     */  
    public static String toXMLString(Element element, String encoding) {  
        if (encoding == null) {  
            encoding = "gb2312";  
        }  
        StringWriter writer = new StringWriter();  
        OutputFormat format = OutputFormat.createPrettyPrint();  
        format.setEncoding(encoding);  
        XMLWriter xmlwriter = new XMLWriter(writer, format);  
        try {  
            xmlwriter.write(element);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return writer.toString();  
    }  
  
    /** 
     * 格式化文档并输出到文�?
     *  
     * @param document 
     *            要输出的文档 
     * @param filename 
     *            XML文件�?
     * @param encoding 
     *            使用的编�?如果为null刚使用默认编�?gb2312) 
     * @return true or false 
     */  
    public static boolean toXMLFile(Document document, String filename,  
            String encoding) {  
        if (encoding == null) {  
            encoding = "gb2312";  
        }  
        boolean returnValue = false;  
        try {  
            XMLWriter output = null;  
            /** 格式化输�?类型IE浏览�?�� */  
            OutputFormat format = OutputFormat.createPrettyPrint();  
            /** 指定XML字符集编�?*/  
            format.setEncoding(encoding);  
            output = new XMLWriter(new FileWriter(new File(filename)), format);  
            output.write(document);  
            output.close();  
            /** 执行成功,�?���? */  
            returnValue = true;  
        } catch (Exception ex) {  
            ex.printStackTrace();  
            returnValue = false;  
        }  
        return returnValue;  
    }  
  
    /** 
     * 格式化XML文件并保�?
     *  
     * @param srcFileName 
     *            源XML文件 
     * @param desFileName 
     *            格式化后的XML文件,如果为null,则使用srcFileName 
     * @param encoding 
     *            使用的编�?如果为null刚使用默认编�?gb2312) 
     * @return true or false 
     */  
    public static boolean toXMLFile(String srcFileName, String desFileName,  
            String encoding) {  
        if (encoding == null) {  
            encoding = "gb2312";  
        }  
        if (desFileName == null) {  
            desFileName = srcFileName;  
        }  
        boolean returnValue = false;  
        try {  
            SAXReader saxReader = new SAXReader();  
            Document document = saxReader.read(new File(srcFileName));  
            XMLWriter output = null;  
            /** 格式化输�?类型IE浏览�?�� */  
            OutputFormat format = OutputFormat.createPrettyPrint();  
            /** 指定XML字符集编�?*/  
            format.setEncoding(encoding);  
            output = new XMLWriter(new FileWriter(new File(desFileName)),  
                    format);  
            output.write(document);  
            output.close();  
            /** 执行成功,�?���? */  
            returnValue = true;  
        } catch (Exception ex) {  
            ex.printStackTrace();  
            returnValue = false;  
        }  
        return returnValue;  
    }  
  
    /** 
     * 从读取XML文件 
     *  
     * @param fileName 
     * @return Document对象 
     */  
    public static Document read(String fileName) {  
        SAXReader reader = new SAXReader();  
        Document document = null;  
        try {  
            document = reader.read(new File(fileName));  
        } catch (DocumentException e) {  
            e.printStackTrace();  
        }  
        return document;  
    }  
  
    /** 
     * 从XML字符串转换到document 
     *  
     * @param xmlStr 
     *            XML字符�?
     * @return Document 
     */  
    public static Document parseText(String xmlStr) {  
        Document document = null;  
        try {  
            document = DocumentHelper.parseText(xmlStr);  
        } catch (DocumentException e) {  
            e.printStackTrace();  
        }  
        return document;  
    }  
}  

