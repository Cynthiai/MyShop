package com.wpp.domain;

import java.util.List;

public class ProductsPage {

	/**
	 * pageBean中
	 * list  currPage pageSize totalPage totalCount
	 */
	private List<Product> list;
	private Integer currPage;   
	private Integer pageSize=12;
	private Integer totalCount;
	private Integer totalPage;
	public List<Product> getList() {
		return list;
	}
	public void setList(List<Product> list) {
		this.list = list;
	}
	public Integer getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return (int)Math.ceil(totalCount*1.0/pageSize);
	}
	public ProductsPage(List<Product> list, Integer currPage, Integer pageSize, Integer totalCount, Integer totalPage) {
		super();
		this.list = list;
		this.currPage = currPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
	}
	public ProductsPage() {
		super();
	}
	@Override
	public String toString() {
		return "ProductsPage [list=" + list + ", currPage=" + currPage + ", pageSize=" + pageSize + ", totalCount="
				+ totalCount + ", totalPage=" + totalPage + "]";
	}
	
	
}
