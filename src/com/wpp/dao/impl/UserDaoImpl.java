package com.wpp.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.wpp.dao.UserDao;
import com.wpp.domain.User;
import com.wpp.utils.DataSourceUtils;
import com.wpp.utils.MD5Utils;

/**
 * userDao接口的实现类
 * @author wpp
 *
 */
public class UserDaoImpl implements UserDao{

	/**
	 * 添加用户方法实现
	 * @throws SQLException 
	 */
	@Override
	public void addUser(User user) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into user values(?,?,?,?,?,?,?,?,?,?)";
		qr.update(sql, user.getUid(),user.getUsername(),user.getPassword(),
				user.getName(),user.getEmail(),user.getTelephone(),
				user.getBirthday(),user.getSex(),user.getState(),user.getCode());
	}

	@Override
	public User getUserByCode(String code) throws Exception {
		
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from user where code = ?";
		return qr.query(sql, new BeanHandler<User>(User.class), code);
	}

	@Override
	public void updateUser(User user) throws Exception {
		
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update user set username = ? , password = ? ,"
				+ "name = ? , email = ? , "
				+ "sex = ? , state = ? , code = ? where uid = ?";
		qr.update(sql, user.getUsername(),user.getPassword(),
				user.getName(),user.getEmail(),user.getSex(),
				user.getState(),user.getCode(),user.getUid());
	}

	/**
	 * 根据用户名查询用户信息
	 * @throws Exception 
	 */
	@Override
	public User selectUserByUsernameAndPassword(String username, String password) throws Exception {
		
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from user where username = ? and password = ?";
		String pwd=MD5Utils.md5(password);
		return qr.query(sql, new BeanHandler<User>(User.class), username,pwd);
	}
}
