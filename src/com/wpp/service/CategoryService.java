package com.wpp.service;

import java.util.List;

import com.wpp.domain.Category;

public interface CategoryService {

	List<Category> findAll() throws Exception;

}
