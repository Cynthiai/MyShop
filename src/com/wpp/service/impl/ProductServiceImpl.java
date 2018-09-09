package com.wpp.service.impl;

import java.util.List;

import com.wpp.dao.ProductDao;
import com.wpp.dao.impl.ProductDaoImpl;
import com.wpp.domain.Product;
import com.wpp.domain.ProductsPage;
import com.wpp.service.ProductService;

public class ProductServiceImpl implements ProductService{

	/**
	 * 查询热门商品
	 */
	@Override
	public List<Product> findHotProduct() throws Exception {

		ProductDao p=new ProductDaoImpl();
		//查询最新
		List<Product> hotList=p.findHotProduct();
		return hotList;
	}

	@Override
	public List<Product> findNewProduct() throws Exception {
		
		ProductDao p=new ProductDaoImpl();
		//查询最新
		List<Product> newList=p.findNewProduct();
		return newList;
	}

	/**
	 * 查询单个商品详细信息
	 */
	@Override
	public Product findOneProduct(String pid) throws Exception {
		
		ProductDao p=new ProductDaoImpl();
		Product product=p.findDetailProduct(pid);
		return product;
	}

	/**
	 * 安类目分页查询,封装pagebean
	 */
	@Override
	public ProductsPage findPageProducts(String cId, int currPage , int pageSize) throws Exception {
		
		//查询所在页商品
		ProductDao p=new ProductDaoImpl();
		List<Product> list=p.findPageProducts(cId,currPage,pageSize);
		//查询totalProducts
		int totalCount=p.findTotalProducts(cId);
		int totalPage=(int)Math.ceil(totalCount*1.0/pageSize);
		//创建pagebean
		ProductsPage productPage=new ProductsPage(list, currPage, pageSize, totalCount, totalPage);
		return productPage;
	}

}
