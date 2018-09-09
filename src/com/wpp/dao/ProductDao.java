package com.wpp.dao;

import java.util.List;

import com.wpp.domain.Product;

public interface ProductDao {

	List<Product> findHotProduct() throws Exception;

	List<Product> findNewProduct() throws Exception;

	Product findDetailProduct(String pid) throws Exception;

	List<Product> findPageProducts(String cId, int currPage,int pageSize) throws Exception;

	int findTotalProducts(String cId) throws Exception;

}
