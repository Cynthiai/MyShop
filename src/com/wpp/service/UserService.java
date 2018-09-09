package com.wpp.service;

import com.wpp.domain.User;

/**
 * UserService的接口，定义规则
 * @author wpp
 *
 */
public interface UserService {

	/**
	 * 注册用户
	 * @param user
	 * @throws Exception
	 */
	void addUser(User user) throws Exception;

	/**
	 * 通过激活码，获取用户
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
	 * 根据用户名和密码查询用户
	 * @param username
	 * @param password
	 * @return
	 */
	User selectUserByUsernameAndPassword(String username, String password) throws Exception;
}
