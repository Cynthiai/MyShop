package com.wpp.web.servlet;


import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wpp.domain.Product;
import com.wpp.service.ProductService;
import com.wpp.service.impl.ProductServiceImpl;


/**
 * Servlet implementation class IndexServlet
 */
@SuppressWarnings("serial")
public class IndexServlet extends BaseServlet {
	
	/**
	 * 查询数据库，将热门商品和最新商品展示
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public String index(HttpServletRequest request, HttpServletResponse response){
		
		ProductService p=new ProductServiceImpl();
		List<Product> hotList=null;
		List<Product> newList=null;
		//查询热门商品
		try {
			hotList=p.findHotProduct();
			//System.out.println(hotList.toString());
			request.setAttribute("hotProduct", hotList);
			newList=p.findNewProduct();
			request.setAttribute("newProduct", newList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//查询最新商品
		return "/jsp/index.jsp";
	}

}
