package cn.com.cnnoc.expert.dao;

import cn.com.cnnoc.expert.model.User;

public interface UserDao extends BaseDao<User>{

	public User findUserByUsername(String username);
}
