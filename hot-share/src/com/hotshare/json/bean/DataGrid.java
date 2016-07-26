/**
 * Program  : DataGrid.java
 * Author   : lhzh
 * Create   : 2013-1-18 上午9:08:09
 */

package com.hotshare.json.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * json数据表格
 * 
 * @author lhzh
 * @version 1.0.0
 * @2013-1-18 上午9:08:09
 */
public class DataGrid<T> {

	private List<T> rows;

	private long total;

	public DataGrid() {
		rows = new ArrayList<T>();
		total = 0;
	}

	public DataGrid(List<T> rows, int total) {
		this.rows = rows;
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
}
