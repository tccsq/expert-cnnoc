package cn.com.cnnoc.expert.dao;

import cn.com.cnnoc.expert.model.User;

public interface UserDao extends BaseDao<User> {
	
	/**
	 * 根据用户名查询用户		
	 * @param username
	 * @return
	 */
	public User findUserByUsername(String username);
	
	/**
	 * 根据员工号查询用户
	 * @param empno
	 * @return
	 */
	public User findUserByEmpno(String empno);
}
