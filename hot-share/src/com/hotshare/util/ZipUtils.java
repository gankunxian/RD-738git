package com.hotshare.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

/**
 * ant压缩文件工具�?
 * 
 * @author misery
 * @create 2013-4-11 下午5:57:21
 * @since
 */
public class ZipUtils {

	private static Logger logger = Logger.getLogger(ZipUtils.class);

	private static final int BUFFER = 10240;

	public ZipUtils(String pathName) {

	}
	/**
	 * 压缩文件
	 * 
	 * @author misery
	 * @create 2013-4-11 下午5:57:36
	 * @since
	 * @param srcPathName
	 * @param zipFile
	 */
	public static void compress(String srcPathName, File zipFile) {
		File file = new File(srcPathName);
		if (!file.exists())
			throw new RuntimeException(srcPathName + "不存在！");
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
			CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
			ZipOutputStream out = new ZipOutputStream(cos);
			String basedir = "";
			compress(file, out, basedir);
			out.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 压缩文件
	 * 
	 * @author misery
	 * @create 2013-4-11 下午5:58:15
	 * @since
	 * @param file
	 * @param out
	 * @param basedir
	 */
	private static void compress(File file, ZipOutputStream out, String basedir) {
		/* 判断是目录还是文�?*/
		if (file.isDirectory()) {
			// logger.info("压缩�? + basedir + file.getName());
			compressDirectory(file, out, basedir);
		} else {
			// logger.info("压缩�? + basedir + file.getName());
			compressFile(file, out, basedir);
		}
	}

	/**
	 * 压缩目录
	 * 
	 * @author misery
	 * @create 2013-4-11 下午5:59:00
	 * @since
	 * @param dir
	 * @param out
	 * @param basedir
	 */
	private static void compressDirectory(File dir, ZipOutputStream out, String basedir) {
		if (!dir.exists())
			return;

		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			/* 递归 */
			compress(files[i], out, basedir + dir.getName() + "/");
		}
	}

	/**
	 * 压缩文件
	 * 
	 * @author misery
	 * @create 2013-4-11 下午5:59:12
	 * @since
	 * @param file
	 * @param out
	 * @param basedir
	 */
	private static void compressFile(File file, ZipOutputStream out, String basedir) {
		if (!file.exists()) {
			return;
		}
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			ZipEntry entry = new ZipEntry(basedir + file.getName());
			out.putNextEntry(entry);
			int count;
			byte data[] = new byte[BUFFER];
			while ((count = bis.read(data, 0, BUFFER)) != -1) {
				out.write(data, 0, count);
			}
			bis.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 解压文件
	 * 
	 * @author misery
	 * @create 2013-4-11 下午5:59:25
	 * @since
	 * @param srcFile
	 * @param dest
	 * @param deleteFile
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static void unCompress(String srcFile, String dest, boolean deleteFile) throws Exception {
		File file = new File(srcFile);
		if (!file.exists()) {
			throw new Exception("解压文件不存�?");
		}
		ZipFile zipFile = new ZipFile(file, "gbk");
		Enumeration e = zipFile.getEntries();
		while (e.hasMoreElements()) {
			ZipEntry zipEntry = (ZipEntry) e.nextElement();
			if (zipEntry.isDirectory()) {
				String name = zipEntry.getName();
				name = name.substring(0, name.length() - 1);
				File f = new File(dest + name);
				f.mkdirs();
			} else {
				File f = new File(dest + zipEntry.getName());
				f.getParentFile().mkdirs();
				// logger.info("file --->  " + f.getPath());
				f.createNewFile();
				InputStream is = zipFile.getInputStream(zipEntry);
				FileOutputStream fos = new FileOutputStream(f);
				int length = 0;
				byte[] b = new byte[1024];
				while ((length = is.read(b)) != -1) {
					fos.write(b, 0, length);
				}
				is.close();
				fos.close();
			}
		}

		if (zipFile != null) {
			zipFile.close();
		}

		if (deleteFile) {
			file.delete();
		}
	}

	/**测试文件是否符合某种树结�?
	 * @author misery
	 * @create 2013-4-11 下午5:59:57
	 * @since
	 * @param format
	 * @param zipFile
	 * @return 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static boolean verifyFile(String[] format, ZipFile zipFile) throws IOException {
		if (format == null || format.length == 0) {
			return true;
		}

		List<String> pathList = new ArrayList<String>();
		Enumeration<ZipEntry> entrys = (Enumeration<ZipEntry>) zipFile.getEntries();
		while (entrys.hasMoreElements()) {
			pathList.add(entrys.nextElement().getName());
		}
		Integer size = pathList.size();
		for (String path : format) {
			int i = 0;
			for (; i < size; ++i) {
				String pathEntry = pathList.get(i);
				if (pathEntry.contains(path)) {
					break;
				}
			}
			if (i >= size) {
				zipFile.close();
				logger.warn("the file not exist : " + path);
				return false;
			}
		}
		zipFile.close();
		return true;
	}
	/**
	 * 取出单个文件
	 * 
	 * @author misery
	 * @create 2013-4-11 下午6:00:36
	 * @since
	 * @param zipEntryName
	 * @param zipFileName
	 * @return
	 * @throws IOException
	 */
	public static InputStream unCompressSingleFile(String zipEntryName, String zipFileName) throws IOException {
		ZipFile zipFile = new ZipFile(zipFileName, "gbk");
		ZipEntry entry = zipFile.getEntry(zipEntryName);
		if (entry == null) {
			throw new RuntimeException("file not exist : " + zipEntryName);
		}
		if (entry.isDirectory()) {
			throw new RuntimeException("path is directory : " + zipEntryName);
		}
		if (entry.getSize() > 1024 * 1024 * 2) {// 2M
			throw new RuntimeException("file too big : " + zipEntryName);
		}

		return zipFile.getInputStream(entry);
	}

	public static void main(String[] args) throws Exception {

		unCompress("D:/backup/app/m_domestic_flight.zip", "D:/backup/app/m_domestic_flight", false);
	}
}
