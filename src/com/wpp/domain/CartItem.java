package com.wpp.domain;

import java.io.Serializable;

/**
 * 购物车项，包括商品、数量、小计
 * @author wpp
 *
 */
@SuppressWarnings("serial")
public class CartItem implements Serializable{

	private Product product;
	private Integer count = 0;
	@SuppressWarnings("unused")
	private Double subtotal = 0.0;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getSubtotal() {
		return product.getShop_price()*count;
	}
	
	public CartItem(Product product, Integer count) {
		super();
		this.product = product;
		this.count = count;
	}
	public CartItem() {
		super();
	}
}
