package com.wpp.service;

import java.util.List;

import com.wpp.domain.Product;
import com.wpp.domain.ProductsPage;

public interface ProductService {

	List<Product> findHotProduct() throws Exception;

	List<Product> findNewProduct() throws Exception;

	Product findOneProduct(String pid) throws Exception;

	ProductsPage findPageProducts(String cId, int currPage ,int pageSize) throws Exception;

	
}
