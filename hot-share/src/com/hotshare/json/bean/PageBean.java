/**
 * Program  : PageBean.java
 * Author   : lhzh
 * Create   : 2013-1-18 上午10:53:25
 */

package com.hotshare.json.bean;

/**
 * 接收 datagrid传递过来的分页参数
 * 
 * @author lhzh
 * @version 1.0.0
 * @2013-1-18 上午10:53:25
 */
public class PageBean {

	private int page;

	private int rows;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
}