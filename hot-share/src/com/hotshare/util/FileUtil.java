/**
 * @Program : FileUtil.java
 * @Author : xietingyan
 * @Create : 2011-2-2
 */
package com.hotshare.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUtil {

	private final static Log logger = LogFactory.getLog(FileUtil.class);

	/**
	 * 拷贝文件并改�?
	 * 
	 * @param oldPath
	 * @param newPath
	 * @param newName
	 * @return
	 */
	@SuppressWarnings("unused")
	public static boolean copyFileByPath(String oldPath, String newPath, String newName) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);

			if (oldfile.exists()) { // 文件存在�?
				InputStream inStream = new FileInputStream(oldPath); // 读入原文�?
				FileOutputStream fs = new FileOutputStream(newPath + "/" + newName);
				byte[] buffer = new byte[1024];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节�?文件大小
					fs.write(buffer, 0, byteread);
				}
				fs.flush();
				fs.close();
				inStream.close();

				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 拷贝文件并改�?
	 * 
	 * @param inStream
	 * @param newPath
	 * @param newName
	 * @return
	 */
	public boolean copyFile(InputStream inStream, String newPath, String newName) {

		Log log = LogFactory.getLog(this.getClass());
		try {
			int bytesum = 0;
			int byteread = 0;

			FileOutputStream fs = new FileOutputStream(newPath + "/" + newName);
			byte[] buffer = new byte[102400];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread; // 字节�?文件大小
				fs.write(buffer, 0, byteread);
				log.info("已上传字节数:" + bytesum);
			}
			fs.flush();
			fs.close();
			inStream.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 拷贝文件�?
	 * 
	 * @param oldPath
	 * @param newPath
	 */
	public static void copyFolder(String oldPath, String newPath) {

		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件�?
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件�?
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * 从文件中读出数据放到String�?
	 * 
	 * @param filePath
	 *            文件路径.
	 * @param encoding
	 *            编码格式.
	 * @return 文件的String.
	 */
	public static String readFile(String filePath, String encoding) {
		try {
			return new String(readFileByte(filePath), encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	//
	// /**
	// * @param filePath
	// * @return
	// */
	// public static byte[] readFileByte(String filePath) {
	// byte[] data = null;
	// FileInputStream fissrc;
	// try {
	// fissrc = new FileInputStream(filePath);
	// int len = fissrc.available();
	// data = new byte[len];
	// int actual = 0;
	// int bytesread = 0;
	// while ((bytesread != len) && (actual != -1)) {
	// actual = fissrc.read(data, bytesread, len - bytesread);
	// bytesread += actual;
	// }
	// fissrc.close();
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// return data;
	// }

	/**
	 * 将二进制写入文件�?前提是文件的目录已存�?.
	 * 
	 * @param file
	 * @param byt
	 * @return
	 * @author sunny
	 * @create 2007-2-2 下午03:57:13
	 */
	public static boolean writeByteToFile(File file, byte[] byt) {
		try {
			FileOutputStream out = new FileOutputStream(file);
			try {
				out.write(byt);
				out.flush();
				out.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 将二进制写入文件�?前提是文件的目录已存�?.
	 * 
	 * @param file
	 * @param byt
	 * @return
	 * @author liuhz
	 * @create 2007-2-2 下午03:57:13
	 */
	public static boolean writeStringToFile(File file, String content) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			Writer out = new OutputStreamWriter(fos, "utf-8");
			try {
				out.write(content);
				out.flush();
				out.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	

	/**
	 * 创建文件�?
	 * 
	 * @author Link Wang
	 * @create 2007-12-4 上午11:08:48
	 * @since
	 * @param dirPath
	 * @param overwrite
	 * @return
	 */
	public synchronized static File mkDir(String dirPath, boolean overwrite) {
		try {
			File dir = new File(dirPath);
			if (overwrite && dir.isDirectory()) {
				FileUtils.deleteDirectory(dir);
			}
			dir.mkdirs();
			return dir;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 删除文件�?
	 * 
	 * @author Link Wang
	 * @create 2007-9-6 上午09:57:24
	 * @since
	 * @param dirPath
	 * @throws Exception
	 */
	public synchronized static boolean rmDir(String dirPath) {
		try {
			FileUtils.deleteDirectory(new File(dirPath));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * 删除文件�?
	 * 
	 * @author Link Wang
	 * @create 2007-9-6 上午09:57:24
	 * @since
	 * @param dirPath
	 * @throws Exception
	 */
	public synchronized static boolean rmDir(File dir) {
		try {
			FileUtils.deleteDirectory(dir);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * 删除文件
	 * 
	 * @author Link Wang
	 * @create 2007-9-6 上午09:58:00
	 * @since
	 * @param filePath
	 * @throws Exception
	 */
	public static boolean delete(String filePath) throws Exception {
		try {
			return delete(new File(filePath));
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * 删除文件
	 * 
	 * @author Link Wang
	 * @create 2007-9-6 上午09:58:00
	 * @since
	 * @param filePath
	 * @throws Exception
	 */
	public synchronized static boolean delete(File file) throws Exception {
		try {
			if (file.isFile()) {
				file.delete();
			}
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * 读文件，取得其字节数�?
	 * 
	 * @author Link Wang
	 * @create 2007-9-26 上午11:51:09
	 * @since
	 * @param file
	 * @return
	 */
	public static byte[] readFileByte(File file) {
		try {
			return FileUtils.readFileToByteArray(file);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 读文件，取得其字节数�?
	 * 
	 * @author Link Wang
	 * @create 2007-11-27 下午12:01:47
	 * @since
	 * @param filePath
	 * @return
	 */
	public static byte[] readFileByte(String filePath) {
		try {
			return FileUtils.readFileToByteArray(new File(filePath));
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 获得项目的物理路�?�?���?��字符是分隔符).<br>
	 * 1.在容器里,返回的是WEB-INF的上�?��目录.<br>
	 * �? D:\workspace\AodAppServer\web\ 2.在应用程序下,返回的bin目录.<br>
	 * 
	 * @return
	 * @author sunny
	 * @create 2007-10-25 下午03:44:40
	 */
	public static String getAbsPathOfProject() {
		String url = null;
		try {
			url = URLDecoder.decode(FileUtil.class.getClassLoader().getResource("").toString(), "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// Win file:/E:/projects/Eclipse/workspace/SAS-Studio/WEB-INF/classes/
		// Linux file:/home/share/SAS-TOMCAT/webapps/SAS/WEB-INF/classes/
		String reg0 = "file:(.+?)WEB-INF"; // 在Tomcat�?
		Matcher mat0 = Pattern.compile(reg0, Pattern.CASE_INSENSITIVE).matcher(url);
		String reg1 = "file:(.+?bin/)"; // 应用程序�?
		Matcher mat1 = Pattern.compile(reg1, Pattern.CASE_INSENSITIVE).matcher(url);
		Matcher mat = mat0.find() ? mat0 : mat1;
		if (mat.find(0)) {
			String path = mat.group(1);
			path = path.replaceAll("/", "\\" + File.separator);
			return File.separator.equals("\\") ? path.substring(1) : path;
		}
		return null;
	}

	/**
	 * 创建文件�?
	 * 
	 * @param filePath
	 * @return
	 * @throws Exception
	 * @author sunny
	 * @create 2007-10-25 下午03:07:18
	 */
	public synchronized static File mkDir(String filePath) throws Exception {
		File file = new File(filePath);
		if (!file.exists())
			file.mkdirs();
		return file;
	}

	/**
	 * 获得文件的扩展名(.txt)
	 * 
	 * @param fileName
	 * @return
	 * @author sunny
	 * @create 2007-5-31 上午09:56:23
	 */
	public static String getExtension(String fileName) {
		if (fileName == null)
			return null;
		String tmp = fileName.substring(fileName.lastIndexOf("."));
		return tmp;
	}

	/**
	 * 将二进制写入文件
	 * 
	 * @param absPath
	 *            绝对路径(包括文件�?
	 * @param byt
	 * @return
	 * @author sunny
	 * @create 2007-10-30 上午09:45:20
	 */
	public static boolean writeByteToFile(String absPath, byte[] byt) {
		absPath = absPath.replaceAll("/", "\\" + File.separator);
		String[] dirs = absPath.split("\\" + File.separator);

		File file = new File(dirs[0]);
		if (dirs.length > 1) {
			for (int i = 1; i < dirs.length - 1; i++) {
				file = new File(file, dirs[i]);
				if (!file.exists()) {
					file.mkdirs();
				}
			}
		}
		file = new File(file, dirs[dirs.length - 1]);
		try {
			return writeByteToFile(file, byt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 删除文件
	 * 
	 * @param file
	 * @return
	 * @author sunny
	 * @create 2007-10-30 上午11:15:58
	 */
	public static boolean delFile(File file) {
		if (file == null)
			return false;
		return file.delete();
	}

	/**
	 * 删除文件
	 * 
	 * @param absPath
	 * @return
	 * @author sunny
	 * @create 2007-10-30 上午11:16:05
	 */
	public static boolean delFile(String absPath) {
		return delFile(new File(absPath));
	}

	/**
	 * 从路径中截取文件�?
	 * 
	 * @param path
	 *            路径
	 * @param split
	 *            路径分隔�?
	 * @return
	 * @author sunny
	 * @create 2007-11-13 上午09:45:09
	 */
	public static String getFileName(String path, String split) {
		if (path == null)
			return null;
		String slit = split;
		if (split == null)
			slit = "/";
		return path.substring(path.lastIndexOf(slit) + slit.length(), path.length());
	}

	/**
	 * �?��文件路径中是否含有index.htm(l)页面
	 * 
	 * @param path
	 * @return
	 * @author sunny
	 * @create 2007-11-19 下午04:04:23
	 */
	public static boolean containsIndexPage(String path) {
		String reg = "index\\.htm[l]{0,1}";
		Matcher m = Pattern.compile(reg, Pattern.CASE_INSENSITIVE).matcher(path);
		if (m.find())
			return true;
		return false;
	}

	/**
	 * 判断扩展名是否为js和html
	 * 
	 * @param name
	 * @return
	 * @author sunny
	 * @create 2007-11-22 上午11:58:04
	 */
	public static boolean endWithHtmlOrJS(String name) {
		if (name == null)
			return false;
		String[] ends = { ".js", ".html", ".htm" };
		name = name.toLowerCase();
		for (int i = 0; i < ends.length; i++) {
			if (name.substring(name.lastIndexOf(".")).equals(ends[i]))
				return true;
		}
		return false;
	}

	/**
	 * �?���?��文件内容是否是一个zip文件
	 * 
	 * @author kongxiangpeng
	 * @create 2007-5-21 下午04:19:56
	 * @since
	 * @param zipStream
	 *            输入的zip�?
	 * @return
	 */
	public static boolean isZipFile(byte[] zipStream) {
		ByteArrayInputStream content = new ByteArrayInputStream(zipStream, 0, 4);
		int head = content.read();
		head = (head << 8) | content.read();
		head = (head << 8) | content.read();
		head = (head << 8) | content.read();
		return head == 0x504b0304;
	}

	/**
	 * 从给定的字符串中创建文件系统路径�?
	 * 
	 * @param directory
	 *            给定的路径表示字符串�?
	 * @return 是否成功 成功==true.
	 */
	public static boolean makeDirectory(String directory) {
		File file = new File(directory);
		if (!file.exists()) {
			if (file.mkdirs()) {
				logger.debug("make dirctory success!:" + directory);
				return true;
			} else {
				logger.debug("make dirctory <<<<<<<faile>>>>>>>!:" + directory);
				return false;
			}
		} else {
			logger.debug("this directory is existed!:" + directory);
			return true;
		}
	}

	/**
	 * 从String中创建文件，如果此文件存在覆盖掉，如果不存在创建此文�?如果文件路径上的目录没有则创建此目录�?
	 * 
	 * @param filePath
	 * @param fileData
	 * @return 写文件是否成�?
	 * @throws IOException
	 */
	public static boolean directWriteFile(String filePath, String fileData, String charsetName) {
		filePath = filePath.replace("/", File.separator);
		if (filePath == null || fileData == null) {
			logger.debug("the fileName or fileData is null: fileName=" + filePath + " fileData=" + fileData);
			return false;
		} else if (filePath.equals("") || filePath.trim().equals("")) {
			logger.debug("the fileName or fileData is   : fileName=" + filePath + " fileData=" + fileData);
			return false;
		}
		String fileDir = filePath.substring(0, filePath.lastIndexOf(System.getProperty("file.separator")));
		// String fileDir = filePath.substring(0, filePath.lastIndexOf("/"));
		// fileDir = fileDir.replaceAll(" 0-0", "");
		boolean flag = makeDirectory(fileDir);
		if (!flag) {
			return false;
		}
		FileOutputStream fileOutputStream = null;
		try {
			byte[] data = fileData.getBytes(charsetName);
			File file = new File(filePath);
			if (!file.exists()) {
				logger.debug("this file is not exist!:" + filePath);
				file.createNewFile();
				logger.debug("creat file!:" + filePath);
			}
			fileOutputStream = new FileOutputStream(filePath);
			fileOutputStream.write(data);
			fileOutputStream.close();
			logger.debug("write file:" + filePath);
			return true;
		} catch (FileNotFoundException e) {
			logger.debug(e.getMessage());
		} catch (IOException e) {
			logger.debug(e.getMessage());
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					logger.debug(e.toString());
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * 将byte[]的数据写入文件，如果此文件存在则覆盖掉，如果不存在创建此文件,目录不存在也直接创建其目录�?
	 * 
	 * @param filePath
	 *            文件全路�?
	 * @param fileData
	 *            文件数据.
	 * @return 写文件是否成�?
	 */
	public static boolean directWriteFile(String filePath, byte[] fileData) {
		filePath = filePath.replace("/", File.separator);
		if (filePath == null || fileData == null) {
			logger.debug("filePath or fileData is null");
			return false;
		} else if (filePath.trim().equals("")) {
			logger.debug("filePath is \"\"!");
			return false;
		}
		String fileDir = filePath.substring(0, filePath.lastIndexOf(System.getProperty("file.separator")));
		boolean flag = makeDirectory(fileDir);
		if (!flag) {
			return false;
		}
		FileOutputStream write;
		try {
			write = new FileOutputStream(filePath);
			write.write(fileData);
			write.close();
			logger.debug("write file:" + filePath + " success!");
			return true;
		} catch (FileNotFoundException e) {
			logger.debug(e.getMessage());
		} catch (IOException e) {
			logger.debug(e.getMessage());
		}
		return false;
	}

	/**
	 * 将指定的文件copy到指定的路径下，如果该路径不存在将创建此路径�?
	 * 
	 * @param filePath
	 *            全路�?
	 * @param toDirectoryPath
	 *            全路�?
	 * @return 是否成功 成功==true.
	 */
	public static boolean copyFile(String filePath, String toDirectoryPath) {
		if (filePath == null || toDirectoryPath == null) {
			logger.debug("the filePath or toDirectory is null at _File.copyFile()");
			return false;
		} else if (filePath.trim().equals("") || toDirectoryPath.trim().equals("")) {
			logger.debug("the filePath or toDirectory is \"\" at _File.copuFile()");
			return false;
		}
		File directory = new File(toDirectoryPath);
		if (!directory.exists()) {
			makeDirectory(toDirectoryPath);
		}

		File file = new File(filePath);
		String toFilePath = toDirectoryPath + File.separator + file.getName(); // mod
		File toFile = new File(toFilePath);
		try {
			if (!file.isFile()) {
				logger.debug("can not get FileInputStream from this file:" + filePath);
				return false;
			}
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(toFile);
			byte[] data = new byte[fis.available()];
			int bytesRead;
			while ((bytesRead = fis.read(data)) != -1) {
				fos.write(data, 0, bytesRead);
			}
			fos.flush();
			fos.close();
			fis.close();
			logger.debug("copy file file:" + file + "-----to:" + toDirectoryPath);
			return true;
		} catch (FileNotFoundException e) {
			logger.debug(e.getMessage());
		} catch (IOException e) {
			logger.debug(e.getMessage());
		}
		return false;
	}

	/**
	 * 将指定的文件copy到指定的路径下，如果该路径不存在将创建此路径�?
	 * 
	 * @param sfilePath
	 *            全路�?
	 * @param toDirectoryPath
	 *            全路�?
	 * @return 是否成功 成功==true.
	 */
	public static boolean copyFileByFilePath(String sfilePath, String dfilePath) {
		sfilePath = sfilePath.replace("/", File.separator);
		dfilePath = dfilePath.replace("/", File.separator);
		if (sfilePath == null || dfilePath == null) {
			logger.debug("the filePath is null at _File.copyFile()");
			return false;
		} else if (sfilePath.trim().equals("") || dfilePath.trim().equals("")) {
			logger.debug("the filePath is \"\" at _File.copuFile()");
			return false;
		}
		String toFileDir = dfilePath.substring(0, dfilePath.lastIndexOf(System.getProperty("file.separator")));
		boolean flag = makeDirectory(toFileDir);
		if (!flag) {
			return false;
		}
		File file = new File(sfilePath);
		File toFile = new File(dfilePath);
		try {
			if (!file.isFile()) {
				logger.debug("can not get FileInputStream from this file:" + sfilePath);
				return false;
			}
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(toFile);
			byte[] data = new byte[fis.available()];
			int bytesRead;
			while ((bytesRead = fis.read(data)) != -1) {
				fos.write(data, 0, bytesRead);
			}
			fos.flush();
			fos.close();
			fis.close();
			logger.debug("copy file file:" + file + "-----to:" + dfilePath);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
		return false;
	}

	public static String getUniqueId(int len) {
		if (len < 10)
			len = 10;
		return getUniqueId(len, 999999999);
	}

	private static String getUniqueId(int length, int maxrandom) {
		String tmpstr = "";
		String thread = (new SimpleDateFormat("yyyyMMddhhmmssSSS")).format(new Date()) + Integer.toString(getRandom(maxrandom));
		thread = Integer.toString(thread.hashCode());
		if (thread.indexOf("-") >= 0)
			thread = thread.substring(thread.indexOf("-") + 1);
		if (thread.length() < length) {
			for (int i = thread.length(); i < length; i++)
				tmpstr = tmpstr + "0";

			thread = tmpstr + thread;
		}
		return thread;
	}

	public static int getRandom(int max) {
		return (int) (Math.random() * (double) max);
	}

}
