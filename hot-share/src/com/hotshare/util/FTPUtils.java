package com.hotshare.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
/**
 * ftp工具�?
 * @author misery
 * @create 2013-4-12 上午8:36:59
 * @since
 */
public class FTPUtils {
	private static Logger logger = Logger.getLogger(FTPUtils.class);

	private static FTPClient ftp = null;
	

	/**
	 * @param ip ftp�?��服务器ip
	 * @param name ftp用户�?
	 * @param pwd ftp登录密码
	 * @param port ftp端口
	 * @param root 相对ftp上的�?��目录 
	 * @return
	 */
	public static void getInstance(String ip,String name, String pwd, Integer port){
		
		if(ftp==null)
		{
			ftp = new FTPClient();
			
			try {
				ftp.connect(ip, port);
				ftp.login(name, pwd);// 登录
				ftp.setControlEncoding("GBK");
				int reply = ftp.getReplyCode();
				if (!FTPReply.isPositiveCompletion(reply)) {
					ftp.disconnect();
					throw new RuntimeException("连接ftp服务器失�? ip = " + ip);
				}
				logger.info("login success! ip = " + ip + " ; port = " + port);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
			
		}else{
			try {
				ftp.connect(ip, port);
				ftp.login(name, pwd);// 登录
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
	}

	/**
	 * 将本地localDirPath （包括该目录）文件夹拷到ftp 的ftpPath目录�?
	 * @author zhangjj
	 * @create 2012-9-11 下午5:37:50
	 * @since 
	 * @param ftpPath ftp目录
	 * @param localDirPath 本地目录
	 * @return
	 * @throws IOException 
	 */
	public static Boolean uploadDir(String localDirPath ,String ftpPath) throws IOException{
		File dir = new File(localDirPath);
		if(dir.isDirectory()){
			File[] files = dir.listFiles();
			for(File f : files){
				String path = f.getPath();
				uploadDir(path, ftpPath + File.separator + f.getParentFile().getName());
			}
		}else{
			uploadFile(localDirPath,ftpPath + File.separator + new File(localDirPath).getName());
		}
		
		return false;
	}
	
	/**
	 * 
	 * @author zhangjj
	 * @create 2012-9-11 下午4:12:22
	 * @since 
	 * @param ftpFileName 上传到ftp上的存放文件�?
	 * @param localFilePath 本地�?��上传文件全名
	 * @return
	 */
	public static Boolean uploadFile(String localFilePath, String ftpFileName){
		InputStream input = null;
		try {
			System.out.println(localFilePath);
			System.out.println(ftpFileName);
			input = new FileInputStream(localFilePath);
			createDir(new File(ftpFileName).getParent());
			ftp.changeWorkingDirectory(new File(ftpFileName).getParent());
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.storeFile(new File(ftpFileName).getName(), input);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally{
			try {
				input.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return false;
	}
	
	public static void createDir(String ftpPath) throws IOException{
		StringTokenizer token = new StringTokenizer(new File(ftpPath).getPath(),File.separator);
		String dirName = null;
		ftp.changeWorkingDirectory("/");
		while(token.hasMoreTokens()){
			dirName = token.nextElement().toString();
			ftp.makeDirectory(dirName);
			ftp.changeWorkingDirectory(dirName);
		}
		ftp.changeWorkingDirectory("/");
	}
	
	public static Boolean changeWorkingDirectory(String pathName) throws IOException{
		return ftp.changeWorkingDirectory(pathName);
	}
	
	public static void disconnect(){
		if (ftp.isConnected()) {
			try {
				logger.info("ftp disconnect! ip = " + ftp.getRemoteAddress() + " ; port = " + ftp.getRemotePort());
				ftp.logout();
				ftp.disconnect();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public void reConnect(){
//		try {
//			this.ftp.connect(this.ip, this.port);
//			this.ftp.login(this.name, this.pwd);// 登录
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
	}
	
	public static void main(String[] args) throws IOException {
		FTPUtils.getInstance("192.168.2.201", "admin", "admin", 21000);
    	FTPUtils.uploadFile("D:/broadin/apache-tomcat-5.5.28/webapps/oitv-wms/res/icon/icon.jpg", "/apache/htdocs/icon/oitv-win/channel/1944553877.jpg");
		FTPUtils.disconnect();
	}
}
