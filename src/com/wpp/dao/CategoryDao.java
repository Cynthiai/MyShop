package com.wpp.dao;

import java.util.List;

import com.wpp.domain.Category;

public interface CategoryDao {

	/**
	 * 查询类目标所有
	 * @return
	 * @throws Exception
	 */
	List<Category> findAll() throws Exception;

}
