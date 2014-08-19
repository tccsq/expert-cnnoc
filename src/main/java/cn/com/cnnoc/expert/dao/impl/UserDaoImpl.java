package cn.com.cnnoc.expert.dao.impl;

import org.apache.ibatis.session.SqlSession;

import cn.com.cnnoc.expert.dao.UserDao;
import cn.com.cnnoc.expert.model.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User findUserByUsername(String username) {
		SqlSession session = getSessionFactory().openSession();
		User user = null;
		try {
			user = (User) session.selectOne(User.class.getName()
					+ ".findUserByUsername", username);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return user;
	}

	@Override
	public User findUserByEmpno(String empno) {
		SqlSession session = getSessionFactory().openSession();
		User user = null;
		try {
			user = (User) session.selectOne(User.class.getName()
					+ ".findUserByEmpno", empno);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}
}
