package com.wpp.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wpp.domain.Cart;
import com.wpp.domain.CartItem;
import com.wpp.domain.Product;
import com.wpp.service.ProductService;
import com.wpp.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class Cart
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 获取购物车session
	 * @param request
	 * @return
	 */
	public Cart getCart(HttpServletRequest request) {
		
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		if(cart==null) {
			cart=new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	/**
	 * 添加到购物车
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String add2Cart(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		String pid = request.getParameter("pid");
		int count=Integer.parseInt(request.getParameter("count"));
		
		/*System.out.println(pid);
		System.out.println(count);*/
		ProductService ps=new ProductServiceImpl();
		Product product = ps.findOneProduct(pid);
		//封装购物项
		CartItem cartItem=new CartItem(product, count);
		//将购物项添加到购物车
		Cart cart = getCart(request);
		cart.add2Cart(cartItem);
		//重定向
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		return null;
	}

	/**
	 * 删除某一个商品
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String removeFromCartItem(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String pid = request.getParameter("pid");
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart!=null) {
			cart.removeItem(pid);
		}
		//重定向
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		return null;
	}
	
	/**
	 * 删除购物车
	 * @throws Exception 
	 */
	public String clearCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart!=null) {
			cart.clearCart();
		}
		//重定向
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		return null;
	}
}
