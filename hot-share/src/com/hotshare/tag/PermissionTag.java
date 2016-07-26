/**
 * Program  : PermissionTag.java
 * Author   : chenshilong
 * Create   : 2012-5-9 上午10:37:11
 */

package com.hotshare.tag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;


/**
 * 
 * 用户权限检测自定义标签
 * 
 * @author   chenshilong
 * @version  1.0.0
 * @2012-5-9 上午10:37:11
 */
public class PermissionTag extends TagSupport{

	private static final long serialVersionUID = 278570687508374845L;
	
	private Logger logger = Logger.getLogger(PermissionTag.class);
	
	/** 当前页面检测权限 */
	private String permission;
	
	/**
	 * 
	 * 权限检测构造函数
	 * 
	 * @author chenshilong
	 * @create 2012-5-24 上午9:24:40
	 * @since
	 */
	public PermissionTag(){}
	
	/**
	 * 
	 * 权限检测
	 * 
	 * @author chenshilong
	 * @create 2012-5-24 上午9:07:49
	 * @since 
	 * @return
	 * @throws JspException
	 */
	public int doStartTag() throws JspException {
		boolean result = false;
//		UserInfoBean userInfoBean = UserInfoUtil.getSessionUserInfo((HttpServletRequest) pageContext.getRequest());
//		if(userInfoBean != null){
//			List<Role> list = userInfoBean.getGroup().getRoleList();
//			for(Role role : list){
//				if(role.getPermission().equals(permission)){
					result = true;
//					break;
//				}
//			}
//		}
		return result ? EVAL_BODY_INCLUDE : SKIP_BODY;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
}

