package com.wpp.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.wpp.dao.CategoryDao;
import com.wpp.domain.Category;
import com.wpp.utils.DataSourceUtils;

public class CategoryDaoImpl implements CategoryDao {

	/**
	 * 查询类目表所有  dao层实现 
	 */
	@Override
	public List<Category> findAll() throws Exception {
		
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from category";
		List<Category> list = qr.query(sql, new BeanListHandler<>(Category.class));

		return list;

	}
}
