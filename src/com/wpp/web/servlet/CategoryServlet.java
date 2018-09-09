package com.wpp.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wpp.domain.Category;
import com.wpp.service.CategoryService;
import com.wpp.service.impl.CategoryServiceImpl;

import net.sf.json.JSONArray;

@SuppressWarnings("serial")
public class CategoryServlet extends BaseServlet {
	
	/**
	 * 查询category所有元素，封装成json返回
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		CategoryService category = new CategoryServiceImpl();
		List<Category> list = category.findAll();
		JSONArray jsonList = JSONArray.fromObject(list);
		//System.out.println(jsonList.toString());
		response.setContentType("html/text;charset=utf-8");
		response.getWriter().println(jsonList.toString());
		return null;
	}

}
