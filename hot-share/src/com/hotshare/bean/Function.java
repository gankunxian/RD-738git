package com.hotshare.bean;

/**
 * 功能bean
 * ClassName: Function <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: 2014年8月5日 下午2:29:39 <br/>  
 *  
 * @author lhzh  
 * @version   
 * @since JDK 1.6
 */
public class Function {

	/**  权限id */
	private Integer id ;
	
	/**  权限代号(add、del、mod等)  */
	private String funCode ;

	/**  权限名称(增加、删除等)  */
	private String funName ;
	
	/**  图标  */
	private String img ;
	
	/**  权限描述 */
	private String descInfo ;

	public Function() {
		super();
	}

	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getFunCode() {
		return funCode;
	}



	public void setFunCode(String funCode) {
		this.funCode = funCode;
	}



	public String getFunName() {
		return funName;
	}



	public void setFunName(String funName) {
		this.funName = funName;
	}



	public String getImg() {
		return img;
	}



	public void setImg(String img) {
		this.img = img;
	}



	public String getDescInfo() {
		return descInfo;
	}



	public void setDescInfo(String descInfo) {
		this.descInfo = descInfo;
	}



	public Function(Integer id, String funCode, String funName, String img,
			String descInfo) {
		super();
		this.id = id;
		this.funCode = funCode;
		this.funName = funName;
		this.img = img;
		this.descInfo = descInfo;
	}



	@Override
	public String toString() {
		return this.funCode;
	}

	@Override
	public int hashCode() {
		return this.id;
	}

	@Override
	public boolean equals(Object obj) {
		return obj.toString().equals(this.toString());
	}
}
