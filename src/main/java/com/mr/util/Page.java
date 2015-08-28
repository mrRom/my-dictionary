package com.mr.util;

import java.util.List;

public class Page<T> implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int page;
	private int max;
	private int total;
	private List<T> rows;
	
	public Page(int page, int max, int total, List<T> rows) {
		this.page = page;
		this.max = max;
		this.total = total;
		this.rows = rows;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
}
