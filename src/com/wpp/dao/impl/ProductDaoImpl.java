package com.wpp.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.wpp.dao.ProductDao;
import com.wpp.domain.Product;
import com.wpp.utils.DataSourceUtils;

public class ProductDaoImpl implements ProductDao{

	/**
	 * 查询最新商品
	 */
	@Override
	public List<Product> findHotProduct() throws Exception {
		
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product where is_hot = ? limit 9";
		List<Product> hotList = qr.query(sql, new BeanListHandler<>(Product.class), 1);
		return hotList;
	}

	@Override
	public List<Product> findNewProduct() throws Exception {

		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product order by pdate desc limit 9";
		List<Product> newList = qr.query(sql, new BeanListHandler<>(Product.class));
		return newList;
	}

	@Override
	public Product findDetailProduct(String pid) throws Exception {
		
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product where pid = ? limit 1";
		Product p = qr.query(sql, new BeanHandler<>(Product.class), pid);
		return p;
	}

	@Override
	public List<Product> findPageProducts(String cId, int currPage , int pageSize) throws Exception {

		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product where cid = ? limit ? , ?";
		List<Product> list = qr.query(sql, new BeanListHandler<>(Product.class), cId,(currPage-1)*pageSize,pageSize);
		return list;
	}

	/**
	 * 查询商品的总数目
	 */
	@Override
	public int findTotalProducts(String cId) throws Exception {

		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select count(*) from product where cid = ?";
		return ((Long)qr.query(sql, new ScalarHandler(), cId)).intValue();
	}
}
