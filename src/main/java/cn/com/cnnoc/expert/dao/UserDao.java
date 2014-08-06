package cn.com.cnnoc.expert.dao;

import cn.com.cnnoc.expert.model.User;

public interface UserDao extends BaseDao{
	public void addUser(User user);
	
	public void updateUser(User user);

	public void deleteUser(int id);

	public User findUserByUsername(String username);
}
