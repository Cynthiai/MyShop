package com.wpp.service.impl;

import java.sql.SQLException;

import com.wpp.dao.UserDao;
import com.wpp.dao.impl.UserDaoImpl;
import com.wpp.domain.User;
import com.wpp.service.UserService;
import com.wpp.utils.MailUtils;

/**
 * UserService的实现类
 * @author wpp
 *
 */
public class UserServiceImpl implements UserService{

	/**
	 *注册用户addUser(user)
	 * @throws SQLException 
	 */
	@Override
	public void addUser(User user) throws Exception {
		
		//完成添加操作
		UserDao userDao=new UserDaoImpl();
		userDao.addUser(user);
		
		
		//email:收件人地址
		//emailMsg:邮件的内容
		String emailMsg="欢迎您注册成我们的一员,<a href='http://localhost/store/user?method=active&code="+user.getCode()+"'>点此激活</a>";
		MailUtils.sendMail(user.getEmail(), emailMsg);
	}

	@Override
	public User getUserByCode(String code) throws Exception {
		
		UserDao userDao=new UserDaoImpl();
		User user=userDao.getUserByCode(code);
		return user;
	}

	@Override
	public void updateUser(User user) throws Exception {
		
		UserDao userDao=new UserDaoImpl();
		userDao.updateUser(user);
	}

	@Override
	public User selectUserByUsernameAndPassword(String username, String password) throws Exception {
		
		UserDao findUser=new UserDaoImpl();
		return findUser.selectUserByUsernameAndPassword(username,password);
		
	}

	
}
