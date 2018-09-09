package com.wpp.dao;

import java.sql.SQLException;

import com.wpp.domain.User;

/**
 * UserDao接口，定义规则
 * @author wpp
 *
 */
public interface UserDao {

	/**
	 * 注册用户
	 * @throws SQLException 
	 */
	void addUser(User user) throws Exception;

	/**
	 * 根据激活码，获取用户
	 * @param code
	 * @return
	 * @throws Exception
	 */
	User getUserByCode(String code) throws Exception;

	/**
	 * 更新用户信息
	 * @param user
	 */
	void updateUser(User user) throws Exception;

	/**
	 * 根据用户名和密码查询用户信息
	 * @param username
	 * @param password
	 * @return
	 */
	User selectUserByUsernameAndPassword(String username, String password) throws Exception;
}
