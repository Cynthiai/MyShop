package com.wpp.web.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wpp.domain.Product;
import com.wpp.domain.ProductsPage;
import com.wpp.service.ProductService;
import com.wpp.service.impl.ProductServiceImpl;
import com.wpp.utils.CookUtils;

/**
 * product商品类
 */
@SuppressWarnings("serial")
public class ProductServlet extends BaseServlet {

	/**
	 * 查询单个商品的详细信息、并记录浏览历史放入cookie中
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public String detailOneProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		/*
		 * 商品详情
		 */
		String pid=request.getParameter("pid");
		ProductService p=new ProductServiceImpl();
		Product product=null;
		try {
			product = p.findOneProduct(pid);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		request.setAttribute("detailProduct", product);
		/*
		 * 商品浏览历史
		 */
		String id=product.getPid();
		String ids="";
		Cookie[] cookies = request.getCookies();
		Cookie cookie=CookUtils.getCookieByName("ids", cookies);
		if(cookie==null) {
			//将id放入ids中，稍后统一创建cookie
			//System.out.println("没有cookie，创建");
			ids=id;
		}else {
			//获取cookie值
			System.out.println("有cookie");
			String[] value=cookie.getValue().split("-");
			//将数组变成list集合
			List<String> asList = Arrays.asList(value);
			//不可变长list变成可变大小list
			LinkedList<String> linkedList = new LinkedList<String>(asList);
			
			if(linkedList.contains(id)) {
				linkedList.remove(id);
				linkedList.addFirst(id);
			}else {
				if(linkedList.size()>=5) {
					linkedList.removeLast();
					linkedList.addFirst(id);
				}else {
					linkedList.addFirst(id);
				}
			}
			//将list转换为字符串写入cookie
			for(String str : linkedList) {
				ids+=str+"-";
			}
			ids=ids.substring(0, ids.length()-1);
		}
		System.out.println(ids);
		//将数据写入cookie
		cookie=new Cookie("ids",ids);
		cookie.setMaxAge(36000);
		cookie.setPath(request.getContextPath()+"/");
		response.addCookie(cookie);
		
		return "/jsp/product_info.jsp";
	}

	/**
	 * 通过类目查询，并将结果分页显示
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findProductsPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//接受参数
		String cId=request.getParameter("cid");
		int currPage=Integer.parseInt(request.getParameter("currPage"));
		int pageSize=12;
		//调用service层完成操作
		ProductService p=new ProductServiceImpl();
		ProductsPage page=null;
		try {
			page=p.findPageProducts(cId,currPage,pageSize);
			//System.out.println(page);
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/jsp/product_list.jsp";
	}
}
