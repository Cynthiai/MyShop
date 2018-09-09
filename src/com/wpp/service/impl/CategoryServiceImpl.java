package com.wpp.service.impl;

import java.io.InputStream;
import java.util.List;

import com.wpp.dao.CategoryDao;
import com.wpp.domain.Category;
import com.wpp.service.CategoryService;
import com.wpp.utils.BeanFactory;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;





public class CategoryServiceImpl implements CategoryService {

	/**
	 * 查询类目标所有实现
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> findAll() throws Exception {
		
		//获去src目录下的cache.xml文件
		InputStream is = CategoryServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml");
		//获取缓存管理器
		CacheManager cm=CacheManager.create(is);
		//获取指定的缓存对象
		Cache cache = cm.getCache("categoryCache");
		//通过缓存读取数据
		Element element = cache.get("clist");
		List<Category> list=null;
		//判断读取的缓存对象 是否为空
		if(element==null) {
			//从数据库中读
			CategoryDao cd=(CategoryDao) BeanFactory.getBean("CategoryDao");
			list=cd.findAll();
			
			//将list放入缓存中
			cache.put(new Element("clist",list));
			//System.out.println("cache没有数据 从数据库");
		}else {
			list=(List<Category>) element.getObjectValue();
			//System.out.println("cache中有数据直接取。。。");		
		}
		return list;
	}
}
