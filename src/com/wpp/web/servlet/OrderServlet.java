package com.wpp.web.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wpp.domain.Cart;
import com.wpp.domain.CartItem;
import com.wpp.domain.OrderItem;
import com.wpp.domain.Orders;
import com.wpp.domain.User;
import com.wpp.service.OrderService;
import com.wpp.service.impl.OrderServiceImpl;
import com.wpp.utils.UUIDUtils;

/**
 * order 订单的servlet
 */
public class OrderServlet extends BaseServlet {

	/**
	 * 生成订单的方法，写入数据库
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String submitOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//判断用户有没有登录 如没有则先登录
		User user=(User)request.getSession().getAttribute("user");
		if(user==null) {
			request.setAttribute("msg", "还没有登录，请先去登录！");
			return "/jsp/msg.jsp";
		}
		//封装数据
		Orders or=new Orders();
		or.setOid(UUIDUtils.getId());
		or.setOrdertime(new Date());
		
		//获取购物车，已放入session中
		Cart cart=(Cart)request.getSession().getAttribute("cart");
		or.setTotal(cart.getTotal());
		or.setUser(user);
		//遍历购物项内容
		Collection<CartItem> items = cart.getItems();
		for(CartItem ci : items) {
			OrderItem oi=new OrderItem();
			/**
			 * `itemid` varchar(32) NOT NULL,
			  `count` int(11) DEFAULT NULL,
			  `subtotal` double DEFAULT NULL,
			  `pid` varchar(32) DEFAULT NULL,
			  `oid` varchar(32) DEFAULT NULL,
			 */
			oi.setItemid(UUIDUtils.getId());
			oi.setCount(ci.getCount());
			oi.setSubtotal(ci.getSubtotal());
			oi.setProduct(ci.getProduct());
			oi.setOrders(or);
			or.getItems().add(oi);
		}
		//调用service完成操作
		OrderService os=new OrderServiceImpl();
		try {
			os.addOrder(or);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将order放入request域中,请求转发
		request.setAttribute("order", or);
		//删除购物车中的数据
		request.getSession().setAttribute("cart", null);
		return "/jsp/order_info.jsp";
	}

}
