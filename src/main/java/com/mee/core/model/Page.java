package com.mee.core.model;
import java.util.List;

/**
 * @author funnyzpc
 * data page,默认每页10条
 */
public class Page<T> {
	// query params
	private Object params;
	// total page
	private int total;
	// this page
	private int index;
	// default page size
	private int size = 10;
	// this page data
	private List<T> data;

	public Object getParams() {
		return params;
	}

	public void setParams(Object params) {
		this.params = params;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageCount(){
		return (int)Math.ceil(total * 1.0 / size);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<T> getData() {
		return data;
	}

	public Page setData(List<T> data) {
		this.data = data;
		return this;
	}

	public int getTotalCount(){
		return null == data ? 0 : data.size();
	}
	public Page() {
		super();
	}
	
	public Page(int index) {
		super();
		this.index = index;
	}
	public Page(Object params, int index, int size) {
		super();
		this.params = params;
		this.index = index;
		this.size = size;
	}
}
