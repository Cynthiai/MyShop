package com.wpp.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class BaseServlet extends HttpServlet {

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			//获取子类对象 即this
			Class<? extends BaseServlet> clazz = this.getClass();
			//获取请求的方法名
			String method = request.getParameter("method");
			if(method==null) {
				method="index";
			}
			//获取该方法
			Method m = clazz.getMethod(method, HttpServletRequest.class,HttpServletResponse.class);
			//让方法执行
			String uri=(String) m.invoke(this, request,response);
			if(uri!=null) {
				request.getRequestDispatcher(uri).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} 
	}
	
	public String index(HttpServletRequest request,HttpServletResponse response) {
		return null;
	}
}
