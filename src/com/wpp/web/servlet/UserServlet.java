package com.wpp.web.servlet;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.wpp.domain.User;
import com.wpp.service.UserService;
import com.wpp.service.impl.UserServiceImpl;
import com.wpp.utils.MD5Utils;
import com.wpp.utils.StringTransformDate;
import com.wpp.utils.UUIDUtils;

/**
 * user
 */
@SuppressWarnings("serial")
public class UserServlet extends BaseServlet {

	/**
	 * 注册用户add
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//封装数据
		User user = new User();
		ConvertUtils.register(new StringTransformDate(), Date.class);
		BeanUtils.populate(user, request.getParameterMap());
		user.setUid(UUIDUtils.getId());
		user.setCode(UUIDUtils.getCode());
		user.setPassword(MD5Utils.md5(user.getPassword()));
		//调用service完成添加用户操作
		new UserServiceImpl().addUser(user);
		
		request.setAttribute("msg", "注册成功快去邮箱激活吧");
		return "/jsp/msg.jsp";
	}

	/**
	 * 激活用户active
	 */
	public String active(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//获取激活码
		String code=request.getParameter("code");
		//根据激活码获取用户
		UserService userService=new UserServiceImpl();
		User user=userService.getUserByCode(code);
		//判断code是否为空 为空则已激活
		if(code == null) {
			request.setAttribute("msg", "已激活！");
			return "/jsp/msg.jsp";
		}
		//判断user是否为空 
		if(user==null) {
			request.setAttribute("msg", "激活失败，请重试！");
			return "/jsp/msg.jsp";
		}
		//设置用户状态为激活，并将注册码置为null
		user.setCode(null);
		user.setState(1);
		//调用service层方法 完成用户状态修改操作
		UserService userState=new UserServiceImpl();
		userState.updateUser(user);
		request.getSession().setAttribute("user", user);
		response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
		return null;
	}
	
	/**
	 * 用户登录界面
	 */
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "/jsp/login.jsp";
	}
	
	/**
	 * 用户登录实现
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception  {
	
		//获取参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//根据username和password查询用户
		UserService findUser=new UserServiceImpl();
		User user=null;
		try {
			user=findUser.selectUserByUsernameAndPassword(username,password);
			if(user==null) {
				request.setAttribute("error", "用户名或密码不匹配");
				return "/jsp/login.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("msg","登录失败");
			e.printStackTrace();
			return "/jsp/msg.jsp";	
		}
		request.getSession().setAttribute("user", user);
		response.sendRedirect(request.getContextPath()+"/");
		return null;
	}
	
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String outLogin(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath());
		return null;
	}
	
	public String testCate(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		
		System.out.println("hahaha");
		return "/jsp/index.jsp";
	}
}
