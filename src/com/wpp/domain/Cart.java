package com.wpp.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class Cart implements Serializable{

	private Map<String,CartItem> map=new LinkedHashMap<String,CartItem>();
	private Double total = 0.0;
	
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	/**
	 *获取购物项 
	 * @return
	 */
	public Collection<CartItem> getItems() {
		
		return map.values();
	}
	
	/**
	 * 添加商品到Cart
	 */
	public void add2Cart(CartItem item) {
		
		String pid = item.getProduct().getPid();
		//判断购物项中，若有则在原基础增加
		if(map.containsKey(pid)) {
			CartItem ct = map.get(pid);
			ct.setCount(ct.getCount()+item.getCount());
		//没有则添加
		}else {
			map.put(pid, item);
		}
		total += item.getSubtotal();
	}
	
	/**
	 *购物项移除
	 */
	
	public void removeItem(String pid) {
		
		CartItem item = map.get(pid);
		map.remove(pid);
		total -=item.getSubtotal();
	}
	
	/**
	 * 删除购物车
	 */
	public void clearCart() {
		
		map.clear();
		total=0.0;
	}
}
